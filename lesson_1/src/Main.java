import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number of points in array: ");
        int number = scanner.nextInt();
        Point[] points = new Point[number];
        for (int i = 0; i < points.length; i++) {
            float rX = r.nextInt(0, 10);
            float rY = r.nextInt(0, 10);
            points[i] = new Point(rX, rY);
        }
        System.out.println("List of points: ");
        for (Point point : points) {
            System.out.println(point);
        }

        // Задання центру та радіусу кола
        Point center = new Point(1, 2);
        float radius = 5;

        System.out.println("List of points in a circle: ");
        for (Point point : points) {
            if (point.isInCircle(center, radius)) {
                System.out.println(point);
            }
        }

        System.out.print("Enter an x coordinate: ");
        int newX = scanner.nextInt();
        System.out.print("Enter an y coordinate: ");
        int newY = scanner.nextInt();
        Point newPoint = new Point(newX, newY);
        int count = 0;
        for (Point point : points) {
            if (point.equals(newPoint)) {
                count++;
            }
        }
        System.out.println("Number of points, equal to given: " + count);
    }
}
