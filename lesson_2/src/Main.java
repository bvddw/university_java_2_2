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
        Vector vector_1 = new Vector(3, coordinates);
        Vector vector_2 = vector_1;
        System.out.println("\nFirst vector is: " + vector_1);
        System.out.println("The copy of first vector is: " + vector_2);

        System.out.println("Length of the vector: " + vector_1.length());
        System.out.println("Third coordinate of vector is: " + vector_1.getCoordinate(1));
        System.out.println("The dimension of vector is " + vector_1.getDimension());
    }
}
