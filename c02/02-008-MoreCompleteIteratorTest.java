public abstract class MoreCompleteIteratorTest extends TestCase {
    // Other tests as in IteratorTest

    protected abstract boolean supportsRemove();

    public void testRemoveNoMoreElements() {
        try {
            noMoreElementsIterator.remove();
            if (supportsRemove()) {
                fail("No exception with no elements remaining!");
            } else {
                fail("No exception when attempting to remove!");
            }
        }
        catch (IllegalStateException expected1) {
            if (!supportsRemove()) {
                fail("Expecting UnsupportedOperationsException on " 
                        + "attempt to remove!");
            }
        }
        catch (UnsupportedOperationException expected2) {
            if (supportsRemove()) {
                fail("Expecting IllegalStateException on attempt " 
                        + "to remove!");
            }
        }
    }
}