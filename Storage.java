import Jama.Matrix;

import java.util.HashMap;

public class Storage {

    private static HashMap<String, Matrix> variables = new HashMap<>();

    static void set(String name, Matrix value){
        variables.put(name,value);
    }

    static Matrix get(String name){
        return variables.get(name);
    }

}
