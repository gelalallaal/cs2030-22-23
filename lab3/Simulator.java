import java.util.function.Supplier;

class Simulator {
    private final int nServers;
    private final int qmax;
    private final ImList<Double> aTimes;
    private final Supplier<Double> sTime;

    public Simulator(int nServers, int qmax, ImList<Double> aTimes,
        Supplier<Double> sTime) {
        this.nServers = nServers;
        this.qmax = qmax;
        this.aTimes = aTimes;
        this.sTime = sTime;
    }

    public String simulate() {
        ImList<Server> servers = new ImList<Server>();
        PQ<Event> events = new PQ<Event>(new EventComparator());
        String output = "";
        int served = 0;
        int left = 0;
        double ttlTime = 0;

        for (int n = 0; n < nServers; n++) {
            servers = servers.add(new Server(n + 1));
        }
        for (int n = 0; n < aTimes.size(); n++) {
            Customer customer = new Customer(n + 1, aTimes.get(n), this.sTime);
            events = events.add(new Arrive(aTimes.get(n), customer));
        }
        while (!events.isEmpty()) {
            //emit events and update evnts list
            Pair<Event, PQ<Event>> sortedEv = events.poll();
            Event currEv = sortedEv.first();
            events = sortedEv.second();
            //run event
            Pair<ImList<Event>, Update> updates = currEv.run(servers,
                    served, left, this.qmax, ttlTime);
            ImList<Event> addEvents = updates.first();
            while (!addEvents.isEmpty()) {
                events = events.add(addEvents.get(0));
                addEvents = addEvents.remove(0);
            }
            Update update = updates.second();
            servers = update.getServers(); //updated servers
            output += update.getOutput(); //printed output
            served = update.getServed(); //no served
            left = update.getLeft(); //no left
            ttlTime = update.getTtlTime(); //total wait time
        }
        Double avgWait = 0.0;
        if (served != 0) {
            avgWait = ttlTime / served;
        }
        output = output + String.format("[%.3f %d %d]", avgWait,
            served, left);
        return output;
    }
}

