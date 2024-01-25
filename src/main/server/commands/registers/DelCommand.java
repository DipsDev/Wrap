package main.server.commands.registers;

import main.server.WrapDB;
import main.server.commands.Command;
import main.server.commands.RegisteredCommand;
import main.server.models.datatypes.DataType;
import main.server.models.storetypes.StoreType;

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
