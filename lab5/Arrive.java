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
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
        ImList<Server> autos, int served, int left, int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        String output = this.toString() + "\n";
        Boolean serviced = false;
        Boolean waited = false;
        for (int i = 0; i < servers.size(); i++) {
            Server currServer = servers.get(i);
            if (currServer.isAvail(super.getTime())) {
                Serve nextEv = this.createServe(currServer);
                serviced = true;
                events = events.add(nextEv);
                break;
            }
        }

        if (!serviced && autos.size() > 0) {
            for (int i = 0; i < autos.size(); i++) {
                Server currServer = autos.get(i);
                if (currServer.isAvail(super.getTime())) {
                    Serve nextEv = this.createServe(currServer);
                    serviced = true;
                    events = events.add(nextEv);
                    break;
                }
            }
        }
        if (!serviced) {
            for (int i = 0; i < servers.size(); i++) {
                Server currServer = servers.get(i);
                if (currServer.canWait(qmax)) {
                    Wait wait = this.createWait(currServer);
                    Server newServer = currServer.addWait(super.getCust());
                    servers = servers.set(i, newServer);
                    waited = true;
                    events = events.add(wait);
                    break;
                }
            }
            if (!waited && autos.size() > 0) {
                if (autos.get(0).canWait(qmax)) {
                    Wait wait = this.createWait(autos.get(0));
                    Server newServer = autos.get(0).addWait(super.getCust());
                    autos = autos.set(0, newServer);
                    waited = true;
                    events = events.add(wait);
                }
            }
            if (!waited) {
                Event leave = this.createLeave();
                events = events.add(leave);
            }
        }

        return new Pair<ImList<Event>, Update>(events,
            new Update(servers, autos, output, served, left, ttlTime));
    }
}
