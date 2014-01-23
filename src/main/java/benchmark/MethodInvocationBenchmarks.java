package benchmark;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;

/**
 * TODO include benchmarks for setting a single parameter
 */
@SuppressWarnings("unused")
public class MethodInvocationBenchmarks {

    @GenerateMicroBenchmark
    public Object benchmarkDirectInvoker(DirectInvoker invoker) throws Exception {
        return invoker.call();
    }

    @GenerateMicroBenchmark
    public Object benchmarkLambdaInvoker(LambdaInvoker invoker) throws Exception {
        return invoker.call();
    }

    @GenerateMicroBenchmark
    public Object benchmarkReflectInvoker(ReflectInvoker invoker) throws Exception {
        return invoker.call();
    }

    @GenerateMicroBenchmark
    public Object benchmarkMethodHandleInvoker(MethodHandleInvoker invoker) throws Exception {
        return invoker.call();
    }

    @GenerateMicroBenchmark
    public Object benchmarkStaticFinalMethodHandleInvoker(StaticFinalMethodHandleInvoker invoker) throws Exception {
        return invoker.call();
    }

    @GenerateMicroBenchmark
    public Object benchmarkReflectASMByIndexInvoker(ReflectASMByIndexInvoker invoker) throws Exception {
        return invoker.call();
    }

    @GenerateMicroBenchmark
    public Object benchmarkReflectASMByNameInvoker(ReflectASMByNameInvoker invoker) throws Exception {
        return invoker.call();
    }
}
