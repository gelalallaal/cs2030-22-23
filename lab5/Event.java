abstract class Event {
    private final double time;
    private final Customer cust;

    public Event(double time, Customer customer) {
        this.time = time;
        this.cust = customer;
    }

    public double getTime() {
        return this.time;
    }

    public Customer getCust() {
        return this.cust;
    }

    public abstract Pair<ImList<Event>, Update> run(ImList<Server> servers,
        ImList<Server> autos, int served, int left, int qmax, double ttlTime);
}
