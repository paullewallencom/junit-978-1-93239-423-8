package junit.cookbook.coffee.data.jdbc;

import java.sql.*;

import junit.cookbook.coffee.data.*;

public class OrderStoreCommandExecuterJdbcImpl
    implements OrderStoreCommandExecuter {

    private OrderStoreCommandExecuter executer;
    private ConnectionProvider connectionProvider;

    public OrderStoreCommandExecuterJdbcImpl(
        OrderStoreCommandExecuter executer,
        ConnectionProvider connectionProvider) {

        this.executer = executer;
        this.connectionProvider = connectionProvider;
    }

    public void execute(
        OrderStore orderStore,
        OrderStoreCommand orderStoreCommand,
        String exceptionMessage) {

        OrderStoreJdbcImpl orderStoreJdbcImpl =
            (OrderStoreJdbcImpl) orderStore;

        Connection connection = connectionProvider.getConnection();
        orderStoreJdbcImpl.open(connection);

        try {
            executer.execute(
                orderStoreJdbcImpl,
                orderStoreCommand,
                exceptionMessage);
        }
        finally {
            try {
                orderStoreJdbcImpl.close();
                connection.close();
            }
            catch (SQLException ignored) {
            }
        }
    }
}
