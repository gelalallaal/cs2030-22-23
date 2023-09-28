abstract class Server {
    private final int id;


    public Server(int id) {
        this.id = id;
    }

    public abstract double deptTime();

    public abstract Customer cust();

    public abstract Server nextCust(double time, Customer cust);

    public abstract Boolean isAvail(double time);

    public abstract Server addWait(Customer cust);

    public abstract Boolean canWait(int qmax);

    public abstract ImList<Customer> queue();

    //only human servers can rest
    public abstract Boolean rested();

    public abstract Server rest();

    public abstract Server endRest();

    public int id() {
        return this.id;
    }
}
