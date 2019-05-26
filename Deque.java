import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node firstItem = null;
    private Node lastItem;
    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return firstItem == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You cannot add a null item");
        }
        Node oldFirst = firstItem;
        firstItem = new Node();
        firstItem.item = item;
        firstItem.next = oldFirst;

        // if the list was initially empty
        if (oldFirst == null) {
            lastItem = firstItem; // then the last time is also the first item
        }
        else {
            oldFirst.previous = firstItem;
        }

        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You cannot add a null item");
        }
        Node oldLast = lastItem;

        lastItem = new Node();
        lastItem.item = item;
        lastItem.next = null;
        lastItem.previous = oldLast; // adds a pointer to the previous last item
        // if the list was initially empty
        if (isEmpty()) {
            firstItem = lastItem; // then the first item is also the last item
        }
        else {
            oldLast.next = lastItem;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("the list is empty");
        }
        // since the first item is going to be removed, how do we free up the memory
        Item item = firstItem.item;
        firstItem = firstItem.next;
        if (isEmpty()) {
            lastItem = null;
        }
        else {
            firstItem.previous
                    = null; // the new first position cannot hold a pointer to the previous one
        }
        size--; // decrease the size of the list
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("the list is empty");
        }
        // how do I change the pointer to the last item to advance to the next in line
        Item item = lastItem.item;
        lastItem = lastItem.previous;
        if (lastItem == null) {
            // if only one item item existed in the list initially, and it is not being remove
            // then both the first item and last item pointers point to nothing
            firstItem = null;
        }
        else {
            // the new last position cannot hold a pointer to the one next (in front) of it
            lastItem.next = null;
        }

        size--; // decrease the size of the list
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = firstItem;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("the list is empty");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            // operation not supported
            throw new UnsupportedOperationException("remove operation is not supported");
        }

    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> myDeque = new Deque<>();

        myDeque.addFirst("To");
        myDeque.addLast("Be");
        myDeque.addLast("Or");
        myDeque.addLast("Or");
        System.out.println(myDeque.removeLast());
        System.out.println(myDeque.removeFirst());
        System.out.println(myDeque.size());

        myDeque.addFirst("To");
        myDeque.addLast("Not");
        System.out.println(myDeque.size());
        for (String item : myDeque) {
            System.out.println(item + "\n ************");
        }
        System.out.println(myDeque.removeLast());
        for (String item : myDeque) {
            System.out.println(item);
        }
    }
}
