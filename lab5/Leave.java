class Leave extends Event {

    public Leave(double time, Customer customer) {
        super(time, customer);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves", super.getTime(), super.getCust().getID());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers,
        ImList<Server> autos, int served, int left, int qmax, double ttlTime) {
        ImList<Event> events = new ImList<Event>();
        String output = this.toString() + "\n";
        return new Pair<ImList<Event>, Update>(events,
            new Update(servers, autos, output, served, left + 1, ttlTime));
    }
}
