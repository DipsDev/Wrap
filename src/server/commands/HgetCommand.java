package server.commands;

import server.WrapDB;
import server.models.datatypes.DataType;
import server.models.datatypes.Null;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;
import server.models.storetypes.HashStore;
import server.models.storetypes.StoreType;
import server.models.storetypes.StringStore;

public class HgetCommand implements Command {
    @Override
    public String getName() {
        return "hget";
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public DataType execute(String[] args) {
        StoreType data = WrapDB.getInstance().get(args[1]);
        if (data == null) {
            return new Null();
        }
        if (!(data instanceof HashStore)) {
            return new SimpleError("NOTYPE Hget commands supports only hashmaps");
        }
        HashStore store = (HashStore) data;
        return store.get(args[2]).prepare();
    }
}
