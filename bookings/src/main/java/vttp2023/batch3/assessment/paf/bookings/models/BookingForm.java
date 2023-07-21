package vttp2023.batch3.assessment.paf.bookings.models;

import java.sql.Date;

import jakarta.validation.constraints.Email;
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
public class BookingForm {

    @NotNull(message="Please enter name")
    @NotEmpty(message="Please enter name")
    private String name;

    @Email(message="Please enter a valid email")
    @NotEmpty(message="Please enter your email")
    private String email;

    @NotNull(message="Please choose an arrival date")
    @NotEmpty(message="Please choose an arrival date")
    private Date arrival;

    @NotNull(message="Please enter the duration of your stay")
    private Integer stay;
}
