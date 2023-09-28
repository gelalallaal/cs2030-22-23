abstract class Driver {
    private final String carPlate;
    private final int wTime;

    public Driver(String carPlate, int wTime) {
        this.carPlate = carPlate;
        this.wTime = wTime;
    }

    public String getCarPlate() {
        return this.carPlate;
    }

    public int getWTime() {
        return this.wTime;
    }

    public abstract ImList<Service> chooseService(Request req);

}
