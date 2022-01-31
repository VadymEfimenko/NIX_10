package ua.com.alevel.view.dto.request;

public class LabelRequestDto extends RequestDto {

    private String labelName;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return "LabelRequestDto{" +
                "labelName='" + labelName + '\'' +
                '}';
    }
}
