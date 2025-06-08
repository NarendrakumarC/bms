package com.core.bms.repository;

import com.core.bms.model.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    //Lock on specific row ids, hence anybody won't be allowed to read these row ids

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "Select aa from ShowSeat aa where aa.id IN:ids", nativeQuery = true)
    List<ShowSeat> findAllShowSeatAndLock(List<Long> ids);

}
