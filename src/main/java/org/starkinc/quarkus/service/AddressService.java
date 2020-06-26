package org.starkinc.quarkus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.starkinc.quarkus.model.dto.AddressDTO;
import org.starkinc.quarkus.model.entity.AddressEntity;
import org.starkinc.quarkus.repository.AddressRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.starkinc.quarkus.mapper.AddressMapper.INSTANCE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressDTO getRandomAddress() {
        log.debug("Generating fake address");
        return INSTANCE.mapAddressToAddressDTO();
    }

    public void saveAddress() {
        log.debug("Saving address");
        AddressDTO addressDTO = INSTANCE.mapAddressToAddressDTO();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setRowNum(Long.valueOf(RandomStringUtils.randomNumeric(1)));
        addressEntity.setAddress(addressDTO);
        addressRepository.save(addressEntity);
    }

    public List<AddressDTO> fetchRandomAddress() {
        log.debug("Fetching random address");
        List<AddressEntity> byRowNum = addressRepository.findAddressByRowNum(Long.valueOf(RandomStringUtils.randomNumeric(1)));
        if (Objects.nonNull(byRowNum) && !byRowNum.isEmpty()) {
            return byRowNum.stream().map(AddressEntity::getAddress).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }
}
