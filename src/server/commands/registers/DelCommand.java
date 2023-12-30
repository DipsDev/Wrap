package server.commands.registers;

import server.WrapDB;
import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;
import server.models.storetypes.StoreType;
import server.models.storetypes.StringStore;

@RegisteredCommand
public class DelCommand implements Command {
    @Override
    public String getName() {
        return "del";
    }

    @Override
    public int getArgsCount() {
        return 1;
    }

    @Override
    public DataType execute(String[] args) {
        StoreType<?> get = WrapDB.getInstance().remove(args[1]);
        return get.prepare();
    }
}
