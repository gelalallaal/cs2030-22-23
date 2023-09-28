class Arrive extends Event {
    public Arrive(double time, Customer cust) {
        super(time, cust);
    }

    private Leave createLeave() {
        Leave leave = new Leave(super.getTime(), super.getCust());
        return leave;
    }

    private Serve createServe(Server s, int i) {
        Serve serve = new Serve(super.getTime(), super.getCust(), s, i);
        return serve;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives", super.getTime(),
            super.getCust().getID());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
            int served, int left) {
        ImList<Event> events = new ImList<Event>();
        boolean isServed = false;
        //check for available servers
        for (int i = 0; i < servers.size(); i++) {
            Server currServer = servers.get(i);
            if (currServer.getAvail(super.getCust())) {
                // has server available
                events = events.add(this.createServe(currServer, i));
                isServed = true;
                break;
            }
        }
        if (!isServed) {
            events = events.add(this.createLeave());
        }
        Pair<ImList<Event>, Update> update = new Pair<>(events, new Update(servers,
                    this.toString(), served, left));
        return update;
    }
}
