class Simulator {
    private final int nServers;
    private final ImList<Double> aTimes;
    private final ImList<Double> sTimes;

    public Simulator(int nServers, ImList<Double> aTimes, ImList<Double> sTimes) {
        this.nServers = nServers;
        this.aTimes = aTimes;
        this.sTimes = sTimes;
    }

    public String simulate() {
        ImList<Server> servers = new ImList<Server>();
        PQ<Event> events = new PQ<Event>(new EventComparator());
        String output = "";
        int served = 0;
        int left = 0;

        //make list of servers
        for (int s = 1; s <= this.nServers; s++) {
            servers = servers.add(new Server(s));
        }

        //make customers and queue list of arrival events
        for (int s = 0; s < this.aTimes.size(); s++) {
            double aTime = this.aTimes.get(s);
            Customer c = new Customer(s + 1, aTime, this.sTimes.get(s));
            events = events.add(new Arrive(aTime, c));
        }

        //emitting events
        while (!events.isEmpty()) {
            //emit events and update evnts list
            Pair<Event, PQ<Event>> sortedEv = events.poll();
            Event currEv = sortedEv.first();
            /*
            if (currEv.getClass().getSimpleName() == "Serve") {
                served += 1;
            } else if (currEv.getClass().getSimpleName() == "Leave") {
                left += 1;
            } */
            events = sortedEv.second();
            //run event
            Pair<ImList<Event>, Update> update = currEv.run(servers,
                    served, left);
            served = update.second().getServed();
            left = update.second().getLeft();
            output = output + update.second().getOutput() + "\n";
            ImList<Event> evToAdd = update.first();
            while (evToAdd.size() > 0) {
                events = events.add(evToAdd.get(0));
                evToAdd = evToAdd.remove(0);
            }

            servers = update.second().getServers();
        }
        output = output + String.format("[%d %d]", served, left);
        return output;
    }
}
