package server.commands.registers;

import server.WrapDB;
import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;
import server.models.storetypes.StoreType;
import server.models.storetypes.list.List;
import server.models.storetypes.list.ListStore;
import server.utils.Errors;

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
