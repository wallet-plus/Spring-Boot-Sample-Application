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


    // Search employees by first name (partial match on the first two characters)
    public List<User> searchEmployeesByFirstName(String firstName) {
        return employeeRepository.findByFirstNameStartingWith(firstName);
    }

    // Search employees by mobile number (partial match on the first two characters)
    public List<User> searchEmployeesByMobile(String mobile) {
        return employeeRepository.findByMobileStartingWith(mobile);
    }

    // Search employees by both first name and mobile (partial match on both)
    public List<User> searchEmployeesByFirstNameAndMobile(String firstName, String mobile) {
        return employeeRepository.findByFirstNameStartingWithAndMobileStartingWith(firstName, mobile);
    }

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
