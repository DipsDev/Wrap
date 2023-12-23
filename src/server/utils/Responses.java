package server.utils;

public enum Responses {
    OK("+OK\r\n"),
    COMMAND_NOT_FOUND("-ERR unknown command\r\n"),
    NOT_ENOUGH_ARGS("-ERR not enough args were provided\r\n");

    private final String parsed;

    private Responses(String parsed) {
        this.parsed = parsed;
    }

    public String getParsed() {
        return parsed;
    }
}
