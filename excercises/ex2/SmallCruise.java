class SmallCruise extends Cruise {
    private static final int SERVICE_TIME = 30;

    public SmallCruise(String id, int aTime) {
        super(id, aTime, 1, SERVICE_TIME);
    }
}
