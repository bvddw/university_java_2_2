import java.util.Comparator;

public class Point implements Comparable<Point> {
    public float x;
    public float y;
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean isInCircle(Point center, float radius) {
        return (float) Math.sqrt((this.x - center.x) * (this.x - center.x) + (this.y - center.y) * (this.y - center.y)) <= radius;
    }

    @Override
    public String toString() {
        return "Point (" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point second_point = (Point) obj;
        return Float.compare(second_point.x, x) == 0 && Float.compare(second_point.y, y) == 0;
    }

    @Override
    public int compareTo(Point otherPoint) {
        if (Float.compare(this.x, otherPoint.x) == 0 && Float.compare(this.y, otherPoint.y) == 0) {
            return 0;
        }
        return 1;
    }
}