package main.server.models.storetypes;

import main.server.models.datatypes.DataType;

public interface StoreType<T> {


    /***
     * Prepares the store as sendable datatype
     * @return The store as a sendable datatype
     */
    DataType prepare();




    class Factory {
        public static StoreType<?> createStoreType(String arg) {
            if (arg.charAt(0) == '"' && arg.charAt(arg.length() - 1) == '"') {
                return new StringStore(arg);
            }
            if (IntegerStore.pattern.matcher(arg).matches()) {
                return new IntegerStore(Integer.parseInt(arg));
            }
            return null;

        }
    }








}
