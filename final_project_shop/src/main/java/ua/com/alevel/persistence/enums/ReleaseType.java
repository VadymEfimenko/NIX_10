package ua.com.alevel.persistence.enums;

public enum ReleaseType {
    SP("Single play"),
    LP("Long-playing"),
    EP("Extended Play"),
    ALBUM("record album"),
    VA("Various Artist"),
    UR("Unreleased");

    private String name;

    ReleaseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
