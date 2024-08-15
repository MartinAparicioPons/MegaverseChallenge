package com.example.megaverse.astral;

public class Polyanet extends Astral {

    public Polyanet(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    
    @Override
    public String toString() {
        return "Polyanet{" +
                "coordinate=" + coordinate +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Polyanet))
            return false;
        return !super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
