package com.example.megaverse.astral;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class AstralFactory {
    private static final Map<String, Function<AstralAttributes, Astral>> astralCreatorsFromName = new HashMap<>();
    private static final Map<Integer, Function<AstralAttributes, Astral>> astralCreatorsFromType = new HashMap<>();

    static {
        astralCreatorsFromName.put("SPACE", (a) -> new Space(a.getCoordinate()));
        astralCreatorsFromName.put("POLYANET", (a) -> new Polyanet(a.getCoordinate()));
        astralCreatorsFromName.put("COMETH", (a) -> new Cometh(a.getCoordinate(), a.getDirection()));
        astralCreatorsFromName.put("SOLOON", (a) -> new Soloon(a.getCoordinate(), a.getColor()));

        astralCreatorsFromType.put(null, (a) -> new Space(a.getCoordinate()));
        astralCreatorsFromType.put(0, (a) -> new Polyanet(a.getCoordinate()));
        astralCreatorsFromType.put(1, (a) -> new Soloon(a.getCoordinate(), a.getColor()));
        astralCreatorsFromType.put(2, (a) -> new Cometh(a.getCoordinate(), a.getDirection()));
    }

    public static Astral createAstralFromName(String astralName, AstralAttributes attributes) {
        Function<AstralAttributes, Astral> creator = astralCreatorsFromName.get(astralName.toUpperCase());
        if (creator != null) {
            return creator.apply(attributes);
        }
        throw new IllegalArgumentException("Unknown Astral name: " + astralName);
    }

    public static Astral createAstralFromType(Integer astralType, AstralAttributes attributes) {
        Function<AstralAttributes, Astral> creator = astralCreatorsFromType.get(astralType);
        if (creator != null) {
            return creator.apply(attributes);
        }
        throw new IllegalArgumentException("Unknown Astral type: " + astralType);
    }
}
