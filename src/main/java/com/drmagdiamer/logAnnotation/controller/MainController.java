package com.drmagdiamer.logAnnotation.controller;

import com.drmagdiamer.logAnnotation.model.dto.Person;
import com.drmagdiamer.logAnnotation.model.dto.SecurePerson;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/example")
public class MainController {
  @PostMapping("/processPerson")
  public String processPerson(@RequestBody(required = true) Person person) {
    log.info("Processing Person " + person);

    return person.toString();
  }

  @PostMapping("/processSecurePerson")
  public String processSecurePerson(@RequestBody(required = true) SecurePerson person) {
    log.info("Processing Person " + person);

    return person.toString();
  }
}
