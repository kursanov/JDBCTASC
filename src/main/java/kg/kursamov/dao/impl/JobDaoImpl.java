package kg.kursamov.dao.impl;

import kg.kursamov.config.Configuration;
import kg.kursamov.dao.JobDao;
import kg.kursamov.enums.Description;
import kg.kursamov.enums.Position;
import kg.kursamov.enums.Profession;
import kg.kursamov.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    private final Connection connection = Configuration.getConnection();




    @Override
    public void createPositionType() {
        String queryType = """
                create type positions as enum ( 'MENTOR','MANAGEMENT','INSTRUCTOR');
                """;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryType);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createDescriptionType() {
        String queryFromDes = """
                create type description as  enum('BACKEND_DEVELOPER', 'FRONTED_DEVELOPER');
                """;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryFromDes);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createProfessionType() {
        String queryFromProf = """
                create type profession as enum ('JAVA','JAVASCRIPT'); 
                """;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(queryFromProf);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void createJobTable() {
        String query = """
            create table jobs(
            id serial primary key ,
            positions varchar,
            profession varchar,
            description varchar,
            experience int
            )
            """;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void addJob(Job job) {
        String query = """
            insert into jobs(positions, profession, description, experience)
            values (?,?,?,?)
            """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, String.valueOf(job.getPositions()));
            preparedStatement.setString(2,job.getProfession().toString() );
            preparedStatement.setString(3, job.getDescription().toString());
            preparedStatement.setInt(4, job.getExperience());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Job getJobById(Long jobId) {
        String query = """
                select * from jobs j where j.id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,jobId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    Long id = resultSet.getLong("id");
                    String position = resultSet.getString("positions");
                    String profession = resultSet.getString("profession");
                    String description = resultSet.getString("description");
                    int experience = resultSet.getInt("experience");
                    return new Job(id,position,profession,description, experience);
                }

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {

        List<Job> jobs = new ArrayList<>();
        String query = "select * from jobs order by experience " + ascOrDesc;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String position = resultSet.getString("positions");
                    String profession = resultSet.getString("profession");
                    String description = resultSet.getString("description");
                    int experience = resultSet.getInt("experience");
                    Job job = new Job(id, position, profession, description,experience);
                    jobs.add(job);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jobs;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = null;
        String query = "select j.* from Jobs j join Employees e on j.id = e.job_id where e.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    job = new Job(
                            resultSet.getLong("id"),
                            Position.valueOf(resultSet.getString("positions")),
                            Profession.valueOf(resultSet.getString("profession")),
                            Description.valueOf(resultSet.getString("description")),
                            resultSet.getInt("experience")

                    );

                }

            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String query = """
                alter table jobs drop description
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.executeUpdate();
            System.out.println("Успешно удалелено!");
        }catch (SQLException e){
            System.out.println(e.getMessage() + " Ощибка  при  удаление колумн ");
        }
    }
}
