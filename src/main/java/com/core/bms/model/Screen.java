package com.core.bms.model;

import com.core.bms.model.enums.FeatureType;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Screen extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats ;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<FeatureType> features;

    private Integer capacity;

}
