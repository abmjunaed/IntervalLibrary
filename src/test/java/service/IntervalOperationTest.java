package service;

import model.Interval;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntervalOperationTest {
    private final IntervalOperation operation = new IntervalOperation();

    @Test
    void mergeWithEmptyList() {
        assertTrue(operation.mergeOverlappedIntervals(new ArrayList<>()).isEmpty());
    }

    @Test
    void mergeWithNullList() {
        assertTrue(operation.mergeOverlappedIntervals(null).isEmpty());
    }

    @Test
    void mergeWithOneList() {
        List<Interval> oneElement = Stream.of(new Interval(1, 9)).collect(Collectors.toList());
        List<Interval> result = operation.mergeOverlappedIntervals(oneElement);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getStart());
        assertEquals(9, result.get(0).getEnd());
    }

    @Test
    void mergeUnsorted() {
        List<Interval> input = Stream.of(new Interval(25, 30), new Interval(2, 19), new Interval(14, 23), new Interval(4, 8)).collect(Collectors.toList());
        List<Interval> result = operation.mergeOverlappedIntervals(input);
        assertEquals(2, result.size());

        assertEquals(2, result.get(0).getStart());
        assertEquals(23, result.get(0).getEnd());

        assertEquals(25, result.get(1).getStart());
        assertEquals(30, result.get(1).getEnd());
    }

    @Test
    void mergeWithStrictlySorted() {
        List<Interval> oneElement = Stream.of(new Interval(1, 3), new Interval(1, 4), new Interval(6, 9)).collect(Collectors.toList());
        List<Interval> result = operation.mergeOverlappedIntervals(oneElement);
        assertEquals(2, result.size());

        assertEquals(1, result.get(0).getStart());
        assertEquals(4, result.get(0).getEnd());

        assertEquals(6, result.get(1).getStart());
        assertEquals(9, result.get(1).getEnd());
    }

    @Test
    void mergeWithFirstSortedSecondUnsorted() {
        List<Interval> oneElement = Stream.of(new Interval(1, 4), new Interval(1, 3), new Interval(6, 9)).collect(Collectors.toList());
        List<Interval> result = operation.mergeOverlappedIntervals(oneElement);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getStart());
        assertEquals(4, result.get(0).getEnd());

        assertEquals(6, result.get(1).getStart());
        assertEquals(9, result.get(1).getEnd());
    }


}
