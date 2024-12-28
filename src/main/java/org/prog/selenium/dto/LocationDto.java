package org.prog.selenium.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {
    private StreetDto street;
    private String city;
    private String state;
    private String country;
    private int postcode;
    private TimezoneDto timezone;
}
