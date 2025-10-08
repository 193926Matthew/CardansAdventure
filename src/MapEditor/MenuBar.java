package MapEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    JMenu options;
    JCheckBoxMenuItem showNpcs;
    JCheckBoxMenuItem showEnchancedMapTiles;
    JCheckBoxMenuItem showTriggers;
    JCheckBoxMenuItem showHitboxes;

    public MenuBar(TileBuilder tileBuilder) {
        options = new JMenu("Options");
        showNpcs = new JCheckBoxMenuItem("Show NPCs");
        showNpcs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tileBuilder.setShowNPCs(!tileBuilder.getShowNPCs());
            }
        });
        options.add(showNpcs);
        showEnchancedMapTiles = new JCheckBoxMenuItem("Show Enhanced Map Tiles");
        showEnchancedMapTiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tileBuilder.setShowEnhancedMapTiles(!tileBuilder.getShowEnhancedMapTiles());
            }
        });
        options.add(showEnchancedMapTiles);
        showTriggers = new JCheckBoxMenuItem("Show Enemies");
        showTriggers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tileBuilder.setShowEnemies(!tileBuilder.getShowEnemies());
            }
        });
        options.add(showTriggers);

        showHitboxes = new JCheckBoxMenuItem("Show Hitboxes");
        showHitboxes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tileBuilder.setShowHitboxes(!tileBuilder.getShowHitboxes());
            }
        });
        options.add(showHitboxes);

        add(options);
    }
}
