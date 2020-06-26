package org.starkinc.quarkus.mapper;

import org.apache.commons.lang3.RandomStringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.starkinc.quarkus.model.dto.AddressDTO;

import java.util.Random;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    Random random = new Random();

    default AddressDTO mapAddressToAddressDTO() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setBuildingNumber(Math.abs(random.nextInt(250)));
        addressDTO.setStreetAddress(RandomStringUtils.randomAlphabetic(7));
        addressDTO.setCity(RandomStringUtils.randomAlphabetic(5));
        addressDTO.setState(RandomStringUtils.randomAlphabetic(5));
        addressDTO.setCountry(RandomStringUtils.randomAlphabetic(6));
        addressDTO.setZipcode(RandomStringUtils.randomNumeric(5));
        return addressDTO;
    }
}
