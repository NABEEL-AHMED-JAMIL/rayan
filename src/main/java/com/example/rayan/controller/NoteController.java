package com.example.rayan.controller;

import com.example.rayan.entity.Note;
import com.example.rayan.entity.Patient;
import com.example.rayan.repository.DoctorRepository;
import com.example.rayan.repository.DoctorTypeRepository;
import com.example.rayan.repository.NoteRepository;
import com.example.rayan.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Nabeel on 4/16/2017.
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorTypeRepository doctorTypeRepository;


    @RequestMapping(value="/addNote/{patientId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Patient> newNote(@PathVariable Long patientId, @RequestBody Note note)  {
        System.out.print("Patient-----------------New Note--------- added->");
        Patient currentPatient = patientRepository.findOne(patientId);
        noteRepository.save(note);
        currentPatient.getNotes().add(note);
        patientRepository.save(currentPatient);
        return new ResponseEntity<Patient>( currentPatient , HttpStatus.OK);

    }


    @RequestMapping(value="/getAllNotes", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<Note>> getAllNotes() {

        System.out.print("Note-----------------list---------Getting->");
        List<Note> notes_list = noteRepository.findAll();
        if(notes_list.isEmpty()){
            System.out.print("Note-----------------list---------Empty->");
            return new ResponseEntity<List<Note>>(notes_list,HttpStatus.NO_CONTENT);
        }
        System.out.print("Note-----------------list---------"+notes_list.toString()+"->");
        return new ResponseEntity<List<Note>>(notes_list,HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Note> deleteNote(@PathVariable("id") Long id) {

        System.out.print("Note-----------------Delete--------->");
        Note note = this.noteRepository.findOne(id);
        this.noteRepository.delete(note);
        return new ResponseEntity<Note>(note,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id,@RequestBody Note note) {

        System.out.print("Note-----------------Update---------->");
        Note currentNote = this.noteRepository.findOne(id);
        currentNote.setNoteDate(note.getNoteDate());
        currentNote.setDoctorType(note.getDoctorType());
        currentNote.setDescription(note.getDescription());
        this.noteRepository.save(currentNote);
        System.out.print(currentNote);

        return new ResponseEntity<Note>(currentNote,HttpStatus.OK);

    }

}
