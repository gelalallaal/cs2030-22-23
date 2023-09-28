import java.util.function.Supplier;

class Customer {
    private final int id;
    private final double aTime;
    private final Supplier<Double> sTime;

    public Customer(int id, double aTime, Supplier<Double> sTime) {
        this.id = id;
        this.aTime = aTime;
        this.sTime = sTime;
    }

    public int getID() {
        return this.id;
    }

    public double getATime() {
        return this.aTime;
    }

    public Supplier<Double> getSTime() {
        return this.sTime;
    }
}
