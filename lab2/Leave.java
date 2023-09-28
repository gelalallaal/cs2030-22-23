class Leave extends Event {
    public Leave(double time, Customer cust) {
        super(time, cust);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves", super.getTime(), 
                super.getCust().getID());
    }

    @Override
    public Pair<ImList<Event>, Update> run(ImList<Server> servers, 
            int served, int left) {
        ImList<Event> events = new ImList<Event>();
        Pair<ImList<Event>, Update> update = new Pair<>(events,
                new Update(servers, this.toString(), served, left + 1));
        return update;
    }
}
