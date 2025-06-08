package com.core.bms.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Movie extends BaseModel{
    private String movieName;
    private String description;
    private List<String> languages;

}
