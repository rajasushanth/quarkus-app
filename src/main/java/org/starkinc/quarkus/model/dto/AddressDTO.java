package org.starkinc.quarkus.model.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class AddressDTO {
    private int buildingNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
