package ua.com.alevel.persistence.enums;

public enum MusicianGroup {

    SOLO("alone"),
    DUO("two"),
    TRIO("three"),
    QUARTET("four"),
    QUINTET("five"),
    ORCHESTRA("many");

    private String name;

    MusicianGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
