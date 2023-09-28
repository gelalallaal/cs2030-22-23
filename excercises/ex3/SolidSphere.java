class SolidSphere extends Sphere implements Solid {
    private final SolidImpl impl;

    public SolidSphere(double r, double d) {
        super(r);
        this.impl = new SolidImpl(new Sphere(r), d);
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
        return String.format("solid-sphere [%.2f] with a mass of %.2f",
        super.getRadius(), this.mass());
    }
}
