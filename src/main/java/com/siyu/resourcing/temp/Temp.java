package com.siyu.resourcing.temp;

import java.util.ArrayList;
import java.util.List;

import com.siyu.resourcing.job.Job;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "temps")
public class Temp {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "temp", cascade = CascadeType.ALL)
    private List<Job> jobs=new ArrayList<Job>();

    public Temp() {
    }

    public Temp(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        
    }

}
