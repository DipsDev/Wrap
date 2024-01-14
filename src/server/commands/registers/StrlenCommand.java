package server.commands.registers;

import server.WrapDB;
import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.DataType;
import server.models.datatypes.Integer;
import server.models.datatypes.Null;
import server.models.datatypes.SimpleError;
import server.models.storetypes.StoreType;
import server.models.storetypes.StringStore;
import server.utils.Errors;

@RegisteredCommand
public class StrlenCommand implements Command {
    @Override
    public String getName() {
        return "strlen";
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
        if (!(data instanceof StringStore)) {
            return new SimpleError(Errors.WRONG_TYPE_PROVIDED);
        }
        return new Integer(((StringStore) data).get().length());
    }
}
