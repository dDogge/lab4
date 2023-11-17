package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue2<E> extends AbstractQueue<E> implements Queue<E> {
    private QueueNode<E> last; // Reference to the last node in the queue
    private int size; // Number of elements in the queue

    public FifoQueue2() {
        super();
        last = null;
        size = 0;
    }

    /**    
     * Inserts the specified element into this queue, if possible
     * post:    The specified element is added to the rear of this queue
     * @param    e the element to insert
     * @return   true if it was possible to add the element 
     *           to this queue, else false
     */
    public boolean offer(E e) {
        // Create a new node for the specified element
        QueueNode<E> newNode = new QueueNode<>(e);
        
        // If the queue is empty, set the last node to the new node with circular reference
        if (last == null) {
            last = newNode;
            last.next = last; // circular reference for a single element
        } else {
            // Connect the new node to the front of the queue and update last
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        size++;
        return true;
    }

    /**    
     * Returns the number of elements in this queue
     * @return the number of elements in this queue
     */
    public int size() {        
        return size;
    }

    /**    
     * Retrieves, but does not remove, the head of this queue, 
     * returning null if this queue is empty
     * @return  the head element of this queue, or null 
     *          if this queue is empty
     */
    public E peek() {
        if (last == null) {
            return null;
        }
        return last.next.element;
    }

    /**    
     * Retrieves and removes the head of this queue, 
     * or null if this queue is empty.
     * post:    the head of the queue is removed if it was not empty
     * @return  the head of this queue, or null if the queue is empty 
     */
    public E poll() {
        if (last == null) {
            return null;
        }
        E removedElement = last.next.element;
        if (last.next == last) {
            last = null; // the last element is being removed
        } else {
            last.next = last.next.next;
        }
        size--;
        return removedElement;
    }

    /**    
     * Returns an iterator over the elements in this queue
     * @return an iterator over the elements in this queue
     */    
    public Iterator<E> iterator() {
        // Create a new Iterator instance using an anonymous inner class
        return new Iterator<E>() {
            // Initialize the iterator with the first node in the queue (or null if the queue is empty)
            private QueueNode<E> current = (last == null) ? null : last.next;
            
            // Track the remaining elements to iterate over
            private int remaining = size;

            /**
             * Checks if there are more elements to iterate over
             * @return true if there are remaining elements, false otherwise
             */
            @Override
            public boolean hasNext() {
                return remaining > 0;
            }

            /**
             * Retrieves the next element in the iteration
             * @return the next element
             * @throws NoSuchElementException if there are no more elements to iterate over
             */
            @Override
            public E next() {
                // Check if there are remaining elements
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                // Retrieve the current element, move to the next node, and decrement the remaining count
                E element = current.element;
                current = current.next;
                remaining--;

                // Return the retrieved element
                return element;
            }
        };
    }

    private static class QueueNode<E> {
        E element; // The data of the node
        QueueNode<E> next; // Reference to the next node in the queue

        private QueueNode(E x) {
            element = x;
            next = null;
        }
    }
    
    /**
     * Appends the specified queue to this queue
     * post: all elements from the specified queue are appended
     * to this queue. The specified queue (q) is empty after the call.
     * @param q the queue to append
     * @throws IllegalArgumentException if this queue and q are identical
     */
    public void append(FifoQueue2<E> q) {
        if (this == q) {
            throw new IllegalArgumentException("Cannot append a queue to itself");
        }

        if (q.size() == 0) {
            return; // Nothing to append if q is empty
        }

        if (this.size() == 0) {
            // If this queue is empty, simply update the references
            this.last = q.last;
        } else {
            // Connect the last node of this queue to the first node of q
            QueueNode<E> temp = this.last.next;
            this.last.next = q.last.next;
            q.last.next = temp;

            // Update the last reference of this queue
            this.last = q.last;
        }

        // Update the size of this queue
        this.size += q.size();

        // Clear the specified queue (q)
        q.last = null;
        q.size = 0;
    }
}