class SolidImpl implements Shape3D, Solid {
    private final Shape3D shape;
    private final double density;

    public SolidImpl(Shape3D shape, double density) {
        this.shape = shape;
        this.density = density;
    }

    public double mass() {
        return this.shape.volume() * this.density;
    }

    public double density() {
        return this.density;
    }

    public Shape3D shape() {
        return this.shape;
    }

    public double volume() {
        return this.shape.volume();
    }

    @Override
    public String toString() {
        return "SolidImpl";
    }

}
