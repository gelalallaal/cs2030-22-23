class Serve extends Event {
    private final Server server;
    private final int i;

    public Serve(double time, Customer cust, Server s, int i) {
        super(time, cust);
        Server newServer = s.nextCustomer(cust);
        this.server = newServer;
        this.i = i;
    }

    public Server getServer() {
        return this.server;
    }

    public Done createDone() {
        Done done = new Done(super.getCust().getDepTime(), super.getCust(),
                this.getServer());
        return done;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d serves by %d", super.getTime(), 
                super.getCust().getID(), this.getServer().getID());
    }

    @Override
    public Pair<ImList<Event>, Update>  run(ImList<Server> servers, 
            int served, int left) {
        ImList<Event> events = new ImList<Event>();
        Pair<ImList<Event>, Update> update = 
                new Pair<>(events.add(this.createDone()), 
                new Update(servers.set(this.i, this.getServer()), 
                    this.toString(), served + 1, left));
        return update;
    }
}


