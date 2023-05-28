public interface SortedListInterface<T extends Comparable<? super T>> {
    void add(T newEntry);
    boolean remove(T anEntry);
    int getPosition(T anEntry);
    T getEntry(int givenPosition);
    boolean contains(T anEntry);
    T remove(int givenPosition);
    void clear();
    int getLength();
    boolean isEmpty();
    T[] toArray();
}