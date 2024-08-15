package com.example.megaverse.astral.services;

import org.springframework.stereotype.Component;

import com.example.megaverse.astral.Astral;
import com.example.megaverse.astral.Cometh;
import com.example.megaverse.astral.Polyanet;
import com.example.megaverse.astral.Soloon;

import java.util.HashMap;
import java.util.Map;

@Component
public class AstralServiceManager {

    private final Map<Class<? extends Astral>, AstralService> serviceMap = new HashMap<>();

    public AstralServiceManager(PolyanetService polyanetService, ComethService comethService,
            SoloonService soloonService) {
        serviceMap.put(Polyanet.class, polyanetService);
        serviceMap.put(Cometh.class, comethService);
        serviceMap.put(Soloon.class, soloonService);
    }

    public void create(Astral astral) {
        AstralService service = serviceMap.get(astral.getClass());
        if (service == null) {
            throw new IllegalArgumentException("No service found for " + astral.getClass().getSimpleName());
        }
        service.create(astral);
    }

    public void delete(Astral astral) {
        AstralService service = serviceMap.get(astral.getClass());
        if (service == null) {
            throw new IllegalArgumentException("No service found for " + astral.getClass().getSimpleName());
        }
        service.delete(astral);

    }
}