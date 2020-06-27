# Quarkus-app project

This project uses [Quarkus](https://quarkus.io/), the Supersonic Subatomic Java Framework.
The objective is to study the Quarkus framework and weigh its pros and cons. I have used following dependencies:
- Spring-di - _Spring dependency injection_
- Spring-web - _Spring web annotations_
- Spring-boot properties
- Mongo-panache - _Spring data for MongoDB_

These were easy to use and not much different from regular Spring boot application. 
Also, I have following additional dependencies to evaluate their compatibility with Quarkus and it was seamless.
- commons-lang
- lombok
- mapstruct

## Running modes
Quarkus app can be started in two modes
 - JVM mode
 - Native image mode
 
### Prerequisites
A few of prerequisites are needed if you are trying to build the native image in the bare-metal machine
1. For instance, in **Windows**, you need to install [Visual studio's C++ Build Tools](https://visualstudio.microsoft.com/visual-cpp-build-tools/) and
 run `mvn clean package -Pnative` by using the `vcvars64.bat` in the `C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\VC\Auxiliary\Build`.
2. GraalVM - [graalvm-ce-java11-20.1.0](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-20.1.0)
3. After installing GraalVM and setting necessary env variable run `gu install native-image` to download and install native image executables for your operating system.
 
### Running the application in the JVM mode

You can run your application in **dev** mode that enables live coding using:
```
mvn quarkus:dev
```
You can run your application in **production** mode
1. The application can be packaged using:
```
mvn clean package
```
It produces the `quarkus-app-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware it’s not an _uber-jar_ as the dependencies are copied into the `target/lib` directory.

Spring boot v2.3.x  in now using this type of packing, as this significantly reduce time required to build a docker image as it creates a cache layer for jars in lib folder.

The application is now runnable using `java -jar target/quarkus-app-1.0.0-SNAPSHOT-runner.jar`.


### Running the application in the Native image mode

You can create a native executable using: 
```
mvn clean package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```
mvn package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-app-1.0.0-SNAPSHOT-runner`

## Docker
To free yourself from the above tedious process, I have create `Dockerfile.jvm` and `Dockerfile.native` to abstract the process for building the jar or native executables.
```
docker build -f <Dockerfile-path> -t <image-name> .
```

_Usually building native image in bare metal takes from 5 to 10 minutes as the process is both memory hungry and CPU intensive.
This makes even worse when you are trying to build in a docker image, so I recommend increasing the CPU and RAM for Windows Docker desktop using settings and increasing the docker process priority for other operating systems_

## Observations

### Startup time
As promised, Quarkus application was able to start in less than 100ms, in the native image and production mode making it perfect
for a **serverless** application. And roughly within a second while using it dev mode.

### Docker image size
In native image mode, you get roughly 150MB image size, in which executable size is about 50MB.

### Memory consumption
Native images exhibit very low memory footprint. Just 100MB of RAM is needed for the docker container.
JVM mode is similar to the Spring boot application's memory requirement. Here, it required 512MB of RAM.

### Performance
It was quite a disappointment with the native image mode, it exhibited poor performance when compared to JVM mode.
Although, GraalVM was able to introduce _ahead-of-time_ compilation, it is no match with JVM JIT compiler. Please refer the [Quarkus_benchmark.xlsx](https://github.com/rajasushanth/quarkus-app/blob/master/Quarkus_benchmark.xlsx) spreadsheet for the benchmark results.

On the contrary, Quarkus application in the JVM mode was able to easily surpass the benchmark of the spring boot.
In fact, it managed to give nearly twice the throughput as in similar spring boot application.

## Conclusion
- Quarkus with GraalVM's native image managed to out perform JVM in memory consumption and start up time segment but their is a significant dip in the performance.
- Use native image mode for a short lived, serverless applications
- Use JVM mode for a long lived, JVM applications.
