package com.example.rayan;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lycus 01 on 7/5/2017.
 */
@RestController
@RequestMapping(value = "/dba")
public class DbaController {

    @RequestMapping(value = "/private", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DBA')")
    public String getPrivatePage() {

        return "Private DBA String" ;
    }
}
