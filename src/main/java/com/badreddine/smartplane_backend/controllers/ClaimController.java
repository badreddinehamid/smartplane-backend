package com.badreddine.smartplane_backend.controllers;

import com.badreddine.smartplane_backend.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/claim")
public class ClaimController {

    @Autowired
    private ClaimService claimService;


    @GetMapping("/")
    public Object getClaim(@RequestParam(value = "namespace", defaultValue = "crossplane-system")String namespace) throws Exception {

        try{
            return claimService.listclaims();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;

    }

}
