package ca.jrvs.apps.practice;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc{

  /**
   * Create a String stream from array
   * <p>
   * note: arbitrary number of value will be stored in an array
   *
   * @param strings
   * @return
   */
  @Override
  public Stream<String> createStrStream(String... strings) {
    return Stream.of(strings);
  }

  /**
   * Convert all strings to uppercase please use createStrStream
   *
   * @param strings
   * @return
   */
  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings).map(string -> string.toUpperCase());
  }

  /**
   * filter strings that contains the pattern e.g. filter(stringStream, "a") will return another
   * stream which no element contains a
   *
   * @param stringStream
   * @param pattern
   * @return
   */
  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(s -> !s.contains(pattern));
  }

  /**
   * Create a intStream from a arr[]
   *
   * @param arr
   * @return
   */
  @Override
  public IntStream createIntStream(int[] arr) {
    return IntStream.of(arr);
  }

  /**
   * Convert a stream to list
   *
   * @param stream
   * @return
   */
  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  /**
   * Convert a intStream to list
   *
   * @param intStream
   * @return
   */
  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  /**
   * Create a IntStream range from start to end inclusive
   *
   * @param start
   * @param end
   * @return
   */
  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.rangeClosed(start, end);
  }

  /**
   * Convert a intStream to a doubleStream and compute square root of each element
   *
   * @param intStream
   * @return
   */
  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.mapToDouble(Math::sqrt);
  }

  /**
   * filter all even number and return odd numbers from a intStream
   *
   * @param intStream
   * @return
   */
  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(x-> (x&1)==1);
  }

  /**
   * Return a lambda function that print a message with a prefix and suffix This lambda can be
   * useful to format logs
   * <p>
   * You will learn: - functional interface http://bit.ly/2pTXRwM & http://bit.ly/33onFig - lambda
   * syntax
   * <p>
   * e.g. LambdaStreamExc lse = new LambdaStreamImp(); Consumer<String> printer =
   * lse.getLambdaPrinter("start>", "<end"); printer.accept("Message body");
   * <p>
   * sout: start>Message body<end
   *
   * @param prefix prefix str
   * @param suffix suffix str
   * @return
   */
  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return message -> System.out.println(prefix + message + suffix);
  }

  /**
   * Print each message with a given printer Please use `getLambdaPrinter` method
   * <p>
   * e.g. String[] messages = {"a","b", "c"}; lse.printMessages(messages,
   * lse.getLambdaPrinter("msg:", "!") );
   * <p>
   * sout: msg:a! msg:b! msg:c!
   *
   * @param messages
   * @param printer
   */
  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    for(String message: messages)
      printer.accept(message);
  }

  /**
   * Print all odd number from a intStream. Please use `createIntStream` and `getLambdaPrinter`
   * methods
   * <p>
   * e.g. lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
   * <p>
   * sout: odd number:1! odd number:3! odd number:5!
   *
   * @param intStream
   * @param printer
   */
  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    intStream = getOdd(intStream);
    intStream.mapToObj(Integer::toString).forEach(printer);
  }

  /**
   * Square each number from the input. Please write two solutions and compare difference - using
   * flatMap
   *
   * @param ints
   * @return
   */
  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    return ints.flatMap(List::stream).map(x -> x*x);
  }

  public static void main(String[] args){
    LambdaStreamExc stream = new LambdaStreamExcImp();
    System.out.println("Test for toUpperCase and createStrStream");
    Stream<String> stringStream = stream.toUpperCase("test1", "test2", "test3");
    stringStream.forEach(System.out::println);
    System.out.print("\n");

    System.out.println("Test for filter");
    Stream<String> stringStream2 = stream.createStrStream("a", "ba", "b");
    stream.filter(stringStream2, "a").forEach(System.out::println);
    System.out.print("\n");

    System.out.println("Test for createIntStream and toList");
    int[] arr = {1,2,3,4,5};
    IntStream intStream = stream.createIntStream(arr);
    List<Integer> intList = stream.toList(intStream);
    intList.forEach(System.out::println);
    System.out.print("\n");

    System.out.println("Test for string toList");
    Stream<String> stringStream3 = stream.createStrStream("a", "b", "c");
    List<String> stringList = stream.toList(stringStream3);
    for(String s : stringList){
      System.out.println(s);
    }
    System.out.print("\n");

    System.out.println("Test for createIntStream with range and squareRootIntStream");
    IntStream intStream2 = stream.createIntStream(0, 10);
    DoubleStream doubleStream = stream.squareRootIntStream(intStream2);
    doubleStream.forEach(System.out::println);
    System.out.print("\n");

    System.out.println("Test for getOdd");
    IntStream intStream3 = stream.createIntStream(0, 10);
    intStream3 = stream.getOdd(intStream3);
    intStream3.forEach(System.out::println);
    System.out.print("\n");

    System.out.println("Test for getLambdaPrinter and printMessages");
    Consumer<String> printer = stream.getLambdaPrinter("msg:", "!");
    String[] messages = {"a", "b", "c"};
    stream.printMessages(messages, printer);
    System.out.print("\n");

    System.out.println("Test for printOdd");
    Consumer<String> printer2 = stream.getLambdaPrinter("odd number:", "!");
    IntStream intStream4 = stream.createIntStream(0, 10);
    stream.printOdd(intStream4, printer2);
    System.out.print("\n");

    System.out.println("Test for flatNestedInt");
    IntStream intStream5 = stream.createIntStream(0, 10);
    List<Integer> list = stream.toList(intStream5);
    Stream<List<Integer>> ints = Stream.of(list);
    Stream<Integer> squaredStream = stream.flatNestedInt(ints);
    squaredStream.forEach(System.out::println);
  }
}
