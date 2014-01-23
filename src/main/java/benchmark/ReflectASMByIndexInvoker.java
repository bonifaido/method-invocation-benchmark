package benchmark;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ReflectASMByIndexInvoker extends AbstractInvoker {

    private final MethodAccess methodAccess;
    private final int methodIndex;

    public ReflectASMByIndexInvoker() {
        this.methodAccess = MethodAccess.get(targetObject.getClass());
        methodIndex = methodAccess.getIndex("call");
    }

    @Override
    public Object call() throws Exception {
        return methodAccess.invoke(targetObject, methodIndex);
    }
}
