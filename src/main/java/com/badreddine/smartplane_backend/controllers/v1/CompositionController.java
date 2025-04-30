package com.badreddine.smartplane_backend.controllers.v1;


import com.badreddine.smartplane_backend.services.CompositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/compositions")
public class CompositionController  {

    @Autowired
    private CompositionsService compositionsService;


    @GetMapping("/")
    public Object getCompositions(@RequestParam(value = "namespace", defaultValue = "crossplane-system") String namespace){

        try {
            return compositionsService.listCompositions();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
