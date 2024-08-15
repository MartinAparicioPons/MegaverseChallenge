package com.example.megaverse.astral;

import java.util.Objects;

public abstract class Astral {

    Coordinate coordinate;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Astral{" +
                "coordinate=" + coordinate +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Astral astral = (Astral) o;
        return Objects.equals(coordinate, astral.coordinate);
    }
}
