import java.lang.Math;

class BigCruise extends Cruise {
    private final int nPass;
    private final int lenCruise;
    private static final int STIME_BY_NPASS = 50;
    private static final int LOADERS_BY_LEN = 40;

    public BigCruise(String id, int aTime, int lenCruise, int nPass) {
        super(id, aTime, 0, 0);
        this.nPass = nPass;
        this.lenCruise = lenCruise;
    }

    @Override
    public int getServiceTime() {
        return (int) Math.ceil((double) this.nPass / STIME_BY_NPASS);
    }

    @Override
    public int getNumOfLoadersRequired() {
        return (int) Math.ceil((double) this.lenCruise / LOADERS_BY_LEN);
    }
}
