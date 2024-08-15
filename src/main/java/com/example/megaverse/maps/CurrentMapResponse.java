package com.example.megaverse.maps;
import java.util.List;

public class CurrentMapResponse {

    public class MapContent{
        private List<List<AstralObject>> content;
        public List<List<AstralObject>> getContent() {
            return content;
        }
    
        public void setContent(List<List<AstralObject>> content) {
            this.content = content;
        }
    }

    private MapContent map;

    public MapContent getMap() {
        return map;
    }

    public void setMap(MapContent map) {
        this.map = map;
    }
}
