package junit.cookbook.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import junit.framework.TestCase;

public class ListIterator extends TestCase {
    private Iterator noMoreElementsIterator;

    protected void setUp() {
        List empty = new ArrayList();
        noMoreElementsIterator = empty.iterator();
    }

    public void testHasNextNoMoreElements() {
        assertFalse(noMoreElementsIterator.hasNext());
    }

    public void testNextNoMoreElements() {
        try {
            noMoreElementsIterator.next();
            fail("No exception with no elements remaining!");
        }
        catch (NoSuchElementException expected) {
        }
    }

    public void testRemoveNoMoreElements() {
        try {
            noMoreElementsIterator.remove();
            fail("No exception with no elements remaining!");
        }
        catch (IllegalStateException expected) {
        }
    }
}