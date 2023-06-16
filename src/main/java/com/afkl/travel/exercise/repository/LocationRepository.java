package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface responsible for handle Location in the DB
 * @author leonardofoz
 * @since 0.1.0
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByTypeAndCode(String type, String code);

    List<Location> findByType(String type);
}
