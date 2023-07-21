package vttp2023.batch3.assessment.paf.bookings.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    
    @NotNull(message="Please choose a country")
    @NotEmpty(message="Please choose a country")
    private String country;

    @Min(value=1, message="Number of persons has to be greater than or equal to 1")
    @Max(value=10, message="Number of persons has to be less than or equal to 10")
    @NotNull(message="Please enter an amount")
    private Integer numberOfPerson;

    @Min(value=1, message="Price amount needs to be greater than or equal to 1")
    @NotNull(message="Please enter a minimum amount")
    private Double min;

    @Max(value=10000, message="Price amount needs to be lesser than or equal to 10000")
    @NotNull(message="Please eneter a maximum amount")
    private Double max;
}
