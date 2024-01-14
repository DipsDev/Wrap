package server.commands.registers;

import server.WrapDB;
import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.Array;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.storetypes.IntegerStore;
import server.models.storetypes.StoreType;
import server.models.storetypes.list.ListStore;
import server.utils.Errors;

import java.util.ArrayList;
import java.util.List;

@RegisteredCommand
public class LrangeCommand implements Command {
    @Override
    public String getName() {
        return "lrange";
    }

    @Override
    public int getArgsCount() {
        return 3;
    }

    @Override
    public DataType execute(String[] args) {
        StoreType<?> type = WrapDB.getInstance().get(args[1]);
        if (type == null) {
            return new SimpleError("ERR List is null");
        }
        if (!IntegerStore.pattern.matcher(args[2]).matches() || !IntegerStore.pattern.matcher(args[3]).matches()) {
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);

        }
        if (!(type instanceof ListStore listStore)) {
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }
        List<StoreType<?>> list = listStore.range(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        return new Array(list);


    }
}
