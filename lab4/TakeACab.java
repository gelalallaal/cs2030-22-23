class TakeACab extends Service {
    @Override
    public int computeFare(int dist, int nPass, int time) {
        return 33 * dist + 200;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}

