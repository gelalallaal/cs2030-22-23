class Cuboid implements Shape3D {
    private final double height;
    private final double width;
    private final double length;

    public Cuboid(double h, double w, double l) {
        this.height = h;
        this.width = w;
        this.length = l;
    }

    @Override
    public double volume() {
        return this.height * this.width * this.length;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getLength() {
        return this.length;
    }

    @Override
    public String toString() {
        return String.format("cuboid [%.2f x %.2f x %.2f]",
                this.height, this.width, this.length);
    }
}
