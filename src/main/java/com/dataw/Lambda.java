package com.dataw;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Kinyi_Chan
 * @since 2018-09-13
 */
public class Lambda {

    public static void main(String[] args) {
        Stream<List<Integer>> stream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2,3),
                Arrays.asList(4,5,6)
        );
        stream.flatMap(x -> x.stream().map(y -> y + 1)).forEach(System.out::println);

    }
}
