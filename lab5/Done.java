class Done extends Event {
    private final Server server;

    public Done(double time, Customer cust, Server s) {
        super(time, cust);
        this.server = s;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %s", super.getTime(),
                super.getCust().getID(), this.server.toString());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
        ImList<Server> autos, int served, int left, int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        int nServers = servers.size();
        if (this.server.id() <= nServers) {
            Server thisServer = servers.get(this.server.id() - 1);
            Event nextEv = new Rest(super.getTime(), super.getCust(),
                thisServer);
            events = events.add(nextEv);
        }
        Pair<ImList<Event>, Update> update = new Pair<>(events,
                new Update(servers, autos, this.toString() + "\n", served, left, ttlTime));
        return update;
    }
}
