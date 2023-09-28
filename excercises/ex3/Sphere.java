class Sphere implements Shape3D {
    private final double radius;
    private static final double ratio = 4.0 / 3.0;
    private static final int power = 3;

    public Sphere(double r) {
        this.radius = r;
    }

    @Override
    public double volume() {
        return ratio * Math.PI * Math.pow(this.radius, power);
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public String toString() {
        return String.format("sphere [%.2f]", this.radius);
    }

}
