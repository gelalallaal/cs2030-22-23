class Service {
    private final Loader loader;
    private final Cruise cruise;

    public Service(Loader loader, Cruise cruise) {
        this.loader = loader;
        this.cruise = cruise;
    }

    public Loader getLoader() {
        return this.loader;
    }

    public int getServiceStartTime() {
        return this.cruise.getArrivalTime();
    }

    public int getServiceEndTime() {
        return this.cruise.getArrivalTime() + this.cruise.getServiceTime();
    }

    @Override
    public String toString() {
        return this.loader.toString() + " serving " + this.cruise.toString();
    }
}
