package com.example.rayan.controller;

import com.example.rayan.entity.Doctor;
import com.example.rayan.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nabeel on 4/15/2017.
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;


    @RequestMapping(value = "/doctors" ,method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> listAllDoctor() {
        System.out.print("doctt---------------------->");
        List<Doctor> doctorList = doctorRepository.findAll();
        if(doctorList.isEmpty()){
            return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Doctor>>(doctorList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getDoctor",method = RequestMethod.GET)
    public ResponseEntity<Doctor> getDoctor(@RequestBody Doctor doctor) {

        Doctor doctor1 = doctorRepository.findByUsername(doctor.getUsername());
        //System.out.print(doctor1.toString());
        if(doctor1 == null ){
            //
            return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);

        }
        else{
            return new ResponseEntity<Doctor>(doctor1,HttpStatus.OK);
        }

    }
}