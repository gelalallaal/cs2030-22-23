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
        return String.format("%.3f %d waits at %s", super.getTime(),
                super.getCust().getID(), this.server.toString());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
        ImList<Server> autos, int served, int left, int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        int nServers = servers.size();
        if (this.server.id() <= nServers) {
            Server thisServer = servers.get(this.server.id() - 1);
            //if this is first in queue make serve
            if (thisServer.queue().get(0).getID() == super.getCust().getID()) {
                Serve newServe = this.createServe(thisServer.deptTime(),
                    thisServer);
                events = events.add(newServe);
            }
        } else {
            Server thisserver = autos.get(0);
            for (int i = 1; i < autos.size(); i++) {
                if (autos.get(i).deptTime() < thisserver.deptTime()) {
                    thisserver = autos.get(i);
                }
            }
            Serve newServe = this.createServe(thisserver.deptTime(),
                thisserver);
            events = events.add(newServe);
        }

        Pair<ImList<Event>, Update> update = new Pair<>(events,
                new Update(servers, autos, this.toString() + "\n", served, left,
                ttlTime));
        return update;
    }
}
