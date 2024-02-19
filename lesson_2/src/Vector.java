import java.lang.annotation.Documented;

public class Vector {
    public float[] coordinates;

    public Vector(int dim) {
        this.coordinates = new float[dim];
        for (int i = 0; i < dim; i++) {
            this.coordinates[i] = 0;
        }
    }

    public Vector(float[] coordinates) {
        this.coordinates = new float[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            this.coordinates[i] = coordinates[i];
        }
    }

    public Vector(Vector other) {
        this.coordinates = new float[other.coordinates.length];
        System.arraycopy(other.coordinates, 0, this.coordinates, 0, other.coordinates.length);
    }

    public float getCoordinate(int index) {
        return coordinates[index];
    }

    public void setCoordinate(int index, float newCoordinate) {
        coordinates[index] = newCoordinate;
    }

    public int getDimension() {
        return this.coordinates.length;
    }

    public float length() {
        float length = 0;
        for (float coordinate : coordinates) {
            length += (float) Math.pow(coordinate, 2);
        }
        return (float) Math.sqrt(length);
    }

    @Override
    public String toString() {
        String result = "(";
        for (float coordinate : coordinates) {
            result = result + coordinate + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += ")";
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector vector2 = (Vector) obj;
        for (int i = 0; i < this.getDimension(); i++) {
            if (this.getCoordinate(i) != vector2.getCoordinate(i))
                return false;
        }
        return true;
    }
}
