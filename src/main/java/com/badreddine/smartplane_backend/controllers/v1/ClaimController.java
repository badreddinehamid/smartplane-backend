package com.badreddine.smartplane_backend.controllers.v1;

import com.badreddine.smartplane_backend.dto.ClaimDto;
import com.badreddine.smartplane_backend.models.ClaimsModel;
import com.badreddine.smartplane_backend.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/claim")
public class ClaimController {

    @Autowired
    private ClaimService claimService;


    @GetMapping("/")
    public List<ClaimDto> getClaim(@RequestParam(value = "namespace", defaultValue = "crossplane-system")String namespace) throws Exception {

        try{
            return claimService.listclaims();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;

    }

}
