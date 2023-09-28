class Wait extends Event {
    private final Server server;

    public Wait(double time, Customer customer, Server s) {
        super(time, customer);
        this.server = s;
    }

    private Serve createServe(Double deptTime, Server s) {
        return new Serve(deptTime, super.getCust(), s);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits at %d", super.getTime(),
                super.getCust().getID(), this.server.id());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
            int served, int left, int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        Server thisServer = servers.get(this.server.id() - 1);
        //if this is first in queue make serve
        if (thisServer.queue().get(0).getID() == super.getCust().getID()) {
            Serve newServe = this.createServe(thisServer.deptTime(), thisServer);
            events = events.add(newServe);
        }
        Pair<ImList<Event>, Update> update = new Pair<>(events,
                new Update(servers, this.toString() + "\n", served, left,
                ttlTime));
        return update;
    }
}
