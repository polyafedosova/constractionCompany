package fedosova_p.constractioncompany.model.enums;

public enum Status {
    done("завершен"),
    in_progress("в процессе"),
    fail("сорван");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
