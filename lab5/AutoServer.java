class AutoServer extends Server {
    private final ImList<Customer> queue;
    private final Customer currentCust;
    private final double deptTime;

    public AutoServer(int id) {
        super(id);
        this.queue = new ImList<Customer>();
        this.currentCust = new Customer(0, 0, new DefaultServiceTime());
        this.deptTime = 0;
    }

    public AutoServer(int id, ImList<Customer> queue, Customer currentCust, double deptTime) {
        super(id);
        this.queue = queue;
        this.currentCust = currentCust;
        this.deptTime = deptTime;
    }

    @Override
    public Boolean isAvail(double time) {
        if (this.currentCust.getID() == 0) {
            return true;
        } else if (this.deptTime <= time) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean canWait(int qmax) {
        if (this.queue.size() < qmax) {
            return true;
        } else {
            return false;
        }
    }

    public AutoServer nextCust(double deptTime, Customer c) {
        if (this.queue.size() == 0) {
            return new AutoServer(this.id(), new ImList<Customer>(), c, deptTime);
        } else if (this.queue.get(0).getID() == c.getID()) {
            return new AutoServer(this.id(), this.queue.remove(0), c, deptTime);
        } else {
            return new AutoServer(this.id(), this.queue, c, deptTime);
        }
    }

    public AutoServer addWait(Customer c) {
        return new AutoServer(this.id(), this.queue.add(c), this.currentCust,
            this.deptTime);
    }

    public ImList<Customer> queue() {
        return this.queue;
    }

    public int id() {
        return super.id();
    }

    public double deptTime() {
        return this.deptTime;
    }

    public Customer cust() {
        return this.currentCust;
    }

    public Boolean rested() {
        return false;
    }

    public Server rest() {
        return this;
    }

    public Server endRest() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("self-check %d", this.id());
    }
}
