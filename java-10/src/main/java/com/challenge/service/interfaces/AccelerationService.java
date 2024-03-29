package com.challenge.service.interfaces;

import com.challenge.entity.Acceleration;
import com.challenge.repository.AccelerationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccelerationService implements AccelerationServiceInterface {
    @Autowired
    AccelerationRepository aRepository;


    @Override
    public Optional<Acceleration> findById(Long id) {
        return aRepository.findById(id);
    }

    @Override
    public List<Acceleration> findByCompanyId(Long companyId) {
        return aRepository.findByCompanyId(companyId);
    }

    @Override
    public Acceleration save(Acceleration object) {
        return aRepository.save(object);
    }
}
