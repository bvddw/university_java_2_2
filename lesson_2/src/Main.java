public class Main {
    public static void main(String[] args) {

        // testing zero vector
        Vector zeroVector = new Vector(5);
        System.out.println("Zero vector is: " + zeroVector);
        System.out.println("Length of zero vector is: " + zeroVector.length());
        System.out.println("Third coordinate of zero vector is: " + zeroVector.getCoordinate(3));
        System.out.println("The dimension of zero vector is " + zeroVector.getDimension());

        // testing other vector
        float[] coordinates = {3.0f, 4.0f, 5.0f};
        Vector vector1 = new Vector(coordinates);
        Vector vector2 = vector1;
        Vector vector_3 = new Vector(coordinates);
        System.out.println("\nFirst vector is: " + vector1);
        System.out.println("The copy of first vector is: " + vector2);

        System.out.println("Length of the vector: " + vector1.length());
        System.out.println("Third coordinate of vector is: " + vector1.getCoordinate(1));
        System.out.println("The dimension of vector is " + vector1.getDimension());
        System.out.println("Check the equals method: " + vector1 + " is the same as " + vector_3 + " is " + vector1.equals(vector_3));
    }
}
