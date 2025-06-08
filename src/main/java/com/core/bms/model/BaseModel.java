package com.core.bms.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Long createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private Long updatedAt;
    private Integer versionNo;


}
