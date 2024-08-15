package com.example.megaverse.maps;

import java.util.List;

public class AstralMap<T> {
    private final List<List<T>> matrix;

    public AstralMap (List<List<T>> matrix) {
        this.matrix = matrix;
    }
    
    public List<List<T>> getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (List<T> list : matrix) { 
            sb.append("[");
            for (T t : list) {
                if (t != null) { 
                    sb.append(t + ", ");
                }
            }
            sb.append("]");
        }
        sb.append("]");
        return sb.toString();
    }
}
