import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] N;
    private int next = 0;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        N = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return next == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You cannot add a null item");
        }
        if (next == N.length) {
            Item[] copy = N;
            N = (Item[]) new Object[2 * N.length];
            for (int i = 0; i < copy.length; i++) {
                N[i] = copy[i];
            }
        }
        N[next++] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }
        int randomSelection = StdRandom.uniform(0, next);
        while (N[randomSelection] == null) {
            randomSelection = StdRandom.uniform(0, next);
        }
        Item item = N[randomSelection];
        N[randomSelection] = null;
        size--;

        if (size <= N.length / 4) {
            Item[] copy = N;
            N = (Item[]) new Object[N.length / 2];
            int newArrayPosition = 0;
            for (int i = 0; i < copy.length; i++) {
                if (copy[i] != null) {
                    N[newArrayPosition] = copy[i];
                    newArrayPosition++;
                }
            }
            next = size;
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        }
        int randomSelection = StdRandom.uniform(0, next);
        while (N[randomSelection] == null) {
            randomSelection = StdRandom.uniform(0, next);
        }
        return N[randomSelection];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int currentItem = 0;

        public boolean hasNext() {
            return currentItem < next;
        }

        public Item next() {
            if (currentItem == next) {
                throw new NoSuchElementException("the list is empty");
            }
            while (currentItem < next) {
                if (N[currentItem] != null) {
                    return N[currentItem++];
                }
                currentItem++;
            }
            return null;
        }

        public void remove() {
            // operation not supported
            throw new UnsupportedOperationException("remove operation is not supported");
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<String> myRandomizedQueue = new RandomizedQueue<>();
        myRandomizedQueue.enqueue("We");
        myRandomizedQueue.enqueue("Are");
        myRandomizedQueue.enqueue("One");
        myRandomizedQueue.enqueue("Body");
        System.out.println(myRandomizedQueue.size);
        System.out.println(myRandomizedQueue.dequeue());
        System.out.println(myRandomizedQueue.size);
        System.out.println(myRandomizedQueue.dequeue());
        System.out.println(myRandomizedQueue.size);
    }
}
