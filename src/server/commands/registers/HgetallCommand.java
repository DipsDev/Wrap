package server.commands.registers;

import server.WrapDB;
import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.DataType;
import server.models.datatypes.Null;
import server.models.datatypes.SimpleError;
import server.models.storetypes.HashStore;
import server.models.storetypes.StoreType;
@RegisteredCommand
public class HgetallCommand implements Command {
    @Override
    public String getName() {
        return "hgetall";
    }

    @Override
    public int getArgsCount() {
        return 1;
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
        return store.prepare();
    }
}
