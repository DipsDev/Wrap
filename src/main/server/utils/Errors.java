package main.server.utils;

public enum Errors {
    COMMAND_NOT_FOUND("UKNOWNCOMMAND unknown command\r\n"),

    WRONG_TYPE_PROVIDED("WRONGTYPE Operation expected different input type"),
    NOT_ENOUGH_ARGS("ERR not enough args were provided\r\n");


    private final String parsed;

    private Errors(String parsed) {
        this.parsed = parsed;
    }

    public String getParsed() {
        return parsed;
    }
}
