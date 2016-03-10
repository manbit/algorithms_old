import edu.princeton.cs.algs4.StdOut;

public class Subset {

    public static void main(String[] args) {
        String[] inputs = new String[args.length - 1];
        int k = Integer.parseInt(args[0]);
        for (int i = 1; i < args.length; i++) {
            inputs[i - 1] = args[i];
        }

        RandomizedQueue<String> rQueue = new RandomizedQueue<String>();
        for (String str : inputs) {
            rQueue.enqueue(str);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(rQueue.dequeue());
        }
    }
}
