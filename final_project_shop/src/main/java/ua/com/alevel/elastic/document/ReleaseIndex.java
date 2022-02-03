package ua.com.alevel.elastic.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "releaseindex")
public class ReleaseIndex {

    @Id
    private String id;

    @Field(name = "name", type = FieldType.Text)
    private String name;
}
