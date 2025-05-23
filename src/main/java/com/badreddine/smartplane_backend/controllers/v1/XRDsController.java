package com.badreddine.smartplane_backend.controllers.v1;

import com.badreddine.smartplane_backend.dto.XrdsDto;
import com.badreddine.smartplane_backend.services.XRDsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/xrds")
public class XRDsController {


    private final XRDsService xrdsService;

    @Autowired
    public XRDsController(XRDsService xrdsService) {
        this.xrdsService = xrdsService;
    }


    @GetMapping("/")
    public List<XrdsDto> getxrds(@RequestParam(value = "namespace", defaultValue = "crossplane-system") String namespace){

        try {
            return xrdsService.listXrds();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
