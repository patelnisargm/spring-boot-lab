package com.nmp.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "Eazy Bytes"
    )
    @NonNull
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "tutor@eazybytes.com"
    )
    @NonNull
    @Email
    @NotBlank(message = "Email cannot be blank.")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @NonNull
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @NotBlank(message = "Mobile Number cannot be blank.")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountDto accountDto;

}
