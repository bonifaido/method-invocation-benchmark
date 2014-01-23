package benchmark;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.lang.reflect.Method;

@State(Scope.Benchmark)
public class ReflectInvoker extends AbstractInvoker {

    private final Method method;

    public ReflectInvoker() {
        try {
            this.method = targetObject.getClass().getMethod("call");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object call() throws Exception {
        return method.invoke(targetObject);
    }
}
