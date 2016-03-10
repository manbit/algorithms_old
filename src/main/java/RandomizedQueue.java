import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }                 // construct an empty randomized queue

    public static void main(String[] args) {

    }   // unit testing

    public boolean isEmpty() {
        return size == 0;
    }                 // is the queue empty?

    public int size() {
        return size;
    }                        // return the number of items on the queue

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        checkResize();

        items[size++] = item;
    }           // add the item

    private void checkResize() {
        Item[] temp;
        if (size == 0) {
            return;
        } else if (items.length == size) {
            temp = items;
            items = (Item[]) new Object[size * 2];
        } else if (items.length / size >= 2) {
            temp = items;
            items = (Item[]) new Object[items.length / 2];
        } else {
            return;
        }

        int newIndex = 0;
        for (Item existinItem : temp) {
            items[newIndex++] = existinItem;
        }
        size = items.length - 1;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        checkResize();

        int randomIndex = StdRandom.uniform(size);
        Item itemForReturn = items[randomIndex];
        items[randomIndex] = null;
        return itemForReturn;
    }                    // remove and return a random item

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        Item itemForReturn = items[randomIndex];
        return itemForReturn;
    }                     // return (but do not remove) a random item

    public Iterator<Item> iterator() {
        return new RandomizeQueueIterator<Item>();
    }         // return an independent iterator over items in random order

    private Item[] generateShuffledCopy(Item[] items) {
        Item[] copy = (Item[]) Arrays.copyOf(items, items.length);
        StdRandom.shuffle(copy);
        return copy;
    }

    public class RandomizeQueueIterator<Item> implements Iterator<Item> {
        private Item[] instantCopy = (Item[]) generateShuffledCopy(items);
        private int nextIndex = 0;

        public boolean hasNext() {
            return nextIndex < instantCopy.length - 1;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return instantCopy[nextIndex++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
