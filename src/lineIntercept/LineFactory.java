package lineIntercept;

public class LineFactory {
    public static Line generateLine(Point start, Point end){
        if (start.x < end.x) return new Line(start, end);
        if (start.y < end.y) return new Line(start, end);
        return new Line(end, start);
    }
}
