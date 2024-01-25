package main.server.commands.registers;

import main.server.WrapDB;
import main.server.commands.Command;
import main.server.commands.RegisteredCommand;
import main.server.models.datatypes.DataType;
import main.server.models.datatypes.Null;
import main.server.models.datatypes.SimpleError;
import main.server.models.storetypes.HashStore;
import main.server.models.storetypes.StoreType;
import main.server.utils.Errors;

@RegisteredCommand
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
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }
        HashStore store = (HashStore) data;
        return store.get(args[2]).prepare();
    }
}
