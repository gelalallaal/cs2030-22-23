import java.util.Comparator;

class Booking implements Comparable<Booking> {
    private Driver driver;
    private Request request;

    public Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
    }

    public Integer fare() {
        return this.request.computeFare(this.driver.chooseService(this.request).get(0));
    }

    public Driver driver() {
        return this.driver;
    }

    @Override
    public String toString() {
        return String.format("$%.2f using %s (%s)", this.fare() / 100,
        this.driver.toString(), this.driver.chooseService(this.request).get(0).toString());
    }

    @Override
    public int compareTo(Booking other) {
        if (this.fare() == other.fare()) {
            if (this.driver.getWTime() == other.driver.getWTime()) {
                return 0;
            } else if (this.driver.getWTime() < other.driver().getWTime()) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.fare() < other.fare()) {
            return -1;
        } else {
            return 1;
        }
    }
}
