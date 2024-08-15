package com.example.megaverse.astral;

import java.util.Objects;

public class Soloon extends Astral {

    public enum Color {
        BLUE, RED, PURPLE, WHITE;
    }

    public final Color color;

    public Soloon(Coordinate coordinate, Color color){
        this.coordinate = coordinate;
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }

    
    @Override
    public String toString() {
        return "Soloon{" +
                "coordinate=" + coordinate +
                ", color=" + color +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Soloon))
            return false;
        if (!super.equals(o))
            return false;
        Soloon soloon = (Soloon) o;
        return color == soloon.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
