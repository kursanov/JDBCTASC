package kg.kursamov.service;

import kg.kursamov.models.Employee;
import kg.kursamov.models.Job;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    void createEmployee();
    void addEmployee(Employee employee);
    void dropTable(String tableName);
    void cleanTable(String nameTable);
    void updateEmployee(Long id,Employee employee);
    List<Employee> getAllEmployees();
    Employee findByEmail(String email);
    Map<Employee, Job> getEmployeeById(Long employeeId);
    List<Employee> getEmployeeByPosition(String position);
}
