package kg.kursamov.dao.impl;

import kg.kursamov.config.Configuration;
import kg.kursamov.dao.EmployeeDao;
import kg.kursamov.models.Employee;
import kg.kursamov.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {

    private final Connection connection = Configuration.getConnection();
    @Override
    public void createEmployee() {
        String query = """
              create table employees (
              id serial primary key,
              first_name varchar(255),
              last_name varchar(255),
              age int,
              email varchar(255),
              job_id int references jobs(id))
              """;
        try(Statement statement = connection.createStatement() ) {
            statement.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addEmployee(Employee employee) {
        String query = """
                insert into employees(first_name,last_name,age,email,job_id)
                values(?,?,?,?,?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setLong(5,employee.getJobId());
            int i = preparedStatement.executeUpdate();
            if (i > 0){
                System.out.println("Employee success added!");
            }else System.out.println("Invalid added!");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void dropTable(String tableName) {
        String query = "drop table " + tableName;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Таблица " + tableName + " успешно удаленно!");
        }catch (SQLException e){
            System.out.println("Ошшибка  при  удаления  таблица " + tableName);
        }

    }

    @Override
    public void cleanTable(String name) {
        String query = "delete from " + name;
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
            System.out.println("Таблица " + name + " успешно cleaned!");
        }catch (SQLException e){
            System.out.println("Ошшибка  при  cleanenie  таблица " + name);
        }


    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String query = """
                update employees
                set first_name = ?,
                last_name = ?,
                age = ?,
                email = ?
                where id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setLong(5,id);
            int i = preparedStatement.executeUpdate();
            if (i > 0){
                System.out.println("Запись о сотруднике с ID " + id + " успешно обновлена.");
            }else System.out.println("Не удалось обновить запись о сотруднике с ID " + id + ".");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();
        String query = """
                select * from employees
                """;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                Long jobId = resultSet.getLong("job_id");

                Employee employee = new Employee(firstName,lastName,age, email,jobId);
                employees.add(employee);
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }

    @Override
    public Employee findByEmail(String email) {
        String query = """
                select * from employees e where e.email = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    long id = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    int age = resultSet.getInt("age");
                    String emails = resultSet.getString("email");
                    Long jobId = resultSet.getLong("job_id");
                    return new Employee(id,firstName,lastName,age, emails,jobId);
                }

            }

        }catch (SQLException e){
            System.out.println("Error from email search!");
        }
        return null;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee, Job>  employeeJobMap = new HashMap<>();
        String query = "select e.*, j.*, j.profession as position from Employees e join Jobs j on e.job_id = j.id where e.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    Employee employee = new Employee(
                            resultSet.getLong("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getInt("age"),
                            resultSet.getString("email"),
                            resultSet.getLong("job_id")
                    );
                    Job job = new Job(
                            resultSet.getLong("id"),
                            resultSet.getString("position"),
                            resultSet.getString("profession"),
                            resultSet.getString("description"),
                            resultSet.getInt("experience"));
                    employeeJobMap.put(employee,job);
                }

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employeeJobMap;

    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employees = new ArrayList<>();

        String jobQuery = "select id from Jobs where positions = ?";

        try (PreparedStatement jobStatement = connection.prepareStatement(jobQuery)) {
            jobStatement.setString(1, position);

            try (ResultSet jobResultSet = jobStatement.executeQuery()) {
                while (jobResultSet.next()) {
                    Long jobId = jobResultSet.getLong("id");

                    String employeeQuery = "select * from Employees where job_id = ?";
                    try (PreparedStatement employeeStatement = connection.prepareStatement(employeeQuery)) {
                        employeeStatement.setLong(1, jobId);

                        try (ResultSet employeeResultSet = employeeStatement.executeQuery()) {
                            while (employeeResultSet.next()) {
                                Employee employee = new Employee(
                                        employeeResultSet.getLong("id"),
                                        employeeResultSet.getString("first_name"),
                                        employeeResultSet.getString("last_name"),
                                        employeeResultSet.getInt("age"),
                                        employeeResultSet.getString("email"),
                                        employeeResultSet.getLong("job_id")
                                );
                                employees.add(employee);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return employees;

    }
}
