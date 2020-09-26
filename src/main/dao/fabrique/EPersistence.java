package main.dao.fabrique;

public enum EPersistence {
    MYSQL(0), LISTEMEMORY(1);
    private int code;

    EPersistence(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public EPersistence getTypeFromCode(int code) {
        if (code == 0) {
            return MYSQL;
        }
        return LISTEMEMORY;
    }
}
