package benchmark;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.Callable;

/**
 * TODO review this on the bytecode level
 * TODO add capturing lambda as well
 */
@State(Scope.Benchmark)
public class LambdaInvoker extends AbstractInvoker {

    private final Callable targetObjectMethod;

    public LambdaInvoker() {
        this.targetObjectMethod = targetObject::call;
    }

    @Override
    public Object call() throws Exception {
        return targetObjectMethod.call();
    }
}
