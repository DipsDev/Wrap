package server.commands;

import server.WrapDB;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;
import server.models.storetypes.HashStore;
import server.models.storetypes.StoreType;
import server.models.storetypes.StringStore;

public class HsetCommand implements Command {
    @Override
    public String getName() {
        return "hset";
    }

    @Override
    public int getArgsCount() {
        return 3;
    }

    @Override
    public DataType execute(String[] args) {
        StoreType data = WrapDB.getInstance().get(args[1]);
        if (data == null) {
            HashStore store = new HashStore();
            store.put(args[2], new StringStore(args[3]));
            WrapDB.getInstance().create(args[1], store);
            return new SimpleString("OK");
        }
        if (!(data instanceof HashStore)) {
            return new SimpleError("NOTYPE Hset commands supports only hashmaps");
        }
        HashStore store = (HashStore) data;
        store.put(args[2], new StringStore(args[3]));
        return new SimpleString("OK");


    }
}
