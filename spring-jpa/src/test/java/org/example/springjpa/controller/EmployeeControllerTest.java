package org.example.springjpa.controller;

import org.example.springjpa.model.Employee;
import org.example.springjpa.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @MockBean  // Use @MockBean to mock the EmployeeService
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        // Arrange
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setUsername("john.doe");
        employee1.setEmail("john.doe@example.com");
        employee1.setRole("ADMIN");

        when(employeeService.getAllEmployees()).thenReturn(List.of(employee1));

        // Act & Assert
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("john.doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].role").value("ADMIN"));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        // Arrange
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setUsername("john.doe");
        employee.setEmail("john.doe@example.com");
        employee.setRole("ADMIN");

        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee));

        // Act & Assert
        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("john.doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    public void testCreateEmployee() throws Exception {
        // Arrange
        Employee employee = new Employee();
        employee.setUsername("john.doe");
        employee.setEmail("john.doe@example.com");
        employee.setRole("ADMIN");

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        // Act & Assert
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"john.doe\", \"email\": \"john.doe@example.com\", \"role\": \"ADMIN\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("john.doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        // Arrange
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1L);
        existingEmployee.setUsername("john.doe");
        existingEmployee.setEmail("john.doe@example.com");
        existingEmployee.setRole("ADMIN");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1L);
        updatedEmployee.setUsername("john.doe.updated");
        updatedEmployee.setEmail("john.doe.updated@example.com");
        updatedEmployee.setRole("USER");

        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(updatedEmployee);

        // Act & Assert
        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"john.doe.updated\", \"email\": \"john.doe.updated@example.com\", \"role\": \"USER\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john.doe.updated"))
                .andExpect(jsonPath("$.email").value("john.doe.updated@example.com"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        // Arrange
        doNothing().when(employeeService).deleteEmployee(1L);

        // Act & Assert
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());
    }
}
