package kg.kursamov;

import kg.kursamov.enums.Description;
import kg.kursamov.enums.Position;
import kg.kursamov.enums.Profession;
import kg.kursamov.models.Employee;
import kg.kursamov.models.Job;
import kg.kursamov.service.EmployeeService;
import kg.kursamov.service.JobService;
import kg.kursamov.service.impl.EmployeeServiceImpl;
import kg.kursamov.service.impl.JobServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        JobService jobService = new JobServiceImpl();
//        jobService.createPositionType();
//        jobService.createDescriptionType();
//        jobService.createProfessionType();
//        jobService.createJobTable();
//        jobService.addJob(new Job(Position.valueOf("INSTRUCTOR"), Profession.valueOf("JAVA"), Description.valueOf("BACKEND_DEVELOPER"),3));
//        jobService.addJob(new Job(Position.INSTRUCTOR,Profession.JAVA,Description.BACKEND_DEVELOPER,3));
//        jobService.addJob(new Job(Position.MENTOR,Profession.JAVASCRIPT,Description.FRONTED_DEVELOPER,6));
//        jobService.addJob(new Job(Position.valueOf("MENTOR"), Profession.valueOf("JAVASCRIPT"), Description.valueOf("FRONTED_DEVELOPER"),3));
//        jobService.addJob(new Job(Position.valueOf("MENTOR"), Profession.valueOf("JAVASCRIPT"), Description.valueOf("FRONTED_DEVELOPER"),4));
//        jobService.addJob(new Job(Position.valueOf("MENTOR"), Profession.valueOf("JAVASCRIPT"), Description.valueOf("FRONTED_DEVELOPER"),5));
//        System.out.println(jobService.getJobById(1L));
//        System.out.println(jobService.sortByExperience("desc"));
//        jobService.deleteDescriptionColumn();
//        System.out.println(jobService.getJobByEmployeeId(5L));
        EmployeeService employeeService = new EmployeeServiceImpl();
//        employeeService.assignJobsToEmployee();
//        employeeService.createEmployee();
//        employeeService.addEmployee(new Employee("Kursan","Asanov",22,"ACAN@gmail.com",2L));
//        employeeService.addEmployee(new Employee("Azamat", "Baimatov", 33, "z@gmail.com",1L));
//        employeeService.dropTable("jobs");
//        employeeService.cleanTable("employees");
//        employeeService.updateEmployee(4L,new Employee("Zaripbek","Kursanov",22,"z@gmail.com"));
//        System.out.println(employeeService.getAllEmployees());
//        System.out.println(employeeService.findByEmail("ACAN@gmail.com"));
//        System.out.println(employeeService.getEmployeeById(5L));
//        System.out.println(employeeService.getEmployeeByPosition("INSTRUCTOR"));


    }
}
