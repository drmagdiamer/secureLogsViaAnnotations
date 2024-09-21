package com.drmagdiamer.logAnnotation.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String ssn;
    private String customerId;
}
