package junit.cookbook.test;

import java.util.*;
import java.util.ArrayList;

public class ListIteratorTest extends IteratorTest {
    protected Iterator makeNoMoreElementsIterator() throws Exception {
        return new ArrayList().iterator();
    }
}
