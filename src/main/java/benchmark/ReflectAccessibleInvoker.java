package benchmark;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ReflectAccessibleInvoker extends ReflectInvoker {
    public ReflectAccessibleInvoker() {
        super();
        method.setAccessible(true);
    }
}
