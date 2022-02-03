package ua.com.alevel.persistence.enums;

public enum DigitalMedia {

    VINYL("Пластинка"),
    CD("Диск"),
    CASSETTE("Кассета"),
    STREAMING("Стриминг");

    private String name;

    DigitalMedia(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
