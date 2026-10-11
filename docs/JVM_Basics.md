# JVM Basics

## JDK, JRE and JVM

The **JVM (Java Virtual Machine)** runs Java bytecode. It takes care of running the program on the computer's operating system. The JVM also manages memory while the program runs.

The **JRE (Java Runtime Environment)** is the runtime part needed to run Java applications: it includes the JVM and supporting libraries. The **JDK (Java Development Kit)** is used to develop Java applications. It includes tools such as the Java compiler (`javac`) and the tools needed to run programs. For this project, install a JDK because we need to compile the `.java` files as well as run them.

## What is bytecode?

When `javac` compiles a Java source file such as `Main.java`, it produces a `.class` file containing bytecode. Bytecode is an intermediate set of instructions for the JVM; it is not normally compiled directly into machine code for one specific operating system by the Java compiler.

## What does "write once, run anywhere" mean?

Java source code is compiled into bytecode. The same compiled bytecode can run on different operating systems as long as a compatible JVM is available for each system. For example, a Java application can run on Windows, macOS or Linux without changing its source code just for each operating system.

This does not mean every Java program runs everywhere without any conditions. It still needs a compatible Java version, and code that relies on operating-system-specific files, commands or native libraries may need changes.
