package org.example.springjpa.controller;

import org.example.springjpa.model.User;
import org.example.springjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService employeeService;

    @GetMapping
    public List<User> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<User> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public User createEmployee(@RequestBody User employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public User updateEmployee(@PathVariable Long id, @RequestBody User employeeDetails) {
        User employee = employeeService.getEmployeeById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setUsername(employeeDetails.getUsername());
        employee.setPassword(employeeDetails.getPassword());
        employee.setEmail(employeeDetails.getEmail());
        employee.setRole(employeeDetails.getRole());

        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
