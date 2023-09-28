class Arrive extends Event {

    public Arrive(double time, Customer customer) {
        super(time, customer);
    }

    private Serve createServe(Server s) {
        return new Serve(super.getTime(), super.getCust(), s);
    }

    private Leave createLeave() {
        return new Leave(super.getTime(), super.getCust());
    }

    private Wait createWait(Server s) {
        return new Wait(super.getTime(), super.getCust(), s);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives", super.getTime(), super.getCust().getID());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers, int served, int left,
        int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        String output = this.toString() + "\n";
        //find first available server
        Boolean serviced = false;
        Boolean waited = false;
        for (int i = 0; i < servers.size(); i++) {
            Server currServer = servers.get(i);
            if (currServer.isAvail(super.getCust())) {
                Serve nextEv = this.createServe(currServer);
                serviced = true;
                events = events.add(nextEv);
                break;
            }
        }
        if (!serviced) {
            for (int i = 0; i < servers.size(); i++) {
                Server currServer = servers.get(i);
                if (currServer.queue().size() < qmax) {
                    Wait wait = this.createWait(currServer);
                    Server newServer = currServer.addWait(super.getCust());
                    servers = servers.set(i, newServer);
                    waited = true;
                    events = events.add(wait);
                    break;
                }
            }
            if (!waited) {
                Event leave = this.createLeave();
                events = events.add(leave);
            }
        }

        return new Pair<ImList<Event>, Update>(events,
            new Update(servers, output, served, left, ttlTime));
    }
}
