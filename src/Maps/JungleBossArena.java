package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class JungleBossArena extends Map{
        public JungleBossArena() {
        super("JungleBossArena.txt", new CommonTileset());
        this.playerStartPosition = new Point(2, 8);

    }

}
