package fedosova_p.constractioncompany.model.enums;

public enum Type {
    sale("продажа"),
    rent("аренда");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
