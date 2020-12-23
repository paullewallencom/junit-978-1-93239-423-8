package junit.cookbook.coffee.data.jdbc;

import java.sql.*;
import java.util.*;
import java.util.Date;

import junit.cookbook.coffee.data.DiscountStore;

import com.diasparsoftware.java.sql.PreparedStatementData;
import com.diasparsoftware.jdbc.*;

public class DiscountStoreJdbcImpl implements DiscountStore {
    private DiscountStoreJdbcQueryBuilder queryBuilder =
        new DiscountStoreJdbcQueryBuilder();
    private DiscountStoreJdbcMapper mapper =
        new DiscountStoreJdbcMapper();

    private JdbcQueryExecuter queryExecuter;
    private Connection connection;

    public DiscountStoreJdbcImpl(Connection connection) {
        this.connection = connection;
        queryExecuter = new JdbcQueryExecuter(connection);
    }

    public Set findExpiresNoLaterThan(Date expiryDate) {
        PreparedStatementData selectStatementData =
            new PreparedStatementData(
                "select * from "
                    + "catalog.discount, catalog.discountDefinition "
                    + "where ("
                    + "catalog.discount.discountDefinitionId = catalog.discountDefinition.discountDefinitionId "
                    + "AND toDate <= ?"
                    + ")",
                Collections.singletonList(
                    new java.sql.Date(expiryDate.getTime())));

        return new HashSet(queryExecuter
            .executeSelectStatement(
                selectStatementData,
                new JdbcRowMapper() {

            public Object makeDomainObject(ResultSet row)
                throws SQLException {

                return mapper.makeDiscount(row);
            }
        }));
    }

    public void removeExpiredDiscountAsOf(Date expiryDate) {
        PreparedStatementData removeExpiredDiscountAsOfStatement =
            queryBuilder.getPreparedStatementData(
                "removeExpiredDiscountAsOf",
                Collections.singletonList(expiryDate));

        queryExecuter.executeDeleteStatement(
            removeExpiredDiscountAsOfStatement);
    }

    public void suspendAllDiscounts() {
        PreparedStatementData preparedStatementData =
            queryBuilder.getPreparedStatementData(
                "suspendAllDiscounts",
                Collections.EMPTY_LIST);

        queryExecuter.executeUpdateStatement(preparedStatementData);
    }

}
