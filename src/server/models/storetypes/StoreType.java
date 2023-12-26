package server.models.storetypes;

import server.models.datatypes.DataType;

public interface StoreType<T> {

    /***
     * Prepares the store as sendable datatype
     * @return The store as a sendable datatype
     */
    DataType prepare();

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
