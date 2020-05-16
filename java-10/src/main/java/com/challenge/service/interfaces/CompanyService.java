package com.challenge.service.interfaces;

import com.challenge.entity.Company;
import com.challenge.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements CompanyServiceInterface {

    @Autowired
    CompanyRepository cRepository;

    @Override
    public Optional<Company> findById(Long id) {
        return cRepository.findById(id);
    }

    @Override
    public List<Company> findByAccelerationId(Long accelerationId) {
        return cRepository.findByAccelerationId(accelerationId);
    }

    @Override
    public List<Company> findByUserId(Long userId) {
        return cRepository.findByUserId(userId);
    }

    @Override
    public Company save(Company object) {
        return cRepository.save(object);
    }
}
