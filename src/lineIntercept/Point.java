package lineIntercept;

public class Point {
    public double x;
    public double y;

    public Point(double _x, double _y){
        x = _x;
        y = _y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) return false;
        var p = (Point)obj;
        return this.x == p.x && this.y == p.y;
    }
}
