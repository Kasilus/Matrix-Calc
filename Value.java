import Jama.Matrix;

public class Value {

    private Object value;

    public Value(Object object) {
        this.value = object;
    }

    public boolean isMatrix() {
        return value instanceof Matrix;
    }

    public Matrix asMatrix() {
        return (Matrix) value;
    }

    public boolean isDouble() {
        return value instanceof Double;
    }

    public Double asDouble() {
        return (Double) value;
    }


}
