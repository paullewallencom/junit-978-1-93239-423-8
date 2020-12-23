package junit.cookbook.coffee.jdbc.test;

import java.sql.*;
import java.util.Set;

import javax.sql.DataSource;

import junit.cookbook.coffee.data.CatalogStore;
import junit.cookbook.coffee.data.Product;

import com.diasparsoftware.store.DataStoreException;

public class CatalogStoreStoredProcedureImpl implements CatalogStore {
    private DataSource dataSource;
    private Connection connection;

    public CatalogStoreStoredProcedureImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CatalogStoreStoredProcedureImpl(Connection connection) {
        this.connection = connection;
    }

    public Set findBeansByName(String name) {
        throw new UnsupportedOperationException();
    }

    public Set findAllProducts() {
        throw new UnsupportedOperationException();
    }

    public void addProduct(Product toAdd) {
        CallableStatement addProductStatement = null;
        try {
            addProductStatement =
                connection.prepareCall("call addProduct(?, ?, ?)");

            addProductStatement.clearParameters();

            addProductStatement.setObject(1, toAdd.productId);
            addProductStatement.setObject(2, toAdd.coffeeName);
            addProductStatement.setObject(
                3,
                new Integer(toAdd.unitPrice.inCents()));

            addProductStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataStoreException(e);
        }
        finally {
            try {
                if (addProductStatement != null)
                    addProductStatement.close();
            }
            catch (SQLException tooLate) {
            }
        }
    }
}
