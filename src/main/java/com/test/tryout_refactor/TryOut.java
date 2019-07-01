package com.test.tryout_refactor;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class MyRuntimeException extends RuntimeException {

    public MyRuntimeException(String message) {
        super(message);
    }
}

@AllArgsConstructor
@ToString
class StudentData {

    public String name;
    public String rollNumber;
    public boolean pass;

}


public class TryOut {

    LogWrapper logWrapper = new LogWrapper(TryOut.class.getName());


    private Map<String, StudentData> studentData;

    TryOut() {

        this.studentData = new HashMap<>() {
            {
                put("1234", new StudentData("Tom Cook", "1234", false));
                put("6785", new StudentData("John Snow", "6785", false));
                put("8955", new StudentData("Mickey Mouse", "8955", false));
            }
        };
    }

    Function<String, StudentData> getExamResult = rollNumber -> {

        try {

            return studentData.get(rollNumber);

        } catch (MyRuntimeException re) {

            throw logWrapper.logAndThrows("In runtime exception block", e -> new MyRuntimeException(e.getMessage())).apply(re);


        } catch (Exception exception) {


            throw logWrapper.logAndThrows(e -> new MyRuntimeException(e.getMessage())).apply(exception);
        }

    };

    public StudentData getExamResult(String rollNumber) {


        return logWrapper.logInputs(() -> "Method getExamResult", getExamResult).apply(rollNumber);


    }
}
