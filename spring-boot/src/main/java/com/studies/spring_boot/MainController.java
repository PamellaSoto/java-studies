package com.studies.spring_boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
// endpoint: http://localhost:8080/route/ --------
public class MainController {

  /**
   * HTTP methods
   * GET - Search data
   * POST - Send data
   * PUT - Alter data
   * DELETE - Delete data
   * PATCH - Alter part of data
   */

  @GetMapping
  public String message() {
    return "Message has been read";
  }
}
