import java.util.function.Supplier;

class HumanServer extends Server {
    private final ImList<Customer> queue;
    private final Supplier<Double> rest;
    private final Customer currentCust;
    private final double deptTime;
    private final double restTime;

    public HumanServer(int id, Supplier<Double> rest) {
        super(id);
        this.queue = new ImList<Customer>();
        this.rest = rest;
        this.currentCust = new Customer(0, 0, new DefaultServiceTime());
        this.deptTime = 0;
        this.restTime = 0;
    }

    public HumanServer(int id, ImList<Customer> queue, Customer currentCust,
        double deptTime, Supplier<Double> rest) {
        super(id);
        this.queue = queue;
        this.rest = rest;
        this.currentCust = currentCust;
        this.deptTime = deptTime;
        this.restTime = 0;
    }

    public HumanServer(int id, ImList<Customer> queue, Customer currentCust,
        double deptTime, double restTime, Supplier<Double> rest) {
        super(id);
        this.queue = queue;
        this.rest = rest;
        this.currentCust = currentCust;
        this.deptTime = deptTime + restTime;
        this.restTime = restTime;
    }

    public Boolean isAvail(double time) {
        if (this.rested()) {
            return false;
        } else if (currentCust.getID() == 0) {
            return true;
        } else if (this.deptTime <= time) {
            return true;
        } else {
            return false;
        }
    }

    public HumanServer nextCust(double deptTime, Customer c) {
        return new HumanServer(super.id(), this.queue.remove(0), c, deptTime, this.rest);
    }

    public HumanServer addWait(Customer c) {
        return new HumanServer(super.id(), this.queue.add(c), this.currentCust,
            this.deptTime, this.rest);
    }

    public Boolean canWait(int qmax) {
        if (this.rested()) {
            return false;
        } else if (this.queue.size() < qmax) {
            return true;
        } else {
            return false;
        }
    }

    public HumanServer rest() {
        double restTime = this.rest.get();
        if (restTime > 0) {
            return new HumanServer(super.id(), this.queue, this.currentCust,
                this.deptTime, restTime, this.rest);
        } else {
            return this;
        }
    }

    public HumanServer endRest() {
        return new HumanServer(super.id(), this.queue, this.currentCust, this.deptTime,
            this.rest);
    }

    public Boolean rested() {
        if (this.restTime > 0) {
            return true;
        } else {
            return false;
        }
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

    @Override
    public String toString() {
        return String.format("%d", this.id());
    }
}
