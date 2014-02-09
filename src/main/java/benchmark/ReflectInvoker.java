package benchmark;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.lang.reflect.Method;

@State(Scope.Benchmark)
public class ReflectInvoker extends AbstractInvoker {

    protected final Method method;

    public ReflectInvoker() {
        try {
            method = targetObject.getClass().getMethod("call");
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object call() throws Exception {
        return method.invoke(targetObject);
    }
}
