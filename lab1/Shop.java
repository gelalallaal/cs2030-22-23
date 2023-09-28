class Shop {
    private final int nServers;
    private final ImList<Double> aTimes;
    private final ImList<Double> sTimes;
    private final String output;
    
    public Shop(int n, ImList<Double> aTimes, ImList<Double> sTimes) {
        this.nServers = n;
        this.aTimes = aTimes;
        this.sTimes = sTimes;
        ImList<Server> servers = new ImList<Server>();
        String output = "";

        int serveCount = 0;
        int leaveCount = 0;

        // create servers
        for (int s = 1; s <= this.nServers; s++) { 
            servers = servers.add(new Server(s));
        }

        // process customers
        for (int s = 0; s < this.aTimes.size(); s++) {
            boolean served = false;
            double aTime = this.aTimes.get(s);
            Customer c = new Customer(s + 1, aTime, this.sTimes.get(s));
            output += format(aTime, c) + " arrives\n";
            // attempt to allocate customer to a server
            for (int i = 0; i < this.nServers; i++) {
                Server currServer = servers.get(i);
                if (currServer.getAvail(c)) {
                    // has server available
                    Server newServer = currServer.nextCustomer(c);
                    servers = servers.set(i, newServer);
                    output += format(aTime, c) + " served by " + currServer.toString() + "\n";
                    serveCount++;
                    served = true;
                    break;
                }
            }
            if (!served) {
                output += format(aTime, c) + " leaves\n";
                leaveCount++;
            }
        }
        output += "[" + serveCount + " " + leaveCount + "]";
        this.output = output;
    }

    public String format(double aTime, Customer c) {
        return String.format("%.3f", aTime) + " " + c.toString();
    }

    @Override
    public String toString() {
        return this.output;
    }
}
