public class LinkedList<T> implements IList<T> {
    Node firstNode;
    int numberOfEntries;

    public LinkedList() {
        firstNode = null;
        numberOfEntries = 0;
    }

    public void showData() {
        Node pointer = firstNode;
        while (pointer != null) {
            System.out.print(pointer.getData() + " ");
            pointer = pointer.getNextNode();
        }
    }

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            firstNode = newNode;
        } else { // add to end of nonempty list
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode);
        }
        numberOfEntries++;
    }

    @Override
    public void add(int newPosition, T newEntry) {
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);
            if (newPosition == 1) {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else {
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            } // end if
        } else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to add operation");
    } // end add

    private Node getNodeAt(int givenPosition) {
        if ((firstNode != null) && (0 < givenPosition) && (givenPosition <= numberOfEntries)) {
            Node pointer = firstNode;
            for (int i = 1; i < givenPosition; i++)
                pointer = pointer.getNextNode();
            return pointer;
        }
        return null;
    } // end getNodeAt

    @Override
    public T remove(int givenPosition) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        return null;
    }

    @Override
    public T getEntry(int givenPosition) {
        return null;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        int index = 0;
        Node pointer = firstNode;
        while ((index < numberOfEntries) && (pointer != null)){
            result[index] = pointer.getData();
            pointer = pointer.getNextNode();
            index++;
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        return false;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        boolean result;
        if (numberOfEntries == 0) {
            assert firstNode == null;
            result = true;
        } else {
            assert firstNode != null;
            result = false;
        } // end if
        return result;
    } // end isEmpty

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
