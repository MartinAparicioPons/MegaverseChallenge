package com.example.megaverse.maps;
import java.util.List;

public class GoalMapResponse {
    private List<List<String>> goal;

    public List<List<String>> getGoal () {
        return goal;
    }

    public void setGoal(List<List<String>> goal) {
        this.goal = goal;
    }
}
