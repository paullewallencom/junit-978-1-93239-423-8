package junit.cookbook.coffee.model.ejb.test;

import javax.ejb.EntityContext;
import javax.naming.*;
import javax.naming.Context;
import javax.sql.DataSource;

import junit.cookbook.coffee.model.ejb.OrderBmpBean;
import junit.framework.TestCase;

import org.easymock.MockControl;
import org.mockejb.jndi.MockContextFactory;

public class OrderBmpBeanTest extends TestCase {
    private OrderBmpBean bean;
    private Object actualPrimaryKey;
    private DataSource mockDataSource;

    protected void setUp() throws Exception {
        MockContextFactory.setAsInitial();

        mockDataSource =
            (DataSource) MockControl
                .createNiceControl(DataSource.class)
                .getMock();

        new InitialContext().bind(
            "java:comp/env/jdbc/OrderData",
            mockDataSource);

        bean = new OrderBmpBean();
    }

    public void testLookupDataSource() throws Exception {
        assertSame(mockDataSource, OrderBmpBean.lookupDataSource());
    }

    public void testGetOrderId() throws Exception {
        Integer orderId = new Integer(0);

        MockControl entityContextControl =
            MockControl.createNiceControl(EntityContext.class);

        EntityContext mockEntityContext =
            (EntityContext) entityContextControl.getMock();

        mockEntityContext.getPrimaryKey();
        entityContextControl.setReturnValue(orderId);

        entityContextControl.replay();

        bean.setEntityContext(mockEntityContext);
        assertEquals(orderId, bean.getOrderId());

        entityContextControl.verify();
    }
}
