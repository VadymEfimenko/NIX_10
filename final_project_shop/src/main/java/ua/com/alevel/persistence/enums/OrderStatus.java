package ua.com.alevel.persistence.enums;

public enum OrderStatus {

    ACCEPTED("Принят"),
    IN_PROCESS ("В процессе"),
    SOLVE("Оплачен");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
