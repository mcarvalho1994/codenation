package com.challenge.service.interfaces;

import com.challenge.entity.User;
import com.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository uRepository;

    @Override
    public Optional<User> findById(Long userId) {
        return uRepository.findById(userId);
    }

    @Override
    public List<User> findByAccelerationName(String name) {
        return uRepository.findByAccelerationName(name);
    }

    @Override
    public List<User> findByCompanyId(Long companyId) {
        return uRepository.findByCompanyId(companyId);
    }

    @Override
    public User save(User object) {
        return uRepository.save(object);
    }
}
