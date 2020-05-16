package com.challenge.service.interfaces;

import com.challenge.entity.Submission;
import com.challenge.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SubmissionService implements SubmissionServiceInterface {
    @Autowired
    private SubmissionRepository sRepository;

    @Override
    public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
        BigDecimal higherScore = this.sRepository.findHigherScoreByChallengeId(challengeId);
        if(higherScore == null) {
            return BigDecimal.ZERO;
        }
        return higherScore;
    }

    @Override
    public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
        return sRepository.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
    }

    @Override
    public Submission save(Submission object) {
        return sRepository.save(object);
    }
}
