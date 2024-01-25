package main.server.commands.registers;

import main.server.WrapDB;
import main.server.commands.Command;
import main.server.commands.RegisteredCommand;
import main.server.models.datatypes.DataType;
import main.server.models.datatypes.SimpleError;
import main.server.models.datatypes.SimpleString;
import main.server.models.storetypes.StoreType;
import main.server.models.storetypes.list.ListStore;
import main.server.utils.Errors;

@RegisteredCommand
public class LpushCommand implements Command {
    @Override
    public String getName() {
        return "lpush";
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public DataType execute(String[] args) {
        StoreType<?> type = WrapDB.getInstance().get(args[1]);
        if (type == null) {
            StoreType<?> created = StoreType.Factory.createStoreType(args[2]);
            ListStore newList = new ListStore();
            newList.pushStart(created);
            WrapDB.getInstance().create(args[1], newList);
            return new SimpleString("OK");
        }
        if (!(type instanceof ListStore list)) {
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }

        StoreType<?> created = StoreType.Factory.createStoreType(args[2]);
        list.pushStart(created);
        return new SimpleString("OK");

    }
}
