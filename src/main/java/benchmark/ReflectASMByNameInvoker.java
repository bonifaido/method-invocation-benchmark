package benchmark;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ReflectASMByNameInvoker extends AbstractInvoker {

    private final MethodAccess methodAccess;

    public ReflectASMByNameInvoker() {
        this.methodAccess = MethodAccess.get(targetObject.getClass());
    }

    @Override
    public Object call() throws Exception {
        return methodAccess.invoke(targetObject, "call");
    }
}
