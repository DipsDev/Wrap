package server.models.storetypes;

public interface PrimitiveStoreType<T> extends StoreType<T> {
    /***
     * Puts the value onto the store
     * @param value
     */
    void put(T value);

    /***
     * Gets the value by name
     * @return the found data
     */
    T get();
}
