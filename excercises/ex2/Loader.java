class Loader {
    private final int id;

    public Loader(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Loader #" + this.id;
    }
}
