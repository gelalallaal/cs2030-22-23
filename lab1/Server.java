class Server {
    private final int id;
    private final Customer customer;

    Server(int id) {
        this.id = id;
        this.customer = new Customer(0, 0, 0);
    }

    Server(int id, Customer c) {
        this.id = id;
        this.customer = c;
    }

    public int getID() {
        return this.id;
    }

    public boolean getAvail(Customer c) {
        if (this.customer.getID() == 0) {
            return true;
        }
        return this.customer.arriveAfter(c);
    }

    public Server nextCustomer(Customer c) {
        return new Server(this.id, c);
    }

    @Override
    public String toString() {
        return "server " + this.id;
    }
}
