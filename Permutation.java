import edu.princeton.cs.algs4.In;

public class Permutation {
    public static void main(String[] args) {
        int noToBeRead = Integer.parseInt(args[0]);
        RandomizedQueue<String> myRandomizedQueue = new RandomizedQueue<>();
        In in = new In("mediumTale.txt"); // input file
        int i = 0;
        while (!in.isEmpty()) {
            myRandomizedQueue.enqueue(in.readString());
        }

        while (myRandomizedQueue.size() != 0 && i < noToBeRead) {
            String value = myRandomizedQueue.dequeue();
            i++;
            System.out.println(value);
        }
    }
}
