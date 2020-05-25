package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.entity.Submission;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    SubmissionService submissionService;

    @Autowired
    SubmissionMapper submissionMapper;

    @RequestMapping
    public ResponseEntity<List<SubmissionDTO>> getByChallengeIdAndAccelerationId(@RequestParam("challengeId") Long challengeId,
                                                                                 @RequestParam("accelerationId") Long accelerationId) {
        return new ResponseEntity<List<SubmissionDTO>>(submissionMapper
                .map(submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId)), HttpStatus.OK);
    }
}
