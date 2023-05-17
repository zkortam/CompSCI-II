/*
 * Zakaria Kortam - COMSC 76
 * 4/26/2023 - Professor Estrada
 * 
 * 1. The program creates a LinkedList of names, with the data type of Strings.
 * 2. The LinkedList class implements the MyList interface with a generic parameter.
 * 3. Within the class, there are two fields. There's a node with a head and tail, an there's also
 * an integer value that represents size.
 * 4. Within the class, there are numerous functions with varying functionality regarding mutation and
 * modification of the data.
 * 5. The main function declares all of the names and tests the various functions within the class.
 */

import java.util.*;

public class lists {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();

        // Add 10 names to the list
        names.add(0,"Steve");
        names.add(1, "Tim");
        names.add(2, "Ronald");
        names.add(3, "Johnny");
        names.add(4, "Phil");
        names.add(5, "Scott");
        names.add(6, "David");
        names.add(7, "Omar");
        names.add(8, "Donald");
        names.add(9, "Jack");

        System.out.println("First name: " + names.getFirst());
        System.out.println("Last name: " + names.getLast());
        names.addFirst("Craig");
        names.addLast("Federighi");
        System.out.println("First and Last Names: " + names);
        names.add(3, "Ibrahim");
        System.out.println("4th name: " + names.get(3));
        names.removeFirst();
        names.removeLast();
        System.out.println("After removing first and last names: " + names);
        names.remove(2);
        System.out.println("After removing the 3rd name: " + names);
        System.out.println("List contains Tim?: " + names.contains("Tim"));
        System.out.println("List contains Ronald?: " + names.contains("Ronald"));
        System.out.println("5th name: " + names.get(4));
        System.out.println("Index of Donald: " + names.indexOf("Donald"));
        System.out.println("Last index of David: " + names.lastIndexOf("David"));
        names.clear();
        System.out.println("After clearing list: " + names);

    }
}

class LinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0;

    public void MyLinkedList() {
    }

    public void MyLinkedList(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    public E getFirst() {
        if (size == 0) {
            return null;
        }
        else {
            return head.element;
        }
    }

    public E getLast() {
        if (size == 0) {
            return null;
        }
        else {
            return tail.element;
        }
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
    
        if (tail == null)
            tail = head;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); 
        if (tail == null) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    @Override
    public void add(int index, E e) {
        if (index == 0) 
            addFirst(e);
        else if (index >= size) 
            addLast(e);
        else {
            Node<E> current = head;
            for (int i = 1; i < index; i++)
                current = current.next;
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }
    
    public E removeFirst() {
        if (size == 0) return null; // Nothing to delete
        else {
            Node<E> temp = head; // Keep the first node temporarily
            head = head.next; // Move head to point to next node
            size--; // Reduce size by 1
            if (head == null) tail = null;
            return temp.element; // Return the deleted element
        }
    }

    public E removeLast() {
        if (size == 0) return null; // Nothing to remove
        else if (size == 1) { // Only one element in the list
            Node<E> temp = head;
            head = tail = null; // list becomes empty
            size = 0;
            return temp.element;
        }
        else {
            Node<E> current = head;
    
            for (int i = 0; i < size - 2; i++)
                current = current.next;
    
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) return null; // Out of range
        else if (index == 0) return removeFirst(); // Remove first
        else if (index == size - 1) return removeLast(); // Remove last
        else {
            Node<E> previous = head;
    
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
    
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("(");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", ");
            }
            else {
                result.append(")");
            }
        }
        return result.toString();
    }

    @Override /** Clear the list */
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override /** Return true if this list contains the element e */
    public boolean contains(Object e) {
        Node<E> current = head;
        while (current != null) {
            if (current.element.equals(e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    @Override /** Return the element at the specified index */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }
    
    @Override /** Return the index of the head matching element in this list. Return −1 if no match. */
    public int indexOf(Object e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }
    
    @Override /** Return the index of the last matching element in this list. Return −1 if no match. */
    public int lastIndexOf(E e) {
        Node<E> current = head;
        int lastIndex = -1;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(e)) {
                lastIndex = i;
            }
            current = current.next;
        }
        return lastIndex;
    }
    
    @Override /** Replace the element at the specified position in this list with the specified element. */
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldElement = current.element;
        current.element = e;
        return oldElement;
    }    

    @Override /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements java.util.Iterator<E> {
        private Node<E> current = head; // Current index

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public void remove() {
            if (current == null) {
                throw new IllegalStateException("next() has not been called yet.");
            }

            if (current == head) {
                removeFirst();
                current = head;
            } else if (current == tail) {
                removeLast();
                current = tail;
            } else {
                Node<E> previous = head;
                while (previous != null && previous.next != current) {
                    previous = previous.next;
                }
                previous.next = current.next;
                size--;
                current = previous;
            }
        }

    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    @Override /** Return the number of elements in this list */
    public int size() {
        return size;
    }
}

public interface MyList<E> {

    public void add(E e);

    public void add(int index, E e);

    public void clear();

    public boolean contains(E e);

    public E get(int index);

    public int indexOf(E e);

    public boolean isEmpty();

    public int lastIndexOf(E e);

    public E remove(int index);

    public boolean remove(E e);

    public int size();
}
