package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue2;

class TestFifoAppend {

    @Test
    void testAppendTwoEmptyQueues() {
        // Create two empty FifoQueue2 instances
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        FifoQueue2<Integer> q2 = new FifoQueue2<>();
        
        // Append q2 to q1
        q1.append(q2);

        // Assert that both q1 and q2 are empty after the append operation
        assertTrue(q1.isEmpty());
        assertEquals(0, q1.size());
        assertTrue(q2.isEmpty());
        assertEquals(0, q2.size());
    }

    @Test
    void testAppendEmptyQueueToNonEmptyQueue() {
        // Create two FifoQueue2 instances
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        FifoQueue2<Integer> q2 = new FifoQueue2<>();

        // Add elements 1 and 2 to q2
        q2.offer(1);
        q2.offer(2);

        // Append q2 to q1
        q1.append(q2);

        // Assert that q1 is not empty, has a size of 2, and contains elements 1 and 2
        assertFalse(q1.isEmpty());
        assertEquals(2, q1.size());
        assertTrue(q2.isEmpty());
        assertEquals(0, q2.size());

        // Assert that q1 returns elements 1 and 2 in the expected order
        assertEquals(1, q1.poll());
        assertEquals(2, q1.poll());
    }

    @Test
    void testAppendNonEmptyQueueToEmptyQueue() {
        // Create two FifoQueue2 instances
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        FifoQueue2<Integer> q2 = new FifoQueue2<>();

        // Add elements 1 and 2 to q1
        q1.offer(1);
        q1.offer(2);

        // Append q1 to q2
        q2.append(q1);

        // Assert that q2 is not empty, has a size of 2, and contains elements 1 and 2
        assertFalse(q2.isEmpty());
        assertEquals(2, q2.size());
        assertTrue(q1.isEmpty());
        assertEquals(0, q1.size());

        // Assert that q2 returns elements 1 and 2 in the expected order
        assertEquals(1, q2.poll());
        assertEquals(2, q2.poll());
    }

    @Test
    void testAppendTwoNonEmptyQueues() {
        // Create two FifoQueue2 instances
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        FifoQueue2<Integer> q2 = new FifoQueue2<>();

        // Add elements 1 and 2 to q1, and elements 3 and 4 to q2
        q1.offer(1);
        q1.offer(2);
        q2.offer(3);
        q2.offer(4);

        // Append q2 to q1
        q1.append(q2);

        // Assert that q1 is not empty, has a size of 4, and contains elements 1, 2, 3, and 4
        assertFalse(q1.isEmpty());
        assertEquals(4, q1.size());
        assertTrue(q2.isEmpty());
        assertEquals(0, q2.size());

        // Assert that q1 returns elements 1, 2, 3, and 4 in the expected order
        assertEquals(1, q1.poll());
        assertEquals(2, q1.poll());
        assertEquals(3, q1.poll());
        assertEquals(4, q1.poll());
    }

    @Test
    void testAppendQueueToItself() {
        // Create a FifoQueue2 instance and add elements 1 and 2
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        q1.offer(1);
        q1.offer(2);

        // Assert that attempting to append q1 to itself throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            q1.append(q1);
        });
    }
}