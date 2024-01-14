package server.commands.registers;

import server.WrapDB;
import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;
import server.models.storetypes.IntegerStore;
import server.models.storetypes.StoreType;
import server.models.storetypes.sortedlist.SortedListStore;
import server.utils.Errors;

@RegisteredCommand
public class ZaddCommand implements Command {
    @Override
    public String getName() {
        return "zadd";
    }

    @Override
    public int getArgsCount() {
        return 3;
    }

    @Override
    public DataType execute(String[] args) {
        StoreType<?>  currentData = WrapDB.getInstance().get(args[1]);
        if (!IntegerStore.pattern.matcher(args[2]).matches()) {
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }
        if (currentData == null) {
            SortedListStore list = new SortedListStore();
            list.put(Integer.parseInt(args[2]), StoreType.Factory.createStoreType(args[3]));
            WrapDB.getInstance().create(args[1], list);
            return new SimpleString("OK");
        }
        if (!(currentData instanceof SortedListStore)) {
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }
        ((SortedListStore) currentData).put(Integer.parseInt(args[2]), StoreType.Factory.createStoreType(args[3]));
        return new SimpleString("OK");


    }
}
