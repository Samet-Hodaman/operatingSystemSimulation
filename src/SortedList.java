import java.util.EmptyStackException;

public class SortedList<T extends Comparable<? super T>> implements SortedListInterface<T>{
    private Node firstNode;
    private int numberOfEntries;
    public SortedList(){
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public void add(T newEntry) {
        firstNode = add(newEntry,firstNode);
        numberOfEntries++;
    }
    private Node add(T newEntry,Node currentNode){
        if ((currentNode == null) || (newEntry.compareDateTo(currentNode.getData())<= 0)){
            currentNode = new Node(newEntry,currentNode);
        } else {
            Node nodeAfter = add(newEntry,currentNode.getNextNode());
            currentNode.setNextNode(nodeAfter);
        }
        return currentNode;
    }

    @Override
    public boolean remove(T anEntry) {

        T result = firstNode.getData();

        return false;
    }

    @Override
    public int getPosition(T anEntry) {
        Node current = firstNode;
        int position = 1;
        while ((position <= numberOfEntries) && (anEntry.compareDateTo(current.getData())) > 0){
            current = current.getNextNode();
            position++;
        }
        if ((position > numberOfEntries) || (anEntry.compareDateTo(current.getData())) != 0)
            position = -position;
        return position;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition > 0 && givenPosition<=numberOfEntries){

            Node current = firstNode;
            for (int i = 1; i <givenPosition;i++)
                current = current.getNextNode();
            return current.getData();
        } else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to getEntry operation");
    }

    @Override
    public boolean contains(T anEntry) {
        Node current = firstNode;
        while (current.getData() == null){
            if (current.getData() == anEntry)
                return true;
            current = current.getNextNode();
        }
        return false;
    }

    @Override
    public T remove(int givenPosition) {
        Node nodeBefore = null;
        Node current = firstNode;
        T result;
        if (givenPosition > 0 && givenPosition <= numberOfEntries){
            if (isEmpty())
                throw new EmptyStackException();
            if (givenPosition == 1){
                result = firstNode.getData();
                firstNode = null;
                return result;
            }else {
                for (int i = 1; i < givenPosition;i++){
                    nodeBefore = current;
                    current = current.getNextNode();
                }
                nodeBefore.setNextNode(current.getNextNode());
                numberOfEntries--;
                result = current.getData();
                return result;
            }
        }else
            throw new IndexOutOfBoundsException("" +
                    "Illegal position given to remove operation.");
    }

    @Override
    public void clear() {
        firstNode = null;
        System.gc();
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return (numberOfEntries == 0 && firstNode == null);
    }

    @Override
    public T[] toArray() {
        if (isEmpty())
            throw new EmptyStackException();
        Node pointer = firstNode;
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Comparable[numberOfEntries];
        int index = 0;
        while ((index < numberOfEntries) && (pointer != null)){
            result[index] = pointer.getData();
            pointer = pointer.getNextNode();
            index++;
        }
        return result;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            this.setData(dataPortion);
            this.setNextNode(nextNode);
        } // end constructor

        private T getData() {
            return data;
        }

        private void setData(T data) {
            this.data = data;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            this.next = nextNode;
        }
    } // end Node
}
