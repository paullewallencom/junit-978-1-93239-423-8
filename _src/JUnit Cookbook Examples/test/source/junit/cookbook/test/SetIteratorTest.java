package junit.cookbook.test;

import java.util.*;

public class SetIteratorTest extends IteratorTest {
    protected Iterator makeNoMoreElementsIterator() throws Exception {
        return new HashSet().iterator();
    }
}
