class JustRide extends Service {
    @Override
    public int computeFare(int dist, int nPass, int time) {
        if (time >= 600 && time <= 900) {
            return 22 * dist + 500;
        } else {
            return 22 * dist;
        }
    }

    @Override
    public String toString() {
        return "JustRide";
    }
}
