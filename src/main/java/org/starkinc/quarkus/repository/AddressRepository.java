package org.starkinc.quarkus.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.starkinc.quarkus.model.entity.AddressEntity;

import java.util.List;

@Repository
public class AddressRepository implements PanacheMongoRepository<AddressEntity> {

    public void save(AddressEntity addressEntity) {
        addressEntity.persist();
    }

    public AddressEntity findAddressById(ObjectId id) {
        return findById(id);
    }

    public List<AddressEntity> findAddressByRowNum(Long rowNum) {
        return list("rowNum", rowNum);
    }

    private List<AddressEntity> findAllAddress() {
        return listAll();
    }
}
