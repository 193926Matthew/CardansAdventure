package MapEditor;

import Level.Map;
import Maps.JungleMap;
import Maps.DesertMap; // Ensure DesertMap.java exists in src/Maps/
import Maps.LobbyMap;
import Maps.SnowBossMap;
import Maps.TestMap;
import Maps.SnowMap;
import Maps.StartingCutsceneMap;
import Maps.TitleScreenMap;
import Maps.TutorialMap;


import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("LobbyMap");
            add("DesertMap");
            add("JungleMap");
            add("SnowMap");
            add("TutorialMap");
            add("SnowBossMap");
            add("StartingCutsceneMap");
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
            case "JungleMap":
                return new JungleMap();
            case "SnowMap":
                return new SnowMap();
            case "TutorialMap":
                return new TutorialMap();
            case "SnowBossMap":
                return new SnowBossMap();
            case "StartingCutsceneMap":
                return new StartingCutsceneMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
