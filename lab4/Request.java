class Request {
    private final int dist;
    private final int nPass;
    private final int time;

    public Request(int dist, int nPass, int time) {
        this.dist = dist;
        this.nPass = nPass;
        this.time = time;
    }

    public int computeFare(Service service) {
        return service.computeFare(this.dist, this.nPass, this.time);
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", this.dist, this.nPass,
                this.time);
    }
}
