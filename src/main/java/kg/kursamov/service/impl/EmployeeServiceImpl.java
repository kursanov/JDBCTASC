package kg.kursamov.service.impl;

import kg.kursamov.dao.EmployeeDao;
import kg.kursamov.dao.impl.EmployeeDaoImpl;
import kg.kursamov.models.Employee;
import kg.kursamov.models.Job;
import kg.kursamov.service.EmployeeService;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao dao = new EmployeeDaoImpl();
    @Override
    public void createEmployee() {
        dao.createEmployee();

    }

    @Override
    public void addEmployee(Employee employee) {
        dao.addEmployee(employee);


    }

    @Override
    public void dropTable(String tableName) {
        dao.dropTable(tableName);

    }

    @Override
    public void cleanTable(String nameTable) {
        dao.cleanTable(nameTable);

    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        dao.updateEmployee(id,employee);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    @Override
    public Employee findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return dao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return dao.getEmployeeByPosition(position);
    }
}
