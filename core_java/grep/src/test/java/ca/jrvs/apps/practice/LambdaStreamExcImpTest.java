package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImpTest {

  public static void main(String[] args) {
    LambdaStreamExc lse = new LambdaStreamExcImp();
    String[] TestName = {"Homa", "Almasieh"};
    lse.createStrStream(TestName).forEach(name -> System.out.println(name));

    lse.toUpperCase(TestName).forEach(uppercasename -> System.out.println(uppercasename));

    lse.filter(lse.createStrStream(TestName), "S").forEach(name -> System.out.println(name));

    int[] ints = {2, 4, 6, 8, 10};
    lse.createIntStream(ints).forEach(intArray -> System.out.println(intArray));

    Stream<String> stream = lse.createStrStream(TestName);
    lse.toList(stream).forEach(x -> System.out.println(x));

    IntStream intStream = lse.createIntStream(ints);
    lse.toList(intStream).forEach(x-> System.out.println(x));

    lse.createIntStream(2,7).forEach(val -> System.out.println(val));

    int[] squared = {4, 16, 36, 64, 100};
    IntStream sqrStream = lse. createIntStream(squared);
    lse.squareRootIntStream(sqrStream).forEach(num -> System.out.println(num));

    int[] set = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    IntStream setStream = lse.createIntStream(set);
    lse.getOdd(setStream).forEach(num -> System.out.println(num));

    Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
    printer.accept("Message body");

    String[] messages = {"a","b", "c"};
    lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!") );

    lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));

    List<List<Integer>> newList = Arrays.asList(Arrays.asList(2, 4, 6), Arrays.asList(8, 10, 12));
    // [[2, 4, 6], [8, 10, 12]]
    Stream<List<Integer>> strList = newList.stream();
    lse.flatNestedInt(strList).forEach(num -> System.out.println(num));

  }
}
