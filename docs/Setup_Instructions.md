# Setup Instructions

## JDK used

This project was compiled and tested with **OpenJDK 21**. JDK 17 or newer should also work because the source uses basic Java features available in those versions.

To check the Java installation, open a terminal and run:

```bash
java -version
javac -version
```

`java` should print the installed runtime version, and `javac` should print the compiler version. If `javac` is not found, install a JDK and configure the system `PATH` (and `JAVA_HOME` if your setup requires it).

## Run a Hello World program

1. Create a file named `HelloWorld.java`.
2. Add the following code:

   ```java
   public class HelloWorld {
       public static void main(String[] args) {
           System.out.println("Hello World");
       }
   }
   ```

3. In the folder containing the file, compile it:

   ```bash
   javac HelloWorld.java
   ```

4. Run it:

   ```bash
   java HelloWorld
   ```

Expected output:

```text
Hello World
```

The compiler creates `HelloWorld.class`. That file contains Java bytecode, which the JVM runs. This is a simple check that both the compiler and Java runtime are available.

## Compile LearnTrack

From the LearnTrack project root, use the commands in the main `README.md`. Compiled classes are written to the `out` folder, keeping them separate from the source code.
