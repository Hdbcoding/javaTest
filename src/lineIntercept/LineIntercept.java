package lineIntercept;

public class LineIntercept {
    public static Point calculateInterception(Point s1, Point e1, Point s2, Point e2){
        LinePair lines = calculateLines(s1, e1, s2, e2);
        //same line - return the first point in the second segment, if that point is also in the first
        if (lines.line1.equals(lines.line2))
             return firstPointWithinSameLine(lines.line1, lines.line2);
        if (lines.line1.m == lines.line2.m) return null;
        return calculateInterception(lines.line1, lines.line2);
    }

    private static Point calculateInterception(Line line1, Line line2) {
        double x = (line2.b - line1.b) / (line1.m - line2.m);
        double y = x * line1.m + line1.b;
        Point intersection = new Point(x,y);
        if (isBetween(line1.start, intersection, line1.end)
            && isBetween(line2.start, intersection, line2.end)) return intersection;
        return null;
    }

    private static LinePair calculateLines(Point s1, Point e1, Point s2, Point e2) {
        Line _line1 = LineFactory.generateLine(s1, e1);
        Line _line2 = LineFactory.generateLine(s2, e2);
        //order the segments in the x dimension
        if (s1.x <= s2.x)            return new LinePair(_line1, _line2);
        return new LinePair(_line2, _line1);
    }

    private static Point firstPointWithinSameLine(Line line1, Line line2) {
        if (isBetween(line1.start, line2.start, line1.end)) return line2.start;
        if (isBetween(line2.start, line1.start, line2.end)) return line1.start;
        return null;
    }

    private static boolean isBetween(Point start, Point middle, Point end) {
        return isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y);
    }

    private static boolean isBetween(double start, double middle, double end){
        return Math.min(start, end) <= middle && middle <= Math.max(start,end);
    }

    static class LinePair { Line line1, line2; LinePair(Line l1, Line l2){ line1 = l1; line2 = l2; } }
}
