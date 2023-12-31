package server.commands.registers;

import server.WrapDB;
import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;
import server.models.storetypes.HashStore;
import server.models.storetypes.IntegerStore;
import server.models.storetypes.StoreType;
import server.models.storetypes.StringStore;
@RegisteredCommand
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
            StoreType<?> newStore = StoreType.Factory.createStoreType(args[3]);
            if (newStore == null) {
                return new SimpleError("NOTYPE Unknown type was given");
            }
            HashStore store = new HashStore();
            store.put(args[2], newStore);
            WrapDB.getInstance().create(args[1], store);
            return new SimpleString("OK");
        }
        if (!(data instanceof HashStore)) {
            return new SimpleError("NOTYPE Hset commands supports only hashmaps");
        }
        HashStore store = (HashStore) data;
        StoreType<?> newStore = StoreType.Factory.createStoreType(args[3]);
        if (newStore == null) {
            return new SimpleError("NOTYPE Unknown type was given");
        }
        store.put(args[2], newStore);
        return new SimpleString("OK");


    }
}
