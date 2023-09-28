import java.lang.Math;

class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "point (" + String.format("%.3f", this.x)
            + ", " + String.format("%.3f", this.y) + ")";
    }

    Point midPoint(Point p) {
        return new Point((this.x + p.x) / 2, (this.y + p.y) / 2);
    }

    double angleTo(Point p) {
        double h = Math.abs(this.y - p.y);
        double w = Math.abs(this.x - p.x);
        if (this.y > p.y && p.x > this.x) {
            return -Math.atan(h / w);
        } else if (p.y < this.y && p.x < this.x) {
            return -Math.atan(h / w) - Math.PI / 2;
        } else if (p.y > this.y && p.x > this.x) {
            return Math.atan(h / w);
        } else if (p.y > this.y && p.x < this.x) {
            return Math.atan(h / w) + Math.PI / 2;
        } else if (h == 0 && p.x < this.x) {
            return Math.PI;
        } else if (w == 0 && p.y > this.y) {
            return Math.PI / 2;
        } else if (w == 0 && p.y < this.y) {
            return -Math.PI / 2;
        } else {
            return 0.0;
        }
    }

    Point moveTo(double r, double d) {
        return new Point(this.x + d * Math.cos(r), this.y + d * Math.sin(r));
    }

    double distanceTo(Point p) {
        double dispX = this.x - p.x;
        double dispY = this.y - p.y;
        return Math.sqrt(dispX * dispX + dispY * dispY);
    }
}


