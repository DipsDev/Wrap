package server.models.storetypes;

public interface PrimitiveStoreType<T> {
    /***
     * Puts the value onto the store
     * @param value
     */
    void put(T value);

    /***
     * Gets the value by name
     * @param name the name to be searched for
     * @return the found data
     */
    T get(String name);
}
