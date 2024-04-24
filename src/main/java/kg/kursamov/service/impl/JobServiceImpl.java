package kg.kursamov.service.impl;

import kg.kursamov.dao.JobDao;
import kg.kursamov.dao.impl.JobDaoImpl;
import kg.kursamov.models.Job;
import kg.kursamov.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {


    private final JobDao dao = new JobDaoImpl();


    @Override
    public void createPositionType() {
        dao.createPositionType();
    }

    @Override
    public void createDescriptionType() {
        dao.createDescriptionType();

    }

    @Override
    public void createProfessionType() {
        dao.createProfessionType();

    }

    @Override
    public void createJobTable() {
        dao.createJobTable();

    }

    @Override
    public void addJob(Job job) {
        dao.addJob(job);

    }

    @Override
    public Job getJobById(Long jobId) {
        return dao.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return dao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return dao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        dao.deleteDescriptionColumn();

    }
}
