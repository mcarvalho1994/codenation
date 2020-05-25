package com.challenge.endpoints;


import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @Autowired
    CandidateMapper candidateMapper;

    @GetMapping("/{id}/{acceleration}/{company}")
    ResponseEntity<CandidateDTO> findById(@PathVariable("id") Long userId,
                                          @PathVariable("acceleration") Long accelerationId,
                                          @PathVariable("company") Long companyId) {
        Optional<Candidate> optionalCandidate = this.candidateService.findById(userId, companyId, accelerationId);
        if(optionalCandidate.isPresent()) {
            return new ResponseEntity<CandidateDTO>(candidateMapper.map(optionalCandidate.get()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<List<CandidateDTO>> getCandidateByCompanyOrAcceleration(
            @RequestParam(value = "accelerationId", required = false) Optional<Long> accelerationId,
            @RequestParam(value = "companyId", required = false) Optional<Long> companyId) {
        if(accelerationId.isPresent()) {
            return new ResponseEntity<List<CandidateDTO>>(candidateMapper.map(
                    this.candidateService.findByAccelerationId(accelerationId.get())), HttpStatus.OK);
        } else if(companyId.isPresent()) {
            return new ResponseEntity<List<CandidateDTO>>(candidateMapper.map(
                    this.candidateService.findByCompanyId(companyId.get())), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
