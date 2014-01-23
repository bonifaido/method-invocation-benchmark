package benchmark;

import java.util.concurrent.Callable;

abstract class AbstractInvoker implements Callable {
    protected final Callable targetObject = new Callable() {
        @Override
        public Object call() throws Exception {
            return "Hello World";
        }
    };
}
