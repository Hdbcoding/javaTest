package lineIntercept;

public class Line {
    public double m;
    public double b;
    public double x;
    public boolean isVertical;

    public Point start;
    public Point end;

    public Line(Point s, Point e){
        double dx = e.x - s.x;
        if (dx == 0){
            isVertical = true;
            x = e.x;
        }
        m = (e.y - s.y) / (dx);
        b = e.y - m * e.x;
        start = s;
        end = e;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Line)) return false;
        var line2 = (Line)obj;

        return this.m == line2.m
                && (this.b == line2.b || Double.isNaN(this.b) && Double.isNaN(line2.b))
                && this.x == line2.x
                && this.isVertical == line2.isVertical;
    }
}
