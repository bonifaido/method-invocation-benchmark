package benchmark;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.Callable;

@State(Scope.Benchmark)
public class StaticFinalMethodHandleInvoker extends AbstractInvoker {

    // Change this field from final and check the results then
    // See: http://www.jroller.com/thnagy/entry/methodhandle_vs_reflection_benchmark
    private static final MethodHandle targetMethodHandle;

    static {
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
