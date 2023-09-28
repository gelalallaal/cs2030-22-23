class Serve extends Event {
    private final Server server;

    public Serve(double time, Customer customer, Server s) {
        super(time, customer);
        this.server = s;
    }

    private Done createDone(double time, Server server) {
        return new Done(time, super.getCust(), server);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d serves by %s", super.getTime(),
            super.getCust().getID(), this.server.toString());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
        ImList<Server> autos, int served, int left, int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        String output = "";
        Customer thisCust = super.getCust();
        //check if server is auto
        int nServers = servers.size();
        //if auto
        if (this.server.id() > nServers) {
            //check if server is avail
            Server thisServer = autos.get(this.server.id() - nServers - 1);
            if (!thisServer.isAvail(super.getTime())) {
                Server thisserver = autos.get(0);
                for (int i = 1; i < autos.size(); i++) {
                    if (autos.get(i).deptTime() < thisserver.deptTime()) {
                        thisserver = autos.get(i);
                    }
                }
                Serve newServe = new Serve(thisserver.deptTime(),
                    thisCust, thisserver);
                events = events.add(newServe);
            } else {
                double deptTime = super.getTime() +
                    thisCust.getSTime().get();
                Server newServer = thisServer.nextCust(deptTime, thisCust);
                autos = autos.set(thisServer.id() - nServers - 1, newServer);
                Server newqueue = autos.get(0).nextCust(
                    autos.get(0).deptTime(), thisCust);
                autos = autos.set(0, newqueue);
                Done nextEv = this.createDone(deptTime, newServer);
                events = events.add(nextEv);
                double time = super.getTime() - thisCust.getATime();
                ttlTime += time;
                output = this.toString() + "\n";
                served++;
            }
        } else {
            Server thisServer = servers.get(this.server.id() - 1);
            if (!thisServer.isAvail(super.getTime())) {
                Serve newServe = new Serve(thisServer.deptTime(),
                    thisCust, thisServer);
                events = events.add(newServe);
            } else {
                output = this.toString() + "\n";
                double deptTime = super.getTime() +
                    thisCust.getSTime().get();
                //if server has a queue make next serve
                if (thisServer.queue().size() > 1) {
                    Customer nextCust = thisServer.queue().get(1);
                    Serve newServe = new Serve(deptTime, nextCust, thisServer);
                    events = events.add(newServe);
                }
                //update dept time and cust in server
                Server newServer = thisServer.nextCust(deptTime,thisCust);
                servers = servers.set(thisServer.id() - 1, newServer);
                //make Done
                Event nextEv = this.createDone(deptTime, newServer);
                events = events.add(nextEv);
                //update total time waited
                double time = super.getTime() - thisCust.getATime();
                ttlTime += time;
                served++;
            }
        }
        return new Pair<ImList<Event>, Update>(events, new Update(servers,
            autos, output, served, left, ttlTime));
    }

}
