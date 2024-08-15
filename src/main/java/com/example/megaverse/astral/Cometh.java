package com.example.megaverse.astral;

import java.util.Objects;

public class Cometh extends Astral {

    public enum Direction {
        UP, DOWN, RIGHT, LEFT
    }

    public final Direction direction;

    public Cometh(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Cometh{" +
                "coordinate=" + coordinate +
                ", direction=" + direction +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cometh))
            return false;
        if (!super.equals(o))
            return false;
        Cometh cometh = (Cometh) o;
        return direction == cometh.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), direction);
    }
}
