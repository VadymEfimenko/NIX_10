package ua.com.alevel.view.dto.response.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public abstract class ResponseDto {

    private Long id;
    private Date created;
    private Date updated;
    private Boolean visible;
}
