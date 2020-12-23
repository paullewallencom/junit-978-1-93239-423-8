package junit.cookbook.common.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

public class SimpleMethodTest extends TestCase {
    public void testNewListIsEmpty() {
        List list = new ArrayList();
        assertEquals(0, list.size());
    }

    public void testSynchronizedListHasSameContents() {
        List list =
            Arrays.asList(new String[] { "Albert", "Henry", "Catherine" });
        assertEquals(list, Collections.synchronizedList(list));
    }
}
