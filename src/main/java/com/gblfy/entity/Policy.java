package com.gblfy.entity;

import lombok.Data;

@Data
public class Policy {
    private Integer id;
    private String name;
    private String describe;
    private double matchdgree;
    private double new_matchdgree;

}
