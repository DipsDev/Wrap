package server.commands.registers;

import server.WrapDB;
import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.DataType;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;
import server.models.storetypes.StoreType;
import server.models.storetypes.StringStore;
import server.utils.Errors;

import java.util.Arrays;

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
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }
        if (!WrapDB.getInstance().exists(args[1])) {
            WrapDB.getInstance().create(args[1], new StringStore(args[2]));
            return new SimpleString("OK");
        }
        StoreType get = WrapDB.getInstance().get(args[1]);
        if (get instanceof StringStore ss) {
            ss.put(args[2]);
        } else {
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }
        return new SimpleString("OK");
    }
}
