package ua.com.alevel.persistence.entity.label;

import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "labels")
public class Label extends BaseEntity {

    @Column(name = "label_name")
    private String labelName;

    public Label(){
        super();
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
