package com.mhj.sahwk;

import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
  private PiCalculator piCalculator;

  AppController(PiCalculator piCalculator){
    this.piCalculator = piCalculator;
  }

  @GetMapping("/login")
  ResponseEntity<Boolean> login(HttpSession session) {
    session.setAttribute("login", Boolean.TRUE);
    return ResponseEntity.ok(Boolean.TRUE);
  }

  @GetMapping("/pi")
  ResponseEntity<Long> pi(HttpSession session) {
    System.out.println(session.getAttribute("login"));
    if (session.getAttribute("login") == null || !(boolean) (session.getAttribute("login"))) {
      return new ResponseEntity<Long>(-1L, HttpStatus.UNAUTHORIZED);
    }
    long startTime = System.currentTimeMillis();
    this.piCalculator.calculatePi(10_000_000);
    long endTime = System.currentTimeMillis();
    return ResponseEntity.ok(Long.valueOf(endTime - startTime));
  }
}
