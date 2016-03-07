import edu.princeton.cs.introcs.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.introcs.StdOut.print;

public class Deque<Item> implements Iterable<Item> {

    private Node head, tail;
    private int size;

    public Deque() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.previous = head;
    }                           // construct an empty deque

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("First");
        deque.addLast("Last");
        deque.addFirst("First-1");
        deque.addLast("Last-1");
        String item = deque.removeFirst();
        print(item);
        item = deque.removeLast();
        print(item);
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            print(iterator.next());
            iterator.remove();
        }
        while (iterator.hasNext()) {
            print(iterator.next());
        }
    }   // unit testing

    public boolean isEmpty() {
        return size == 0;
    }                 // is the deque empty?

    public int size() {
        return size;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();

        if (head.value != null) {
            Node newHead = new Node();
            newHead.value = item;
            head.previous = newHead;
            newHead.next = head;
            head = newHead;
        } else {
            head.value = item;
        }
        size++;
    }          // add the item to the front

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();

        if (tail.value != null) {
            Node newTail = new Node();
            newTail.value = item;
            tail.next = newTail;
            newTail.previous = tail;
            tail = newTail;
        } else {
            tail.value = item;
        }
        size++;
    }           // add the item to the end

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item removingItem = null;
        if (head.value != null) {
            removingItem = head.value;
            head = head.next;
            head.previous = null;
        }
        size--;
        return removingItem;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item removingItem = null;
        if (tail.value != null) {
            removingItem = tail.value;
            tail = tail.previous;
            tail.next = null;
        }
        size--;
        return removingItem;
    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }         // return an iterator over items in order from front to end

    public class DequeIterator<Item> implements Iterator<Item> {

        private Node current;

        public DequeIterator() {
            this.current = head;
        }

        public boolean hasNext() {
            return current != null && current.next != null;
        }

        public Item next() {
            Item next = (Item) current.value;
            current = current.next;
            return next;
        }

        public void remove() {
            current = current.next;
        }
    }

    private class Node {
        Item value;
        Node next;
        Node previous;
    }
}
