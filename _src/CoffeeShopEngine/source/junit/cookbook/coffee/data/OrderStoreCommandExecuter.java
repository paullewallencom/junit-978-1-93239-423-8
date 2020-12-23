package junit.cookbook.coffee.data;

public interface OrderStoreCommandExecuter {
    void execute(
        OrderStore orderStore,
        OrderStoreCommand orderStoreCommand,
        String exceptionMessage);
}
