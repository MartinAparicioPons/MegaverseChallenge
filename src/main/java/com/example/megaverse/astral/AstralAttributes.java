package com.example.megaverse.astral;

import com.example.megaverse.astral.Cometh.Direction;
import com.example.megaverse.astral.Soloon.Color;

public class AstralAttributes {
    private Coordinate coordinate;
    private Direction direction;
    private Color color;

    public AstralAttributes(Coordinate coordinate, Direction direction, Color color) {
        this.coordinate = coordinate;
        this.direction = direction;
        this.color = color;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public Color getColor() {
        return color;
    }

}
