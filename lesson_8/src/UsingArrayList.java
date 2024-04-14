import java.util.ArrayList;
import java.util.Iterator;

public class UsingArrayList {
    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(0, 0));

        for (Point p : points) {
            System.out.println(p);
        }

        ArrayList<Point> points2 = new ArrayList<>();
        Iterator<Point> iterator = points.iterator();
        while (iterator.hasNext()) {
            Point point = iterator.next();
            if (!points2.contains(point)) {
                points2.add(point);
            } else {
                iterator.remove();
            }
        }
        System.out.println("After removing duplicates:");
        for (Point p : points) {
            System.out.println(p);
        }
        System.out.println("Size after removing duplicates: " + points.size());
        System.out.println("First element of the list: " + points.getFirst());
        System.out.println("Second element of the list: " + points.get(1));
        System.out.println("Last element of the list: " + points.getLast());
        System.out.println("Is the list empty: " + points.isEmpty());
    }
}