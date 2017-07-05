package com.example.rayan;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lycus 01 on 7/5/2017.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping("/private")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
    public String getPrivatePage() {

        return "Private Admin String" ;
    }
}
