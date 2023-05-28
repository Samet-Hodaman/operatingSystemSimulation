public interface PriorityQueueInterface <T extends Comparable<? super T>>{
    void add(T newEntry);
    T remove();
    T peek();
    boolean isEmpty();
    int getSize();
    void clear();
}
