package com.project.schoolschedulingsystem.Slot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Query("SELECT s FROM Slot s WHERE s.aClass.id = :classId AND s.day = :day")
    List<Slot> findSlotByAClassAndDay (@Param("classId") Long classId, @Param("day") Days day);

}
