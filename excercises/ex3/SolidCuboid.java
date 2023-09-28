class SolidCuboid extends Cuboid implements Solid {
    private final SolidImpl impl;

    public SolidCuboid(double h, double w, double l, double d) {
        super(h, w, l);
        this.impl = new SolidImpl(new Cuboid(h, w, l), d);
    }

    @Override
    public double volume() {
        return super.volume();
    }

    @Override
    public double mass() {
        return impl.mass();
    }

    @Override
    public String toString() {
        return String.format(
                "solid-cuboid [%.2f x %.2f x %.2f] with a mass of %.2f",
                super.getHeight(), super.getWidth(), super.getLength(), this.mass());
    }
}
