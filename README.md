JDK Method Invocation Benchmark
===============================

Invoking methods in Java is possible in a lot of ways, let's compare their execution times!

Benchmarks are made with [jmh](http://openjdk.java.net/projects/code-tools/jmh/)

# Usage

## Build and Run
```shell
  mvn clean package
  java -jar target/benchmark.jar
```

Or simply open the project in your favourite IDE (like IntelliJ) and run: ```org.openjdk.jmh.Main```


# Example Results
Check out how the standard reflection mechanism has improved between 1.7 and 1.8, see ReflectInvoker (earlier Caliper results):

- [JDK7](https://microbenchmarks.appspot.com/runs/247d2055-97a7-4a9b-88c4-1da824fb9c53)
- [JDK8](https://microbenchmarks.appspot.com/runs/b0804f03-0c93-40f8-90e4-b2439bb1daae)
