package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.label.Label;

public class LabelResponseDto extends ResponseDto {

    private String name;
    private Integer releaseCount;

    public LabelResponseDto() {

    }

    public LabelResponseDto(Label label){
        setId(label.getId());
        setCreated(label.getCreated());
        setUpdated(label.getUpdated());
        setVisible(true);
        this.name = label.getLabelName();
        this.releaseCount = label.getReleases().size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReleaseCount() {
        return releaseCount;
    }

    public void setReleaseCount(Integer releaseCount) {
        this.releaseCount = releaseCount;
    }

    @Override
    public String toString() {
        return "LabelResponseDto{" +
                "name='" + name + '\'' +
                ", releaseCount=" + releaseCount +
                '}';
    }
}
