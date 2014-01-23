package benchmark;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class DirectInvoker extends AbstractInvoker {
    @Override
    public Object call() throws Exception {
        return targetObject.call();
    }
}
