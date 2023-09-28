class Update {
    private final ImList<Server> servers;
    private final String output;
    private final int served;
    private final int left;

    public Update(ImList<Server> servers, String output, int served, int left) {
        this.servers = servers;
        this.output = output;
        this.served = served;
        this.left = left;
    }

    public ImList<Server> getServers() {
        return this.servers;
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
}
