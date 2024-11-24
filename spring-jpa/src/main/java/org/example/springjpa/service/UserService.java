package org.example.springjpa.service;

import org.example.springjpa.model.User;
import org.example.springjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository employeeRepository;

    public List<User> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<User> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public User saveEmployee(User employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
