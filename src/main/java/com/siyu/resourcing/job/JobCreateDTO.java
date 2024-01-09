package com.siyu.resourcing.job;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobCreateDTO {

    private String name;
    private String startDate;
    private String endDate;
    private Long tempId;
    
}