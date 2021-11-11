package shared;

public enum COLUMN {
    ID("id"), NAME("name"), STOCK("stock"), PRICE("price");

    private String value;

    COLUMN(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
