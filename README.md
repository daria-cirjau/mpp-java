# Java Seminar

This repository contains the code and seminar materials used during the Java lab sessions.

```text
s01/   Introduction to Java, classes, objects, references, shallow vs deep copy, == vs equals
s02/   Arrays, bidimensional arrays, arrays of objects, Object methods, clone
s03/   Inheritance, interfaces, polymorphism, upcasting/downcasting, JUnit, JARs
s03_jars/  Small separate project used to test the generated JAR
s04/   Generics and Java Collections Framework (List, Set, Map, Iterator, Comparable)
s05/   Java I/O with primitive data and text files
s06/   Object serialization, AutoCloseable, finally, and an early introduction to lambdas
s07/   Java Streams API and functional-style data processing
s08/   Threads, Runnable, ExecutorService, Future/Callable, synchronization, virtual threads
```

# Seminar 01 — Java project setup, classes, objects, references, copying, equality

## What you learned

### 1\. Project and package structure

We created a Java project in IntelliJ, selected a JDK, and worked inside the `src` folder. We also created a package (`eu.ase.oop`) to group related classes.

### 2\. A basic Java class

The `Certificate` class models a simple object with two fields:

* an integer identifier
* a name/issuer field of type `String`

This class was used to practice:

* private fields
* constructors
* getters and setters
* encapsulation

### 3\. Objects, references, stack vs heap

The seminar focused on what happens when you write:

```java
Certificate c1 = new Certificate(...);
```

The important point is that the **object** is created in heap memory, while the **reference variable** is stored in stack memory. This is one of the key ideas that helps explain Java behavior later when we discuss copying, arrays, collections, and method calls.

### 4\. Shallow copy vs deep copy

We saw that assigning one reference to another:

```java
c2 = c1;
```

does **not** create a new object. It only copies the reference. As a result, both variables point to the same object.

Then we implemented a deep copy style method:

```java
public Certificate myClone() {
    Certificate newCert = new Certificate(this.id, this.name);
    return newCert;
}
```

This was an example of intentionally creating a **separate object** with the same content.

### 5\. `==` versus `equals()`

We also compared objects in two ways:

* `==` checks whether two references point to the same object
* `equals()` is meant to check logical/content equality

In `Certificate`, `equals()` was overridden so two certificates with the same field values could be considered equal.

# Seminar 02 — Arrays, multidimensional arrays, arrays of objects, `Object` methods, cloning

## Main goal

This seminar moved from single objects to collections of values and collections of objects. We worked first with primitive arrays, then with arrays of objects, and used that as context for `toString()`, `equals()`, `hashCode()`, and `clone()`.

## What you learned

### 1\. One-dimensional arrays

In `ProgMainArray` we practiced:

* array declaration
* array initialization
* default values
* direct indexing
* classic `for`
* enhanced `for`
* shallow copy of arrays
* deep copy using `Arrays.copyOf(...)`
* deep copy using `System.arraycopy(...)`
* passing arrays to methods

Arrays in Java are objects!

### 2\. Bidimensional arrays

In `ProgMainBidimensionalArrays` we used a 2D array to store marks for students across multiple disciplines. Then we computed average marks per student.

This example reinforced:

* matrix-like indexing with `\[row]\[column]`
* nested loops
* calculating aggregates from structured data

### 3\. Arrays of objects

In `ProgMainOOPArrays`, we created an array of `Student` objects.

### 4\. Designing the `Student` class

The `Student` class includes:

* a student name
* an array of marks
* an average mark field
* a static counter

This class was used to practice object-oriented design and also to reinforce how object fields can themselves contain references.

### 5\. Overriding important methods inherited from `Object`

We implemented and discussed:

* `toString()` for readable output
* `equals()` for logical equality
* `hashCode()` so it stays consistent with `equals()`
* `clone()` to copy a `Student`

The most important idea here is that `Student` contains a `short\[]` array, so cloning it requires more care than in Seminar 01. A shallow copy would copy only the reference to the marks array. To avoid that, the `marks` array is cloned separately.

# Seminar 03 — Inheritance, interfaces, polymorphism, JUnit, and JAR usage

## Main goal

This seminar introduced the core object-oriented mechanisms that make Java scalable: inheritance, interfaces, method overriding, polymorphism, casting, testing, and packaging code as a JAR.

## What you learned

### 1\. Interfaces

`Movement` is a simple interface declaring:

