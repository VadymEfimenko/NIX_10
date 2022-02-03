package ua.com.alevel.persistence.enums;

public enum Format {

    MP3("MPEG-1/2/2.5 Layer 3"),
    MP4("MPEG-4 Part 14"),
    FLAC("Free Lossless Audio Codec"),
    WAV("Waveform audio format"),
    WMA("Windows Media Audio");

    private String name;

    Format(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
