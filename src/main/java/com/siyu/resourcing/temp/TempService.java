package com.siyu.resourcing.temp;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siyu.resourcing.job.Job;
import com.siyu.resourcing.job.JobUpdateDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TempService {

    @Autowired
    private TempRepository tempRepository;

    public List<Temp> getAll() {
        return this.tempRepository.findAll();
    }

    public Optional<Temp> getById(Long id) {
        return this.tempRepository.findById(id);
    }

    public Temp createTemp(TempCreateDTO data) {
        String firstName = data.getFirstName();
        String lastName = data.getLastName();
        Temp newTemp = new Temp(firstName, lastName);
        Temp created = this.tempRepository.save(newTemp);
        return created;
    }

    // public Temp updateById(Long id,Job job) {
    //     Optional<Temp> foundTemp = this.getById(id);
    //     if (foundTemp.isPresent()) {
    //         Temp toUpdate = foundTemp.get();
    //         List<Job> jobList = toUpdate.getJobs();
    //         jobList.add(job);
    //         toUpdate.setJobs(jobList);
    //         this.tempRepository.save(toUpdate);
    //         return toUpdate;
    //     }
	//     return null;
    // }
}
