package com.challenge.endpoints;

import com.challenge.advice.ResourceNotFoundException;
import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    @Autowired
    AccelerationService accelerationService;

    @GetMapping("/{id}")
    ResponseEntity<Acceleration> getAccelerationById(@PathVariable("id") Long id) {
        return new ResponseEntity<Acceleration>(this.accelerationService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acceleration")), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Acceleration>> getAccelerationByCompanyId(@RequestParam(name = "companyId") Long id) {
        return new ResponseEntity<List<Acceleration>>(this.accelerationService.findByCompanyId(id), HttpStatus.OK);
    }
}
