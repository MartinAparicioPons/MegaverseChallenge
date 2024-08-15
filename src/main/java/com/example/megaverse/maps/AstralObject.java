package com.example.megaverse.maps;

public class AstralObject {
    private int type;
    private String direction;
    private String color;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "AstralObject{" +
                "type=" + type +
                (direction == null ? "" : ", direction=\"" + direction + "\"") +
                (color == null ? "" : ", color=\"" + color + "\"") +
                "}";
    }
}
