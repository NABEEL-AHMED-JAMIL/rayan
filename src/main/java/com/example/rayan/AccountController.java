package com.example.rayan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Lycus 01 on 7/5/2017.
 */

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @RequestMapping("/login")
    public Principal getHomePage(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/local")
    public String getLocalPage() {
        return "Local String" ;
    }

    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public String getPrivatePage() {

        return "Private String" ;
    }

}




