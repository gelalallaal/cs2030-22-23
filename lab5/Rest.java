class Rest extends Event {
    private final Server server;

    public Rest(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
        ImList<Server> autos, int served, int left, int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        Server newServer = this.server.rest();
        String output = "";
        servers = servers.set(this.server.id() - 1, newServer);
        if (newServer.rested()) {
            Event nextEv = new ServerBack(super.getTime(), super.getCust(),
                newServer);
            events = events.add(nextEv);
        }
        Pair<ImList<Event>, Update> update = new Pair<>(events,
                new Update(servers, autos, output, served, left, ttlTime));
        return update;
    }
}

