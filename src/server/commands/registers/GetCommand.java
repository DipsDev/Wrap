package server.commands.registers;

import server.WrapDB;
import server.commands.Command;
import server.commands.RegisteredCommand;
import server.models.datatypes.DataType;
import server.models.datatypes.Null;
import server.models.datatypes.SimpleError;
import server.models.datatypes.SimpleString;
import server.models.storetypes.StoreType;
import server.models.storetypes.StringStore;
@RegisteredCommand
public class GetCommand implements Command {
    @Override
    public String getName() {
        return "get";
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
            return new SimpleError("ERR Get command only supports strings");
        }
        return data.prepare();
    }
}
