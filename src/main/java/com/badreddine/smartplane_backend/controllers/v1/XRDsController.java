package com.badreddine.smartplane_backend.controllers.v1;

import com.badreddine.smartplane_backend.services.XRDsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/xrds")
public class XRDsController {


    @Autowired
    private XRDsService xrDsService;


    @GetMapping("/")
    public Object getxrds(@RequestParam(value = "namespace", defaultValue = "crossplane-system") String namespace){

        try {
            return xrDsService.listXrds();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
