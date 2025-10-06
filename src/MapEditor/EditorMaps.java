package MapEditor;

import Level.Map;
import Maps.DesertMap;
import Maps.LobbyMap;
import Maps.TestMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("LobbyMap");
            add("DesertMap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "LobbyMap":
                return new LobbyMap();
            case "DesertMap":
                return new DesertMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
