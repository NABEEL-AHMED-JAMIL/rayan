package com.example.rayan.controller;

import com.example.rayan.entity.Note;
import com.example.rayan.entity.Patient;
import com.example.rayan.repository.DoctorTypeRepository;
import com.example.rayan.repository.NoteRepository;
import com.example.rayan.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Created by Nabeel on 4/19/2017.
 */

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorTypeRepository doctorTypeRepository;
    @Autowired
    private NoteRepository noteRepository;


    @RequestMapping(value="/addPatient",  method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Patient> newPatient(@RequestBody Patient patient ) {
        System.out.print("Patient----------Create------------->");
        this.patientRepository.save(patient);
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);

    }

    @RequestMapping(value="/getAllPatient", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> getAllPatients() {
        System.out.print("Patients-----------------list---------Getting->");
        List<Patient> patientList = patientRepository.findAll();

        return new ResponseEntity<List<Patient>>(patientList,HttpStatus.OK);
    }


    @RequestMapping(value = "notes/{mrNo}",method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getAllPatientNote(@PathVariable("mrNo") Long mrNo) {
        System.out.print("Getting-----------------list---------PatientNote->");
         Patient patient = patientRepository.findOne(mrNo);
         List<Note> noteList = patient.getNotes();
         List<Object> temp = new  ArrayList<Object>();
         if(noteList != null){
             System.out.print("USing the Hash---->Map for send the data");
             for (Note note: noteList) {
                 Map notesMap = new HashMap();
                 notesMap.put("patientMrNo" , patient.getMrNo());
                 notesMap.put("patientName" , patient.getName());
                 notesMap.put("noteId" , note.getId());
                 notesMap.put("noteDate" , note.getNoteDate());
                 notesMap.put("doctorName" , note.getDoctor().getUsername());
                 notesMap.put("description", note.getDescription());
                 notesMap.put("noteType" , note.getDoctorType().getType());
                 temp.add(notesMap);
             }
             return new ResponseEntity<List<Object>>(temp,HttpStatus.OK);
         }else{
             return new ResponseEntity<List<Object>>(temp,HttpStatus.OK);
         }
    }


    @RequestMapping(value = "/{mrNo}",method = RequestMethod.GET)
    public ResponseEntity<Patient> getPatient(@PathVariable("mrNo") Long mrNo) {
        System.out.print("Patient--------------Getting---------->");
        Patient patient = patientRepository.findOne(mrNo);
        return new ResponseEntity<Patient>(patient,HttpStatus.OK);
    }


    @RequestMapping(value = "/{mrNo}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Patient> deletePatient(@PathVariable("mrNo") Long mrNo) {
        System.out.print("Delete-----------------Patient--------->");
        Patient patient = this.patientRepository.findOne(mrNo);
        if(patient == null){
            return new ResponseEntity<Patient>(patient,HttpStatus.OK);
        }else {
            this.noteRepository.delete(patient.getNotes());
            this.patientRepository.delete(mrNo);
            return new ResponseEntity<Patient>(patient,HttpStatus.OK);

        }
    }


    @RequestMapping(value = "/{mrNo}",method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Patient> updatePatient(@PathVariable("mrNo") long mrNo, @RequestBody Patient patient) {

        System.out.print("Update----------------Patient--------->");
        Patient temp = this.patientRepository.findOne(mrNo);
        if(temp == null){
            return new ResponseEntity("Unable to upate. User with id " + mrNo + " not found.", HttpStatus.NOT_FOUND);
        }else{
            temp.setName(patient.getName());
            temp.setPhone(patient.getPhone());
            this.patientRepository.save(temp);
            return new ResponseEntity<Patient>(patient,HttpStatus.OK);
        }
    }

}
