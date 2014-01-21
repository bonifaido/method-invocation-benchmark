JDK Method Invocation Benchmark
===============================

Invoking methods in Java is possible in a lot of ways, let's compare their execution times!

# Usage

## Build and Run
```shell
  mvn clean package
  java -jar target/benchmark.jar
```

Or simply open the project in your favourite IDE (like IntelliJ) and run: ```benchmark.MethodInvocationBenchmarkMain```

## Open the results

The last line (using [Caliper](https://code.google.com/p/caliper/)) will print something like this, open the link in your browser:
> Results have been uploaded. View them at: https://microbenchmarks.appspot.com/runs/[GENERATED_UUID]


# Example Results
Check out how the standard reflection mechanism has improved between 1.7 and 1.8, see ReflectInvoker:

- [JDK7](https://microbenchmarks.appspot.com/runs/247d2055-97a7-4a9b-88c4-1da824fb9c53)
- [JDK8](https://microbenchmarks.appspot.com/runs/b0804f03-0c93-40f8-90e4-b2439bb1daae)
