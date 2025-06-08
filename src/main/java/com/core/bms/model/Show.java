package com.core.bms.model;

import com.core.bms.model.enums.FeatureType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "shows")
@Data
public class Show extends BaseModel {
    @ManyToOne
    private Movie movie;
    private Long startTime;
    private Long endTime;

    @ManyToOne
    private Screen screen;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<FeatureType> features;
}
