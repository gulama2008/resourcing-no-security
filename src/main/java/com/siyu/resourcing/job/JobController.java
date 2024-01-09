package com.siyu.resourcing.job;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siyu.resourcing.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAll() {
        List<Job> jobs = this.jobService.getAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getById(@PathVariable Long id) {
        Optional<Job> found = this.jobService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<Job>(found.get(), HttpStatus.OK);
        }
        throw new NotFoundException(String.format("Job with id: %d does not exist", id));
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody JobCreateDTO data) {
        Job newJob = this.jobService.createJob(data);
        if (newJob == null) {
            throw new NotFoundException(String.format("Temp with id: %d does not exist", data.getTempId()));
        }
        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Job> updateById(@PathVariable Long id, @Valid @RequestBody JobUpdateDTO data) {
        Optional<Job> updated = this.jobService.updateById(id, data);
        if(updated==null){
            throw new NotFoundException(
                String.format("Temp with id %d does not exist, could not update", data.getTempId()));
        }
        if (updated.isPresent()) {
			return new ResponseEntity<Job>(updated.get(), HttpStatus.OK);
		}
        throw new NotFoundException(String.format("Job with id %d does not exist, could not update", id));
    }
    
}
