public interface Comparable<T> {
    int compareDateTo(T other);
    int comparePriorityTo(T other);
    int compareBurstTimeTo(T other);
}
