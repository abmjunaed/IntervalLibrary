package service;

import model.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IntervalOperation {
    /**
     * Merges the overlapping intervals and returns the disjoint intervals.
     * <p>
     * e.g, for the input  [[25,30], [2,19], [14, 23], [4,8]], it returns [[2,23], [25,30]]
     * <p>
     * <b>Algorithm:</b> sort the intervals based on the first value. Then check the overlapping by comparing the start of an interval with the previous interval's end.
     * Increase the end of an interval by taking the bigger of the end of the two overlapping intervals.
     *
     * @return A list containing the merged intervals.
     * This function doesn't return null; it returns empty list if the input is empty or  null
     */
    public List<Interval> mergeOverlappedIntervals(List<Interval> intervals) {

        if (intervals == null || intervals.isEmpty()) return new ArrayList<>();

        sortByStart(intervals);

        List<Interval> result = new ArrayList<>();

        int start = intervals.get(0).getStart();
        int end = intervals.get(0).getEnd();

        for (int i = 1; i < intervals.size(); i++) {
            Interval currentInterval = intervals.get(i);

            if (currentInterval.getStart() > end) { //outside the last interval
                result.add(new Interval(start, end)); //found the previous disjoint set
                start = currentInterval.getStart(); //start of new disjoint set
            }
            end = Math.max(currentInterval.getEnd(), end); //update the end of the disjoint set
        }
        result.add(new Interval(start, end));

        return result;
    }

    private void sortByStart(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(Interval::getStart));
    }
}
