import java.util.EmptyStackException;

public class PriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

    private Node firstNode;
    private int numberOfEntries;

    public PriorityQueue() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
            numberOfEntries++;
        } else {
            firstNode.setBefore(newNode);
            newNode.setNextNode(firstNode);
            firstNode = newNode;
            numberOfEntries++;
        }
    }
    public void showData(){
        Node pointer = firstNode;
        while (pointer != null){
            System.out.println(pointer.getData().toString());
            pointer = pointer.getNextNode();
        }
    }

    @Override
    public T remove() {
        T result = firstNode.getData();
        if (isEmpty())
            throw new EmptyStackException();
        if (numberOfEntries == 1) {
            firstNode = null;
            numberOfEntries--;
            return result;
        }
        Node removedNode = firstNode;
        Node pointer = firstNode;
        while (pointer != null) {
            if (result.comparePriorityTo(pointer.getData()) < 0) {
                removedNode = pointer;
                result = pointer.getData();
            } else if (result.comparePriorityTo(pointer.getData()) == 0) {
                if (result.compareDateTo(pointer.getData()) >= 0) {
                    removedNode = pointer;
                    result = pointer.getData();
                }
            }
            pointer = pointer.getNextNode();
        }
        if (removedNode == firstNode){
            firstNode = removedNode.getNextNode();
            numberOfEntries--;
            return result;
        }
        Node beforeNode = removedNode.getBefore();
        Node nodeAfter = removedNode.getNextNode();
        beforeNode.setNextNode(nodeAfter);
        if (nodeAfter != null)
            nodeAfter.setBefore(beforeNode);
        result = removedNode.getData();
        numberOfEntries--;
        return result;
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        T result = firstNode.getData();
        Node pointer = firstNode;
        while (pointer != null) {
            if (result.comparePriorityTo(pointer.getData()) < 0) {
                result = pointer.getData();
            } else if (result.comparePriorityTo(pointer.getData()) == 0) {
                if (result.compareDateTo(pointer.getData()) > 0) {
                    result = pointer.getData();
                }
            }
            pointer = pointer.getNextNode();
        }
        return firstNode.getData();
    }

    @Override
    public boolean isEmpty() {
        return (firstNode == null && numberOfEntries == 0);
    }

    @Override
    public int getSize() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        firstNode = null;
        System.gc();
    }

    private class Node {
        private T data;
        private Node next;
        private Node before;

        private Node(T dataPortion) {
            this(dataPortion, null, null);
        }

        private Node(T dataPortion, Node nextNode, Node beforeNode) {
            this.setData(dataPortion);
            this.setNextNode(nextNode);
            this.setBefore(beforeNode);
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

        public Node getBefore() {
            return before;
        }

        public void setBefore(Node before) {
            this.before = before;
        }
    }
}