* `startEngine()`
* `stopEngine()`

This was our first example of an interface as a **contract**. It tells implementing classes what behavior they must provide.

### 2\. Base class and subclasses

`Vehicle` acts as the superclass. It stores the common `weight` field and provides common behavior. `Auto` and `Plane` extend `Vehicle`.

This introduced:

* `extends`
* superclass constructors via `super(...)`
* inherited behavior
* specialization through subclass fields and methods

### 3\. Method overriding

Both `Auto` and `Plane` override `display()` so that each type can display its own specific information.

This is where you start seeing that the same method call can behave differently depending on the real runtime type of the object.

### 4\. Polymorphism

A major point of the seminar was this style of code:

```java
Vehicle v;
v = a;
System.out.println(v.display());
v = p;
System.out.println(v.display());
```

Even though `v` is declared as `Vehicle`, the JVM calls the overridden method from the actual object (`Auto` or `Plane`).

This is runtime polymorphism and dynamic binding.

### 5\. Upcasting and downcasting

We discussed:

* upcasting: automatic conversion from subclass to superclass
* downcasting: explicit cast from superclass reference back to subclass

This is also where `ClassCastException` becomes meaningful: not every `Vehicle` is a `Plane`, even if every `Plane` is a `Vehicle`.

### 6\. Interfaces as reference types

Another important example was storing an `Auto` object in a variable of type `Movement`. This showed that:

* a reference of interface type is allowed
* only the methods declared in the interface can be accessed through that reference

### 7\. Cloning and marker interfaces

`Vehicle` implements `Cloneable`, and cloning is done through `super.clone()`. Because `weight` is primitive, shallow copy is enough in this case.

### 8\. Unit testing with JUnit

In the `testing` package, we created tests for `Auto`, especially for validating the `doorsNo` field.

This seminar introduced:

* `@Test`
* `Assert.assertEquals(...)`
* `Assert.fail(...)`
* the idea of testing expected failures
* grouping tests in a suite

### 9\. Creating and using a JAR

We also discussed how to export classes as a JAR and then use that JAR in a separate project (`s03\_jars`). This shows how Java code can be reused as a compiled library.

# Seminar 04 — Generics and the Java Collections Framework

## Main goal

This seminar replaced arrays with the standard collection types used in real Java applications. We learned why generics exist and how to work with `List`, `Set`, and `Map`.

## What you learned

### 1\. Why generics exist

Before generics, collections stored `Object`, which forced manual casting and moved errors to runtime. Generics move many such errors to compile time.

We practiced the difference between:

```java
List list = new ArrayList();
```

and

```java
List<String> list = new ArrayList<String>();
```

This makes code safer and easier to read.

### 2\. `List` and `ArrayList`

We created generic lists and used them to store `Plane` objects. Then we practiced:

* `add(...)`
* `size()`
* iterating with an `Iterator`
* iterating with enhanced `for`

### 3\. `Set` and uniqueness

You introduced `HashSet` and learned that sets do not keep duplicates.

This required a very important theoretical step: understanding why `equals()` and `hashCode()` must be implemented consistently. The `Plane` class overrides both so that two logically identical planes are treated as duplicates in a hash-based set.

### 4\. `Comparable` and ordered collections

The `Plane` class also implements `Comparable<Plane>` by comparing plane IDs.

This matters because ordered structures like `TreeSet` and `TreeMap` rely on comparison logic instead of hashing.

### 5\. `Map` and key-value modeling

We created a `Map<Plane, Country>`, associating each plane with a country. This introduced:

* key-value storage
* unique keys
* `put(...)`
* `get(...)`
* iteration through `keySet()`
* iteration through `entrySet()`

### 6\. Interface-as-type style

Another important concept in this seminar is declaring variables using the interface type:

```java
List<Plane> listPlanes = new ArrayList<>();
Map<Plane, Country> treeMap = new TreeMap<>();
```

This makes code more flexible because the concrete implementation can be changed later more easily.

## Why this seminar matters

The Java Collections Framework is used constantly in real projects. Understanding generics, iteration, hashing, ordering, and maps is essential for nearly all backend, desktop, and enterprise Java work.

## What to review after this seminar

* What `<T>`, `<E>`, `<K, V>` mean
* Why generic collections are safer than raw collections
* Difference between `List`, `Set`, and `Map`
* Why `HashSet` depends on `equals()` and `hashCode()`
* Why `TreeSet` and `TreeMap` depend on comparison rules
* Difference between iterating keys and iterating entries in a map

