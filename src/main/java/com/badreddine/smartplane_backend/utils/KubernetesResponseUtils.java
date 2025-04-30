package com.badreddine.smartplane_backend.utils;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class KubernetesResponseUtils {
    private KubernetesResponseUtils() {} // Prevent instantiation

    public static <T> List<T> transformItems(
            Object response,
            Function<Map<String, Object>, T> mapper
    ) {
        if (response instanceof Map) {
            Map<String, Object> responseMap = (Map<String, Object>) response;
            Object items = responseMap.get("items");

            if (items instanceof List) {
                return ((List<Map<String, Object>>) items).stream()
                        .map(mapper)
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }
}