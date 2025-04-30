package com.badreddine.smartplane_backend.controllers.v1;

import com.badreddine.smartplane_backend.models.ProviderModel;
import com.badreddine.smartplane_backend.services.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/providers")
public class ProvidersController {

    @Autowired
    private ProvidersService providersService;

    @GetMapping("/")
    public ProviderModel.ProviderList getProviders(@RequestParam(value = "namespace", defaultValue = "crossplane-system") String namespace) {
        try {
            return providersService.listProviders();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/providerconfigs")
    public Object getProviderConfigs(@RequestParam(value = "namespace", defaultValue = "crossplane-system") String namespace, @RequestParam(value = "provider", required = false) String provider) {
        try {
            return providersService.listProviderconfigsets();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/status")
    public Object getProviderStatus(@RequestParam(value = "name") String providerName) {
        try {
            return providersService.getProviderStatus(providerName);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", "Failed to fetch provider status");
        }
    }

    @GetMapping("/events")
    public Object getProviderEvents(
            @RequestParam String providerName,
            @RequestParam(value = "namespace", defaultValue = "crossplane-system") String namespace) {
        try {
            return providersService.getProviderEvents(providerName, namespace);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", e.getMessage());
        }
    }

    @GetMapping("/api-resources")
    public Object getProviderApiResources(@RequestParam String providerGroup) {
        try {
            return providersService.listProviderApiResources(providerGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", e.getMessage());
        }
    }
}