# Seminar 05 — Java I/O with binary files and text files

## Main goal

This seminar introduced Java input/output and showed how to write and read data from files, first as primitive values and strings, then using a more object-oriented design.

## What you learned

### 1\. Java streams for file operations

You worked with byte-based and character-based I/O using classes such as:

* `FileOutputStream`
* `BufferedOutputStream`
* `DataOutputStream`
* `FileInputStream`
* `BufferedInputStream`
* `DataInputStream`
* `FileWriter`
* `BufferedWriter`
* `FileReader`
* `BufferedReader`

### 2\. Writing primitive values and strings in binary format

In `ProgMainIo`, invoice-like data is stored in arrays (`prices`, `units`, `descs`) and written to a file using `DataOutputStream`.

The important lesson is that binary output stores data in an internal byte representation, not as human-readable plain text.

### 3\. Reading from binary files

We then read the same file back with `DataInputStream`, reconstructed the values in the same order they had been written, and computed the total invoice value.

This is also where `EOFException` was introduced as a signal that the end of the file has been reached.

### 4\. Writing and reading text files

The seminar also showed a text-based approach using `BufferedWriter` and `BufferedReader`. In the text file version, invoice rows are written as comma-separated values and later reconstructed using `split(",")`.

This helped us compare:

* binary storage
* text storage
* human readability versus structured binary data

### 5\. Refactoring into classes

Instead of keeping everything in `main`, the logic was moved into an `Invoice` class.

`Invoice` stores arrays and exposes methods that save invoice data to file and read it back to calculate totals. This is an important design step: moving behavior into the objects that own the data.

### 6\. Moving from parallel arrays to objects

Later in the seminar, invoice items were modeled with `InvoiceItem`, and `InvoiceWithItems` used a `List<InvoiceItem>` instead of maintaining three separate arrays.

## What to review after this seminar

* Difference between binary and text file storage
* Why read order must match write order
* Why buffered streams are useful
* Why `close()` matters
* How `EOFException` is used when reading until the end of a file
* Why `List<InvoiceItem>` is better than three parallel arrays in many cases

# Seminar 06 — Object serialization, resource management, `finally`, and lambda expressions

## Main goal

This seminar continued the I/O discussion, but moved to saving entire objects instead of individual primitive fields. It also introduced better resource management and gave you a first contact with lambda expressions.

## What you learned

### 1\. Serialization with `Serializable`

`ObjectsGraph` implements `Serializable`, which means an instance of the class can be transformed into a byte stream and saved to a file. `serialVersionUID` is used to help Java validate compatibility when deserializing objects.

### 2\. Saving object graphs

Instead of writing one primitive at a time, `ObjectsSave` uses `ObjectOutputStream` to save objects directly.

A very important conceptual point here is that Java serializes not only a single object, but also the object graph reachable through references. That is why object relationships can be preserved.

### 3\. Restoring objects and reference identity

`ObjectsRestore` reads the objects back using `ObjectInputStream` and checks whether shared references remain shared after deserialization. Java is not just saving values, but also preserving object relationships.

### 4\. Resource management with `AutoCloseable`

The seminar showed why manually calling `close()` can be fragile if an exception occurs before the call. That led to:

* `AutoCloseable`
* try-with-resources

We also created a small `MyResource` class to demonstrate automatic closing.

### 5\. `finally`

We discussed the role of `finally` in try-catch-finally and how it differs from normal code after the try/catch block.

### 6\. Functional interfaces and lambdas

The second part of the seminar introduced the `MathOperation` functional interface with one abstract method. Then `MainLambda` demonstrated multiple lambda-based implementations such as addition, subtraction, multiplication, and division.

This taught you:

* what a functional interface is
* lambda syntax
* type inference in lambda parameters
* passing behavior as a method argument

### 7\. Strategy-like behavior through lambdas

`MathOpClass.operate(...)` accepts a `MathOperation`, which means the caller decides which concrete behavior is applied.

## What to review after this seminar

* Difference between `DataOutputStream` and `ObjectOutputStream`
* What `Serializable` means
* What an object graph is
* Why try-with-resources is safer than manual close logic
* What a functional interface is
* Basic lambda syntax and how lambdas implement a single abstract method

