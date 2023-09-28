class ServerBack extends Event {
    private final Server server;

    public ServerBack(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
        ImList<Server> autos, int served, int left, int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        Server newServer = this.server.endRest();
        servers = servers.set(this.server.id() - 1, newServer);
        Pair<ImList<Event>, Update> update = new Pair<>(events,
                new Update(servers, autos, "", served, left, ttlTime));
        return update;
    }
}
