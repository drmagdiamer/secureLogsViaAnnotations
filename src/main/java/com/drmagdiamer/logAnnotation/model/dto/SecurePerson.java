package com.drmagdiamer.logAnnotation.model.dto;

import com.drmagdiamer.logAnnotation.secureLogAnnotation.annotaion.Encrypted;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.annotaion.Masked;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.annotaion.Unrestricted;
import com.drmagdiamer.logAnnotation.secureLogAnnotation.core.DtoLogSecurity;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurePerson extends DtoLogSecurity {
  @Unrestricted(order = 2)
  private String firstName;

  @Masked(order = 3)
  private String lastName;

  @Masked(order = 6, maskName = "ccMasker")
  private String dateOfBirth;

  @Masked(order = 4, maskName = "ssnMasker")
  private String ssn;

  @Encrypted(order = 1)
  private String customerId;
}
