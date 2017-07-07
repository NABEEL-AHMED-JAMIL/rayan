package com.example.rayan.repository;

import com.example.rayan.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lycus 01 on 7/5/2017.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    public Doctor findByUsername(String username);

}
