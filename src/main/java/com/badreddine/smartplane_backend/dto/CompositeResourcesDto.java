package com.badreddine.smartplane_backend.dto;
import com.badreddine.smartplane_backend.models.CompositeResourcesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositeResourcesDto {

    private String kind;
    private String group;
    private String composition;
    private List<CompositeResourcesModel.ComposedResourceRef> resources;
    private List<CompositeResourcesModel.Condition> conditions;

}
