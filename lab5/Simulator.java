import java.util.function.Supplier;

class Simulator {
    private final int nServers;
    private final int nAuto;
    private final int qmax;
    private final ImList<Double> aTimes;
    private final Supplier<Double> sTime;
    private final Supplier<Double> rTime;

    public Simulator(int nServers, int nAuto, int qmax, ImList<Double> aTimes,
            Supplier<Double> sTime, Supplier<Double> rTime) {
        this.nServers = nServers;
        this.nAuto = nAuto;
        this.qmax = qmax;
        this.aTimes = aTimes;
        this.sTime = sTime;
        this.rTime = rTime;
    }

    public String simulate() {
        ImList<Server> servers = new ImList<Server>();
        ImList<Server> autos  = new ImList<Server>();
        PQ<Event> events = new PQ<Event>(new EventComparator());
        String output = "";
        int served = 0;
        int left = 0;
        double ttlTime = 0;

        for (int n = 0; n < nServers; n++) {
            servers = servers.add(new HumanServer(n + 1, this.rTime));
        }

        for (int n = nServers; n < nServers + nAuto; n++) {
            autos = autos.add(new AutoServer(n + 1));
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
            Pair<ImList<Event>, Update> updates = currEv.run(servers,autos,
                    served, left, this.qmax, ttlTime);
            ImList<Event> addEvents = updates.first();
            while (!addEvents.isEmpty()) {
                events = events.add(addEvents.get(0));
                addEvents = addEvents.remove(0);
            }
            Update update = updates.second();
            servers = update.getServers(); //updated servers
            autos = update.getAutos(); //updated autos
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

