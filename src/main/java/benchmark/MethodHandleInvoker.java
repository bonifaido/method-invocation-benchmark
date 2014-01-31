package benchmark;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.Callable;

/**
 * On JDK7 repetitions above 1000000 shows interesting results, MH becomes slower than Method
 * under that MH is about two times faster
 */
@State(Scope.Benchmark)
public class MethodHandleInvoker extends AbstractInvoker {

    private final MethodHandle targetMethodHandle;

    public MethodHandleInvoker() {
        MethodType methodType = MethodType.methodType(Object.class);
        try {
            targetMethodHandle = MethodHandles.lookup().findVirtual(Callable.class, "call", methodType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object call() throws Exception {
        try {
            return targetMethodHandle.invokeExact(targetObject);
        } catch (Throwable throwable) {
            throw new Exception(throwable);
        }
    }
}
