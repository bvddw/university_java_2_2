import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Iterator;
import java.util.TreeSet;

public class UsingTreeSet {
    public static void main(String[] args) {
        TreeSet<Point> points = new TreeSet();
        points.add(new Point(0,0));
        points.add(new Point(1,1));
        points.add(new Point(2,2));
        points.add(new Point(0, 1));

        for (Point p : points) {
            System.out.println(p);
        }

        Iterator<Point> iterator = points.iterator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter value 'x' and 'y' for deleting (format: x y):");
        float xToDelete = scanner.nextFloat();
        float yToDelete = scanner.nextFloat();
        while (iterator.hasNext()) {
            Point point = iterator.next();
            if (point.x == xToDelete && point.y == yToDelete) {
                iterator.remove();
            }
        }

        System.out.println("After removing:");
        for (Point p : points) {
            System.out.println(p);
        }
        System.out.println("Size of the treeset is: " + points.size());
        System.out.println("First item in treeset is: " + points.first());
        System.out.println("Last item in treeset is: " + points.last());
        System.out.println("Does treeset contain point (1, 1): " + points.contains(new Point(1, 1)));
        System.out.println("Does treeset contain point (2, 1): " + points.contains(new Point(2, 1)));
    }
}