# Seminar 07 — Java Streams API

## Main goal

This seminar built on lambdas and functional interfaces by introducing the Streams API, which lets you process collections in a more declarative way.

## What you learned

### 1\. Streams as processing pipelines

A stream is not a collection. It does not store data. Instead, it processes data coming from a source such as a list.

### 2\. Counting, filtering, and predicates

We started from a `List<String>` and counted empty strings in multiple ways:

* classic `for` loop
* enhanced `for`
* `stream().filter(...).count()`

This made it possible to compare imperative style with declarative style.

We also introduced `Predicate<String>` as a reusable functional object for conditions.

### 3\. Inline lambdas in stream operations

Instead of declaring a separate predicate, we also used inline lambdas such as:

```java
strings.stream().filter(s -> s.isEmpty()).count();
```

### 4\. Parallel streams

We explored `parallelStream()` and the idea that Java may process elements using multiple threads.

### 5\. Building new collections and merged output

We also used:

* `collect(Collectors.toList())`
* `Collectors.joining(...)`

This showed how streams can transform and aggregate data into useful final results.

### 6\. Mapping and distinct values

Using a list of integers, we created a list of distinct squares with:

* `map(...)`
* `distinct()`
* `collect(...)`

### 7\. Numeric statistics

Using `mapToInt(...).summaryStatistics()`, we computed:

* maximum
* minimum
* sum
* average

### 8\. Random numbers as streams

Finally, we saw that streams are not limited to lists: random values can also be generated, limited, sorted, and consumed via stream pipelines.

## Why this seminar matters

Streams are heavily used in modern Java codebases.

# Seminar 08 — Multithreading, executors, synchronization, futures, and virtual threads

## Main goal

This seminar introduced concurrency in Java: how multiple threads execute, how they can share data, what goes wrong without synchronization, and how Java provides higher-level concurrency tools.

## What you learned

### 1\. Creating threads in different ways

We created threads by:

* extending `Thread`
* implementing `Runnable`
* using an anonymous class
* using a lambda for `Runnable`

### 2\. `run()` versus `start()`

One of the most important beginner mistakes in threading is calling `run()` directly. The seminar clearly distinguished:

* `run()` = normal method call
* `start()` = request to start a new thread of execution

### 3\. Waiting with `join()`

We discussed `join()` and the idea that one thread can wait for another to finish.

### 4\. Using `ExecutorService`

Instead of manually creating many threads, `ExecutorService` lets you submit tasks to a managed pool.

In `MainThreads` you used:

* fixed thread pools
* `submit(...)`
* `shutdown()`
* `awaitTermination(...)`
* `shutdownNow()`

This is the practical, scalable way to manage many tasks.

### 5\. `Callable` and `Future`

Unlike `Runnable`, `Callable<V>` returns a value. The result is accessed through `Future<V>`.

This introduced:

* asynchronous task results
* blocking with `future.get()`
* exception handling for concurrent tasks

### 6\. Race conditions

`ThreadNonSync` demonstrates what happens when multiple threads modify shared static data without coordination. Because execution interleaves unpredictably, inconsistent output appears.

This is your an example of a race condition.

### 7\. Synchronization with a lock object

`ThreadSync` solves the shared-data problem by synchronizing on a common lock object.

This teaches the key idea of **mutual exclusion**: only one thread at a time is allowed to enter a protected critical section.

### 8\. Virtual threads

`VirtualThreadsPlayground` and the final part of `MainThreads` introduce virtual threads, a modern Java feature (JDK 21+) that allows many lightweight threads to be created much more efficiently than traditional platform threads.

## What to review after this seminar

* Difference between process and thread
* Difference between `Thread` and `Runnable`
* Difference between manual threads and executor-based execution
* Difference between `Runnable` and `Callable`
* What a `Future` represents
* What a race condition is
* How `synchronized` protects shared data
* Why virtual threads are important in newer Java versions

# How the seminars build on each other

Each seminar prepares the next one.

1. **S01** gives you the object/reference foundation.
2. **S02** extends that knowledge to arrays and object methods.
3. **S03** adds inheritance and polymorphism.
4. **S04** moves from arrays to generic collections.
5. **S05** shows how to move data between memory and files.
6. **S06** upgrades file work to object serialization and introduces lambdas.
7. **S07** uses lambdas in the Streams API.
8. **S08** introduces concurrent execution and more advanced modern Java features.

