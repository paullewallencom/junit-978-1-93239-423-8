package junit.cookbook.test;

import java.util.*;
import java.util.ArrayList;

public class NoRemoveListIteratorTest extends NoRemoveIteratorTest {
    protected Iterator makeNoMoreElementsIterator()
        throws Exception {

        return new ArrayList().iterator();
    }
}
