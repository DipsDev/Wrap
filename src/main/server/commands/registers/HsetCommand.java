package main.server.commands.registers;

import main.server.WrapDB;
import main.server.commands.Command;
import main.server.commands.RegisteredCommand;
import main.server.models.datatypes.DataType;
import main.server.models.datatypes.SimpleError;
import main.server.models.datatypes.SimpleString;
import main.server.models.storetypes.HashStore;
import main.server.models.storetypes.StoreType;
import main.server.utils.Errors;

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
                return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
            }
            HashStore store = new HashStore();
            store.put(args[2], newStore);
            WrapDB.getInstance().create(args[1], store);
            return new SimpleString("OK");
        }
        if (!(data instanceof HashStore)) {
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }
        HashStore store = (HashStore) data;
        StoreType<?> newStore = StoreType.Factory.createStoreType(args[3]);
        if (newStore == null) {
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }
        store.put(args[2], newStore);
        return new SimpleString("OK");


    }
}
