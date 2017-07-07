package com.example.rayan.controller;

import com.example.rayan.entity.DoctorType;
import com.example.rayan.repository.DoctorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Nabeel on 4/19/2017.
 */
@RestController
@RequestMapping("/docType")
public class DoctorTypeController {

    @Autowired
    private DoctorTypeRepository doctorTypeRepository;
	
	// ok
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorType> addDoctorType(@RequestBody DoctorType doctorType) {
        this.doctorTypeRepository.save(doctorType);
        return new ResponseEntity<DoctorType>(doctorType,HttpStatus.CREATED);
    }
	
	// ok
    @RequestMapping(value="/getAllType", method = RequestMethod.GET)
    public ResponseEntity<List<DoctorType>> getAllTypes() {

        List<DoctorType> doctorTypeList = doctorTypeRepository.findAll();
        if(doctorTypeList.isEmpty()){
            return new ResponseEntity<List<DoctorType>>(doctorTypeList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<DoctorType>>(doctorTypeList, HttpStatus.OK);
    }
	// ok
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorType> deleteDoctorType(@PathVariable("id") Long id) {
        this.doctorTypeRepository.delete(id);
        return new ResponseEntity<DoctorType>(HttpStatus.NO_CONTENT);
    }
	
	// ok
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<DoctorType> getDoctorType(@PathVariable("id") Long id) {

        DoctorType doctorType = this.doctorTypeRepository.findOne(id);
        return new ResponseEntity<DoctorType>(doctorType,HttpStatus.OK);
    }

	// ok
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorType> updateDoctorType(@RequestBody DoctorType doctorType) {
        this.doctorTypeRepository.save(doctorType);
        return new ResponseEntity<DoctorType>(doctorType,HttpStatus.OK);
    }

}
