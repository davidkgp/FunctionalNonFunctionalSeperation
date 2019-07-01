package com.test.tryout_refactor;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LogWrapper {

    private final Logger logger;

    public LogWrapper(String className) {
        logger = Logger.getLogger(className);
    }

    public <E extends Throwable, E1 extends Throwable> Function<E, E1> logAndThrows(String message, Function<E, E1> mapException) {
        return exception -> {
            logger.log(Level.SEVERE, message, exception);
            return mapException.apply(exception);
        };
    }

    public <E extends Throwable, E1 extends Throwable> Function<E, E1> logAndThrows(Function<E, E1> mapException) {
        return exception -> {
            logger.log(Level.SEVERE, exception.getMessage(), exception);
            return mapException.apply(exception);
        };
    }


    public <Input, Output> Function<Input,Output> logInputs(Supplier<String> customMesageSupplier, Function<Input,Output> executionLogic) {
        return input -> {
            logger.info(customMesageSupplier.get()+" "+input.toString());
            Output output =  executionLogic.apply(input);
            logger.info(customMesageSupplier.get()+" "+output.toString());
            return output;
        };
    }


}
