package kg.kursamov.service;

import kg.kursamov.models.Job;

import java.util.List;

public interface JobService {

    void createPositionType();
    void createDescriptionType();

    void createProfessionType();
    void createJobTable();
    void addJob(Job job);
    Job getJobById(Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
