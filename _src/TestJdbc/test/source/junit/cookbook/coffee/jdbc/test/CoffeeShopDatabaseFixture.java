package junit.cookbook.coffee.jdbc.test;

import java.sql.*;

import junit.framework.TestCase;

import com.diasparsoftware.jdbc.JdbcResourceRegistry;
import com.mimer.jdbc.MimerDataSource;

public abstract class CoffeeShopDatabaseFixture extends TestCase {
    private Connection connection;
    private MimerDataSource dataSource;
    private JdbcResourceRegistry jdbcResourceRegistry;

    protected void setUp() throws Exception {
        dataSource = makeDataSource();

        jdbcResourceRegistry = new JdbcResourceRegistry();

        connection = dataSource.getConnection();
        getJdbcResourceRegistry().registerConnection(connection);
    }

    public static MimerDataSource makeDataSource() {
        MimerDataSource dataSource = new MimerDataSource();
        dataSource.setDatabaseName("coffeeShopData");
        dataSource.setUser("admin");
        dataSource.setPassword("adm1n");
        return dataSource;
    }

    protected void tearDown() throws Exception {
        getJdbcResourceRegistry().cleanUp();
    }

    public MimerDataSource getDataSource() {
        return dataSource;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected JdbcResourceRegistry getJdbcResourceRegistry() {
        return jdbcResourceRegistry;
    }

    protected void registerConnection(Connection connection) {
        jdbcResourceRegistry.registerConnection(connection);
    }

    protected void registerStatement(Statement statement) {
        jdbcResourceRegistry.registerStatement(statement);
    }

    protected void registerResultSet(ResultSet resultSet) {
        jdbcResourceRegistry.registerResultSet(resultSet);
    }
}
