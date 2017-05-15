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

    public boolean isInteger() {
        return value instanceof Integer;
    }

    public Integer asInteger() {
        return (Integer) value;
    }


}
