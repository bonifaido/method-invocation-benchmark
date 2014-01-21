package benchmark;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;


class DirectInvoker<T> implements Callable<T> {

    private final Callable<T> targetObject;

    public DirectInvoker(Callable<T> targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public T call() throws Exception {
        return targetObject.call();
    }
}

class ReflectInvoker<T> implements Callable<T> {

    private final Callable<T> targetObject;
    private final Method method;

    public ReflectInvoker(Callable<T> targetObject) throws Exception {
        this.targetObject = targetObject;
        this.method = targetObject.getClass().getMethod("call");
    }

    @Override
    public T call() throws Exception {
        return (T) method.invoke(targetObject);
    }
}

class MethodHandleInvoker<T> implements Callable<T> {

    private final MethodHandle targetMethodHandle;

    public MethodHandleInvoker(Callable<T> targetObject) throws Exception {
        MethodType methodType = MethodType.methodType(Object.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(Callable.class, "call", methodType);
        this.targetMethodHandle = MethodHandles.insertArguments(methodHandle, 0, targetObject);
    }

    @Override
    public T call() throws Exception {
        try {
            return (T) targetMethodHandle.invokeExact();
        } catch (Throwable throwable) {
            throw new Exception(throwable);
        }
    }
}

class StaticFinalMethodHandleInvoker<T> implements Callable<T> {

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

    private final Callable<T> targetObject;

    public StaticFinalMethodHandleInvoker(Callable<T> targetObject) throws Exception {
        this.targetObject = targetObject;
    }

    @Override
    public T call() throws Exception {
        try {
            return (T) targetMethodHandle.invokeExact(targetObject);
        } catch (Throwable throwable) {
            throw new Exception(throwable);
        }
    }
}

class ReflectASMByNameInvoker<T> implements Callable<T> {

    private final Callable<T> targetObject;
    private final MethodAccess methodAccess;

    public ReflectASMByNameInvoker(Callable<T> targetObject) throws Exception {
        this.targetObject = targetObject;
        this.methodAccess = MethodAccess.get(targetObject.getClass());
    }

    @Override
    public T call() throws Exception {
        return (T) methodAccess.invoke(targetObject, "call");
    }
}

class ReflectASMByIndexInvoker<T> implements Callable<T> {

    private final Callable<T> targetObject;
    private final MethodAccess methodAccess;
    private final int methodIndex;

    public ReflectASMByIndexInvoker(Callable<T> targetObject) throws Exception {
        this.targetObject = targetObject;
        this.methodAccess = MethodAccess.get(targetObject.getClass());
        methodIndex = methodAccess.getIndex("call");
    }

    @Override
    public T call() throws Exception {
        return (T) methodAccess.invoke(targetObject, methodIndex);
    }
}

// TODO include benchmarks for setting a single parameter
@SuppressWarnings("unused")
public class MethodInvocationBenchmarkMain extends Benchmark {

    private final Callable<String> targetObject = new Callable<String>() {
        @Override
        public String call() throws Exception {
            return "Hello World";
        }
    };

    public static void main(String[] args) {
        CaliperMain.main(MethodInvocationBenchmarkMain.class, args);
    }

    public void timeDirectInvoker(long reps) throws Exception {
        DirectInvoker<String> invoker = new DirectInvoker<>(targetObject);
        for (long i = 0; i < reps; i++) {
            invoker.call();
        }
    }

    public void timeReflectInvoker(long reps) throws Exception {
        ReflectInvoker<String> invoker = new ReflectInvoker<>(targetObject);
        for (long i = 0; i < reps; i++) {
            invoker.call();
        }
    }

    // reps above 1000000 shows interesting results, MH becomes slower than Method
    // under that MH is about two times faster
    public void timeMethodHandleInvoker(long reps) throws Exception {
        MethodHandleInvoker<String> invoker = new MethodHandleInvoker<>(targetObject);
        for (long i = 0; i < reps; i++) {
            invoker.call();
        }
    }

    public void timeStaticFinalMethodHandleInvoker(long reps) throws Exception {
        StaticFinalMethodHandleInvoker<String> invoker = new StaticFinalMethodHandleInvoker<>(targetObject);
        for (long i = 0; i < reps; i++) {
            invoker.call();
        }
    }

    public void timeReflectASMByNameInvoker(long reps) throws Exception {
        ReflectASMByNameInvoker<String> invoker = new ReflectASMByNameInvoker<>(targetObject);
        for (long i = 0; i < reps; i++) {
            invoker.call();
        }
    }

    public void timeReflectASMByIndexInvoker(long reps) throws Exception {
        ReflectASMByIndexInvoker<String> invoker = new ReflectASMByIndexInvoker<>(targetObject);
        for (long i = 0; i < reps; i++) {
            invoker.call();
        }
    }
}
