package org.starkinc.quarkus.model.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.starkinc.quarkus.model.dto.AddressDTO;

@Data
@EqualsAndHashCode(callSuper = true)
@MongoEntity(collection = "address")
public class AddressEntity extends PanacheMongoEntity {

    @BsonProperty("row_num")
    private Long rowNum;
    private AddressDTO address;
}
