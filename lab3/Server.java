import java.util.function.Supplier;

class Server {
    private final int id;
    private final ImList<Customer> queue;
    private final Customer currentCust;
    private final double deptTime;

    public Server(int id) {
        this.id = id;
        this.queue = new ImList<Customer>();
        this.currentCust = new Customer(0, 0, new DefaultServiceTime());
        this.deptTime = 0;
    }

    public Server(int id, ImList<Customer> queue, Customer currentCust, double deptTime) {
        this.id = id;
        this.queue = queue;
        this.currentCust = currentCust;
        this.deptTime = deptTime;
    }

    public Boolean isAvail(Customer c) {
        if (currentCust.getID() == 0) {
            return true;
        } else if (this.deptTime <= c.getATime()) {
            return true;
        } else {
            return false;
        }
    }

    public Server nextCust(double deptTime, Customer c) {
        return new Server(this.id, this.queue.remove(0), c, deptTime);
    }

    public Server addWait(Customer c) {
        return new Server(this.id, this.queue.add(c), this.currentCust,
            this.deptTime);
    }

    public ImList<Customer> queue() {
        return this.queue;
    }

    public int id() {
        return this.id;
    }

    public double deptTime() {
        return this.deptTime;
    }
}
