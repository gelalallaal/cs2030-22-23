class Customer {
    private final double arrival;
    private final double service;
    private final int id;

    Customer(int d, double a, double s) {
        this.arrival = a;
        this.service = s;
        this.id = d;
    }

    // return true if the provided customer arrives after
    // this customer departs.
    public boolean arriveAfter(Customer c) {
        return c.arrival >= this.getDepTime();
    }

    public double getDepTime() {
        return this.arrival + this.service;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return "customer " +  this.id;
    }
}
