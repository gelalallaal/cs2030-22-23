class RecycledLoader extends Loader {

    public RecycledLoader(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Recycled Loader #" + super.getId();
    }
}
