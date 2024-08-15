package com.example.megaverse.astral;

public class Space extends Astral {

    public Space(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Space{" +
                "coordinate=" + coordinate +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Space))
            return false;
        return !super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
