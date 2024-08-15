package com.example.megaverse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.megaverse.astral.Astral;
import com.example.megaverse.astral.AstralAttributes;
import com.example.megaverse.astral.AstralFactory;
import com.example.megaverse.astral.Cometh.Direction;
import com.example.megaverse.astral.Coordinate;
import com.example.megaverse.astral.Space;
import com.example.megaverse.astral.Soloon.Color;
import com.example.megaverse.maps.AstralMap;
import com.example.megaverse.maps.AstralObject;
import com.example.megaverse.maps.MapService;
import com.example.megaverse.tasks.Task;
import com.example.megaverse.tasks.TaskManager;
import com.example.megaverse.tasks.TaskQueue;

@Component
public class StartupRunner implements CommandLineRunner {

    private final MapService mapService;

    public StartupRunner(MapService mapService) {
        this.mapService = mapService;
    }

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TaskQueue taskQueue;

    @Override
    public void run(String... args) throws Exception {
        taskManager.startWorkers();

        AstralMap<String> goalMap = mapService.getGoalMap();
        AstralMap<AstralObject> currentMap = mapService.getCurrentMap();

        System.out.println("goal Map: " + goalMap);
        System.out.println("current Map: " + currentMap);

        for (int x = 0; x < goalMap.getMatrix().size(); x++) {
            for (int y = 0; y < goalMap.getMatrix().get(x).size(); y++) {
                String goalAstralString = goalMap.getMatrix().get(x).get(y);
                Astral goalAstral = createAstralFromAstralString(goalAstralString, x, y);

                Astral currentAstral = astralObjectToAstral(currentMap.getMatrix().get(x).get(y), x, y);

                if (!currentAstral.equals(goalAstral)) {
                    if (goalAstral instanceof Space) {
                        taskQueue.enqueue(new Task(Task.Action.DELETE, currentAstral));
                    } else {
                        taskQueue.enqueue(new Task(Task.Action.ADD, goalAstral));
                    }
                }
            }
        }
    }

    private Astral astralObjectToAstral(AstralObject currentAstralObject, int x, int y) {
        if (currentAstralObject == null) {
            return AstralFactory.createAstralFromType(null, new AstralAttributes(new Coordinate(x, y), null, null));
        }
        Direction direction = null;
        if (currentAstralObject.getDirection() != null) {
            direction = Direction.valueOf(currentAstralObject.getDirection().toUpperCase());
        }
        Color color = null;
        if (currentAstralObject.getColor() != null) {
            color = Color.valueOf(currentAstralObject.getColor().toUpperCase());
        }

        return AstralFactory.createAstralFromType(currentAstralObject.getType(),
                new AstralAttributes(new Coordinate(x, y), direction, color));
    }

    private Astral createAstralFromAstralString(String astralString, int x, int y) {
        String name;
        Direction direction = null;
        Color color = null;
        if (astralString.contains("_")) {
            String[] parts = astralString.split("_", 2);
            String attribute = parts[0];
            name = parts[1];

            try {
                direction = Direction.valueOf(attribute);
            } catch (Exception e) {
                // ignore
            }
            try {
                color = Color.valueOf(attribute);
            } catch (Exception e) {
                // ignore
            }
        } else {
            name = astralString;
        }
        return AstralFactory.createAstralFromName(name, new AstralAttributes(new Coordinate(x, y), direction, color));
    }
}
