package main.client;

public enum DataTypes {
    SimpleString('+'),
    SimpleError('-'),
    Integer(':'),
    BulkString('$'),
    Array('*'),
    Null('_'),
    Boolean('#'),
    Double(','),
    BigNumber('('),
    BulkError('!'),
    Set('=');


    private char sign;

    private DataTypes(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }
}
