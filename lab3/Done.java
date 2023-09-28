class Done extends Event {
    private final Server server;

    public Done(double time, Customer cust, Server s) {
        super(time, cust);
        this.server = s;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", super.getTime(),
                super.getCust().getID(), this.server.id());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
            int served, int left, int qmax, double ttlTime) {
        Pair<ImList<Event>, Update> update = new Pair<>(new ImList<Event>(),
                new Update(servers, this.toString() + "\n", served, left, ttlTime));
        return update;
    }
}
