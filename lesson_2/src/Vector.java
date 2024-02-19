public class Vector {
    public int dim;
    public float[] coordinates;

    public Vector(int dim) {
        this.dim = dim;
        this.coordinates = new float[dim];
        for (int i = 0; i < dim; i++) {
            this.coordinates[i] = 0;
        }
    }

    public Vector(int dim, float[] coordinates) {
        this.dim = dim;
        this.coordinates = new float[dim];
        for (int i = 0; i < dim; i++) {
            this.coordinates[i] = coordinates[i];
        }
    }

    public Vector(Vector other) {
        this.dim = other.dim;
        this.coordinates = new float[dim];
        System.arraycopy(other.coordinates, 0, this.coordinates, 0, dim);
    }

    public float getCoordinate(int index) {
        if (index < 0 || index > dim) {
            throw new IllegalArgumentException("Index out of the range");
        }
        return coordinates[index];
    }

    public void setCoordinate(int index, float newCoordinate) {
        if (index < 0 || index > dim) {
            throw new IllegalArgumentException("Index out of the range");
        }
        coordinates[index] = newCoordinate;
    }

    public int getDimension() {
        return dim;
    }

    @Override
    public String toString() {
        String result = "(";
        for (float coordinate : coordinates) {
            result = result + coordinate + ", ";
        }
        result = result.substring(0, result.length() - 2); // Remove the extra comma and space
        result += ")";
        return result;
    }

    public float length() {
        float length = 0;
        for (float coordinate : coordinates) {
            length += (float) Math.pow(coordinate, 2);
        }
        return (float) Math.sqrt(length);
    }
}
