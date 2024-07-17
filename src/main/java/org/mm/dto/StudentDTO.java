package org.mm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mm.annotations.EmployeeRoleValidation;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO
{
    private Long id;

    @NotNull(message = "Required Field in Student : name")
    @Size(min = 3, max = 10 ,message = "number of characters in name should be n the range : [3,10]")
    private String name;

    @Email(message = "Email should be a valid email address")
    private String email;

    @Max(value = 80, message = "age cannot be greater than 80")
    @Min(value = 18, message = "age cannot be less than 18")
    private Integer age;

    @NotBlank(message = "The role of the student cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "The role of the student can be ADMIN OR USER")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "salary of the student should be not null")
    @Positive(message = "Salary of Student should be positive")
    @Digits(integer = 6, fraction = 2, message = "Salary of Student can be xxxxxx.yy")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Integer salary;

    @PastOrPresent(message = "Date of joining field in student cannot be in future")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;

}
