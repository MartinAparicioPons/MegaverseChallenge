package com.example.megaverse.tasks;

import com.example.megaverse.astral.Astral;

public class Task {
    public enum Action {
        ADD, DELETE
    }

    private final Action action;
    private final Astral astral;

    public Task(Action action, Astral astral) {
        this.action = action;
        this.astral = astral;
    }

    public Action getAction() {
        return action;
    }

    public Astral getAstral() {
        return astral;
    }

    @Override
    public String toString() {
        return "Task{" +
                "action=" + action +
                ", astral=" + astral +
                "}";
    }
}
