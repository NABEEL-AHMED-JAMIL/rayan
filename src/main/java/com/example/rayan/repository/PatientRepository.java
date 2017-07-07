package com.example.rayan.repository;


import com.example.rayan.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Nabeel on 4/19/2017.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {


}
