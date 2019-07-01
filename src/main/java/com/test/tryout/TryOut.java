package com.test.tryout;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class MyRuntimeException extends RuntimeException {

    public MyRuntimeException(String message) {
        super(message);
    }
}

@AllArgsConstructor
class StudentData {

    public String name;
    public String rollNumber;
    public boolean pass;

}

public class TryOut {

    final static Logger logger = Logger.getLogger(TryOut.class.getName());


    private final Map<String, StudentData> studentData;

    TryOut() {

        this.studentData = new HashMap<>() {
            {
                put("1234", new StudentData("Tom Cook", "1234", false));
                put("6785", new StudentData("John Snow", "6785", false));
                put("8955", new StudentData("Mickey Mouse", "8955", false));
            }
        };
    }

    public StudentData getExamResult(String rollNumber) {

        logger.info("call method getExamResult with rollNumber "+rollNumber);

        try {

            return studentData.get(rollNumber);

        } catch (MyRuntimeException re) {

            logger.log(Level.SEVERE,"In runtime exception block", re);

            throw new MyRuntimeException(re.getMessage());

        } catch(Exception exception){

            logger.log(Level.SEVERE,"In exception block", exception);

            throw new MyRuntimeException(exception.getMessage());
        }

    }
}
