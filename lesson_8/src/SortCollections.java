import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortCollections {
    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 2));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(0, 0));

        System.out.println("Points: ");
        for (Point p : points) {
            System.out.println(p);
        }

        Comparator<Point> comparator = new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                int result = Float.compare(p1.x, p2.x);
                if (result == 0) {
                    result = Float.compare(p1.y, p2.y);
                }
                return result;
            }
        };
        points.sort(comparator);
        System.out.println("Sorted Points: ");
        for (Point p : points) {
            System.out.println(p);
        }

        System.out.println("Binary search in collections:");

        Point searchPoint = new Point(1, 2);
        int index = Collections.binarySearch(points, searchPoint, comparator);
        if (index >= 0) {
            System.out.println(searchPoint + " found: " + points.get(index) + " on position: " + index + 1);
        } else {
            System.out.println("Point not found.");
        }
        searchPoint = new Point(2, 5);
        index = Collections.binarySearch(points, searchPoint, comparator);
        if (index >= 0) {
            System.out.println("Point found: " + points.get(index));
        } else {
            System.out.println(searchPoint + " not found.");
        }
    }
}
