package com.siyu.resourcing.job;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siyu.resourcing.temp.Temp;
import com.siyu.resourcing.temp.TempRepository;
import com.siyu.resourcing.temp.TempService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private TempService tempService;

    @Autowired
    private TempRepository tempRepository;

    public List<Job> getAll() {
        return this.jobRepository.findAll();
    }

    public Optional<Job> getById(Long id) {
        return this.jobRepository.findById(id);
    }

    public Job createJob(JobCreateDTO data) {
        String name = data.getName();
        String startDate = data.getStartDate();
        String endDate = data.getEndDate();
        System.out.println(name+startDate+endDate);
        if (data.getTempId() != null) {
            Long tempId = data.getTempId();
            Optional<Temp> temp = this.tempService.getById(tempId);
            if (temp.isPresent()) {
                Job newJob = new Job(name, startDate, endDate, temp.get());
                return this.jobRepository.save(newJob);
            }
            return null;
        }
        Job newJob = new Job(name, startDate, endDate);
        Job created = this.jobRepository.save(newJob);
        return created;
    }

    public Optional<Job> updateById(Long id, JobUpdateDTO data) {
        Optional<Job> foundJob = this.getById(id);
        if (foundJob.isPresent()) {
            Job toUpdate = foundJob.get();
            Long tempId = data.getTempId();
            Optional<Temp> temp = this.tempService.getById(tempId);
            if (temp.isPresent()) {
                Temp tempToUpdate = temp.get();
                toUpdate.setTemp(tempToUpdate);
                List<Job> jobList = tempToUpdate.getJobs();
                jobList.add(toUpdate);
                tempToUpdate.setJobs(jobList);
                this.tempRepository.save(tempToUpdate);
                // this.tempService.updateById(tempId, toUpdate);
                return Optional.of(this.jobRepository.save(toUpdate));
            }
            return null;
        }
	    return foundJob;
    }
}
