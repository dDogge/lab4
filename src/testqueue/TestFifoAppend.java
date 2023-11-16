package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue2;

import org.junit.jupiter.api.Test;

class TestFifoAppend {

    @Test
    void testAppendTwoEmptyQueues() {
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        FifoQueue2<Integer> q2 = new FifoQueue2<>();
        q1.append(q2);

        assertTrue(q1.isEmpty());
        assertEquals(0, q1.size());
        assertTrue(q2.isEmpty());
        assertEquals(0, q2.size());
    }

    @Test
    void testAppendEmptyQueueToNonEmptyQueue() {
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        FifoQueue2<Integer> q2 = new FifoQueue2<>();
        q2.offer(1);
        q2.offer(2);
        q1.append(q2);

        assertFalse(q1.isEmpty());
        assertEquals(2, q1.size());
        assertTrue(q2.isEmpty());
        assertEquals(0, q2.size());
        assertEquals(1, q1.poll());
        assertEquals(2, q1.poll());
    }

    @Test
    void testAppendNonEmptyQueueToEmptyQueue() {
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        FifoQueue2<Integer> q2 = new FifoQueue2<>();
        q1.offer(1);
        q1.offer(2);
        q2.append(q1);

        assertFalse(q2.isEmpty());
        assertEquals(2, q2.size());
        assertTrue(q1.isEmpty());
        assertEquals(0, q1.size());
        assertEquals(1, q2.poll());
        assertEquals(2, q2.poll());
    }

    @Test
    void testAppendTwoNonEmptyQueues() {
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        FifoQueue2<Integer> q2 = new FifoQueue2<>();
        q1.offer(1);
        q1.offer(2);
        q2.offer(3);
        q2.offer(4);
        q1.append(q2);

        assertFalse(q1.isEmpty());
        assertEquals(4, q1.size());
        assertTrue(q2.isEmpty());
        assertEquals(0, q2.size());
        assertEquals(1, q1.poll());
        assertEquals(2, q1.poll());
        assertEquals(3, q1.poll());
        assertEquals(4, q1.poll());
    }

    @Test
    void testAppendQueueToItself() {
        FifoQueue2<Integer> q1 = new FifoQueue2<>();
        q1.offer(1);
        q1.offer(2);

        assertThrows(IllegalArgumentException.class, () -> {
            q1.append(q1);
        });
    }
}