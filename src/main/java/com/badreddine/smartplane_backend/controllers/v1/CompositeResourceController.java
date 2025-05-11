package com.badreddine.smartplane_backend.controllers.v1;

import com.badreddine.smartplane_backend.dto.CompositeResourcesDto;
import com.badreddine.smartplane_backend.models.CompositeResourcesModel;
import com.badreddine.smartplane_backend.services.CompositeResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/composite-resources")
public class CompositeResourceController {

    @Autowired
    private CompositeResourceService compositeResourceService;
    @GetMapping("/")
    public List<CompositeResourcesDto> getCompositeResouces(@RequestParam(value = "namespace", defaultValue = "crossplane-system") String namespace){
        try {
            return compositeResourceService.listCompositeResources();

        } catch (Exception e) {
            e.printStackTrace();
            
        }

        return null;
    }

}
