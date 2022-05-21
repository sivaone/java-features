package com.sivanagireddy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class ParallelStreamsTest {

    @Test
    void testParallelStreams() {
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

        Assertions.assertTrue(list.parallelStream().isParallel());
        list.parallelStream().map(e -> e*e).forEach(System.out::println);

        System.out.println("no of cores "+Runtime.getRuntime().availableProcessors());
        //average returns optional double
        OptionalDouble average = IntStream.range(1, 5).filter(e -> e % 5 == 0).average();
        // sum returns int
        int sum = IntStream.range(1, 5).filter(e -> e % 5 == 0).sum();
        // count returns long
        long count = IntStream.range(1, 5).filter(e -> e % 5 == 0).count();


        List<Person> personList = new ArrayList<>();
        personList.add(new Person());
        personList.add(new Student()); // works

        printList(personList);

        List<Student> studentList = new ArrayList<>();
        // printPersonList(studentList); // won't work
        printList(studentList); // works


    }

    void printPersonList(List<Person> personList) {}

    void printList(List<? extends Person> personList) {
        // personList.add(new Person()); // won't work
        // personList.add(new Student()); // won't work
    }

    @Test
    void testArrayTypeSafety() {
        Person[] pArr;
        Student[] sArr = new Student[3];

        pArr = sArr;
        pArr[0] = new Student(); // works
        sArr[1] = new Student(); // works
        // pArr[2] = new Person(); // FAIL at runtime, actual type is student array
    }
}

class Person {}

class Student extends Person {}