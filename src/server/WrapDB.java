package server;

import server.commands.Command;

import java.util.HashMap;

public class WrapDB {
    private static WrapDB instance;

    private WrapDB() {
    }


    public static WrapDB getInstance() {
        if (instance == null) {
            instance = new WrapDB();
        }
        return instance;
    }
}
