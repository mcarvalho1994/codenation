package com.challenge.endpoints;

import com.challenge.advice.ResourceNotFoundException;
import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/{id}")
    ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
        return new ResponseEntity<Company>(this.companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanyByUserOrAccelerationId(
            @RequestParam(name = "userId", required = false) Optional<Long> userId,
            @RequestParam(name = "accelerationId", required = false) Optional<Long> accelerationId) {
        if (userId.isPresent()) {
            return new ResponseEntity(companyService.findByUserId(userId.get()), HttpStatus.OK);
        } else if (accelerationId.isPresent()) {
            return new ResponseEntity(companyService.findByAccelerationId(accelerationId.get()), HttpStatus.OK);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
