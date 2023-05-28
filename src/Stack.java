import java.util.EmptyStackException;

public class Stack<T> implements StackInterface<T>{
    private Node firstNode;
    private int numberOfEntries;
    public Stack() {
        firstNode = null;
        numberOfEntries = 0;
    }
    public void showData(){
        Node pointer = firstNode;
        while (pointer != null){
            System.out.println(pointer.getData());
            pointer = pointer.getNextNode();
        }
    }
    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry);
        if (!isEmpty()) {
            newNode.next = firstNode;
        }
        firstNode = newNode;
        numberOfEntries++;
    }

    @Override
    public T pop(){
        if (isEmpty())
            throw new EmptyStackException();
        else {
            T result = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            return result;
        }
    }
    @Override
    public T peek() {
        return firstNode.getData();
    }
    @Override
    public boolean isEmpty() {
        return (firstNode == null && numberOfEntries == 0);
    }
    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
        System.gc();
    }

    @Override
    public int getLength() {
        return numberOfEntries;
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
