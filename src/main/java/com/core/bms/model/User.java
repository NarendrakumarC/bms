package com.core.bms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;

}
