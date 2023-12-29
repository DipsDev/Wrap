package server.commands;

import server.WrapDB;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;
import server.models.storetypes.StoreType;
import server.models.storetypes.StringStore;

@RegisteredCommand
public class SetCommand implements Command {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public int getArgsCount() {
        return 2;
    }

    @Override
    public DataType execute(String[] args) {
        if (!args[2].startsWith("\"") || !args[2].endsWith("\"")) {
            return new SimpleError("ERR Set command supports only strings");
        }
        args[2] = args[2].substring(1, args[2].length() - 1);
        if (!WrapDB.getInstance().exists(args[1])) {
            WrapDB.getInstance().create(args[1], new StringStore(args[2]));
            return new SimpleString("OK");
        }
        StoreType get = WrapDB.getInstance().get(args[1]);
        if (get instanceof StringStore ss) {
            ss.put(args[2]);
        } else {
            return new SimpleError("ERR Set command only works on strings");
        }
        return new SimpleString("OK");
    }
}
