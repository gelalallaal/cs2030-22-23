import java.lang.Math;

class Cruise {
    private final String id;
    private final String aTime;
    private final int sTime;
    private final int nLoad;
    private static final int MINS_PER_HOUR = 60;

    public Cruise(String id, int aTime, int nLoad, int sTime) {
        this.id = id;
        this.aTime =  String.format("%04d", aTime);
        this.nLoad = nLoad;
        this.sTime = sTime;
    }

    public int getServiceTime() {
        return this.sTime;
    }

    public int getArrivalTime() {
        // 24hr to mins
        return Integer.parseInt(this.aTime) % 100 +
                (int) Math.floor(Integer.parseInt(this.aTime) / 100) * MINS_PER_HOUR;
    }

    public int getNumOfLoadersRequired() {
        return this.nLoad;
    }

    @Override
    public String toString() {
        return this.id + "@" + this.aTime;
    }
}
