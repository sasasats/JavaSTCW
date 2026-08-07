package cucumber.utils;

import io.cucumber.java.ParameterType;

public class ParameterTypes {

    @ParameterType("male|female")
    public String sex(String sex) {
        return sex;
    }
}
