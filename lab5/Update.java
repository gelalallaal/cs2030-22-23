class Update {
    private final ImList<Server> servers;
    private final ImList<Server> autos;
    private final String output;
    private final int served;
    private final int left;
    private final double ttlTime;

    public Update(ImList<Server> servers, ImList<Server> autos, String output,
        int served, int left, double ttlTime) {
        this.servers = servers;
        this.autos = autos;
        this.output = output;
        this.served = served;
        this.left = left;
        this.ttlTime = ttlTime;
    }

    public ImList<Server> getServers() {
        return this.servers;
    }

    public ImList<Server> getAutos() {
        return this.autos;
    }

    public String getOutput() {
        return this.output;
    }

    public int getServed() {
        return this.served;
    }

    public int getLeft() {
        return this.left;
    }

    public double getTtlTime() {
        return this.ttlTime;
    }
}
