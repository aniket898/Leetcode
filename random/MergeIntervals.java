/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            return result;
        }
         
        Collections.sort(intervals, new IntervalComparator());
         
        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (isOverlapped(curr, prev)) {
                prev.start = prev.start;
                prev.end = Math.max(curr.end, prev.end);
            } else {
                result.add(prev);
                prev = curr;
            }
        }
         
        result.add(prev);
        return result;
    }
     
    private boolean isOverlapped(Interval curr, Interval prev) {
        return curr.start <= prev.end;
    }
     
    private class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }
}