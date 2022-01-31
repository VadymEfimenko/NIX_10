package ua.com.alevel.persistence.type;

public enum OrderStatus {
    MADE("принят"), IN_PROCESS("в процессе"), DONE("совершен");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
