class Serve extends Event {
    private final Server server;

    public Serve(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    private Event createDone(double time, Server server) {
        return new Done(time, super.getCust(), server);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d serves by %d", super.getTime(),
            super.getCust().getID(), this.server.id());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers, int served, int left,
        int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        String output = this.toString() + "\n";
        Server thisServer = servers.get(this.server.id() - 1);
        double deptTime = super.getTime() + super.getCust().getSTime().get();
        //if server has a queue make next serve
        if (thisServer.queue().size() > 1) {
            Customer nextCust = thisServer.queue().get(1);
            Serve newServe = new Serve(deptTime, nextCust, thisServer);
            events = events.add(newServe);
        }
        //update dept time and cust in server
        Server newServer = thisServer.nextCust(deptTime, super.getCust());
        servers = servers.set(thisServer.id() - 1, newServer);
        //make Done
        Event nextEv = this.createDone(deptTime, newServer);
        events = events.add(nextEv);
        //update total time waited
        double time = super.getTime() - super.getCust().getATime();
        return new Pair<ImList<Event>, Update>(events, new Update(servers,
            output, served + 1, left, ttlTime + time));
    }

}
