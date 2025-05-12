package com.badreddine.smartplane_backend.controllers.v1;

import com.badreddine.smartplane_backend.dto.CompositeResourcesDto;
import com.badreddine.smartplane_backend.services.CompositeResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/composite-resources")
public class CompositeResourceController {

    private final CompositeResourceService compositeResourceService;

    @Autowired
    public CompositeResourceController(CompositeResourceService compositeResourceService) {
        this.compositeResourceService = compositeResourceService;
    }

    @GetMapping("/")
    public List<CompositeResourcesDto> getCompositeResouces(@RequestParam(value = "namespace", defaultValue = "crossplane-system") String namespace){
        try {
            return compositeResourceService.listCompositeResources();

        } catch (Exception e) {
            e.printStackTrace();
            
        }

        return Collections.emptyList();
    }

}
