double epsilon = 1E-15;

Circle createUnitCircle(Point a, Point b) {
    Point mid = a.midPoint(b);
    double d = Math.sqrt(1 - Math.pow(a.distanceTo(mid),2)) ;
    double r = a.angleTo(b) + Math.PI/2;
    Point c = mid.moveTo(r, d);
    return new Circle(c, 1);
}

int findMaxDiscCoverage(ImList<Point> points) {
    int maxDiscCoverage = 0;
    int numOfPoints = points.size();

    for (int i = 0; i < numOfPoints - 1; i++) {
        for (int j = i + 1; j < numOfPoints; j++) {
            Point p = points.get(i);
            Point q = points.get(j);
            double distPQ = p.distanceTo(q);
            if (distPQ < 2.0 + epsilon && distPQ > 0) {
                Circle c = createUnitCircle(p,q);

                int coverage = 0;
                for (Point point : points) {
                    if (c.contains(point)) {
                        coverage = coverage + 1;
                    }
                }
                if (coverage > maxDiscCoverage) {
                    maxDiscCoverage = coverage;
                }
            }
        }
    }
    return maxDiscCoverage;
}
