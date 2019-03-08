package training.busboard.exceptionUtil;

import java.util.function.Consumer;

public class LambdaWrappers {

    public interface ThrowingConsumer<T, E extends Exception> {
        void accept(T t) throws E;
    }

    public static <T> Consumer<T> tflExceptionLambdaWrapper(ThrowingConsumer<T, TflRequestException> consumer) {
        return (T t) -> {
            try {
                consumer.accept(t);
            } catch (TflRequestException e) {
                throw new TflRequestRuntimeException(e);
            }
        };
    }

    public static <T> Consumer<T> postcodeExceptionLambdaWrapper(ThrowingConsumer<T, PostcodeRequestException> consumer) {
        return (T t) -> {
            try {
                consumer.accept(t);
            } catch (PostcodeRequestException e) {
                throw new PostcodeRequestRuntimeException(e);
            }
        };
    }
}
