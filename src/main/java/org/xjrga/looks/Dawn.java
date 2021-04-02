package org.xjrga.looks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalTheme;

/**
 * This class is a theme for Java Look and Feel (also known as Metal)
 *
 * @author Jorge R Garcia de Alba
 */
public class Dawn extends MetalTheme {

    private final ColorUIResource primary1;
    private final ColorUIResource primary2;
    private final ColorUIResource primary3;
    private final ColorUIResource secondary1;
    private final ColorUIResource secondary2;
    private final ColorUIResource secondary3;
    private ColorTones colorTone01 = null;
    private ColorTones colorTone02 = null;
    private ColorTones colorTone03 = null;
    private final FontUIResource fontUIResource;

    /**
     * Dawn constructor
     *
     * @param font the font
     * @see java.awt.Font
     */
    public Dawn(Font font) {
        colorTone01 = new ColorTones(new Color(94, 98, 101));
        colorTone02 = new ColorTones(new Color(58, 127, 184));
        colorTone03 = new ColorTones(new Color(129, 116, 2));
        fontUIResource = new FontUIResource(font);
        secondary3 = new ColorUIResource(colorTone01.getColor());
        //app, menu background, selected tab               

        secondary1 = new ColorUIResource(colorTone01.getColor());
        //iframe outlines, button outlines, menu outline, menu item checkbox, 
        //textbox outline, component outlines

        primary1 = new ColorUIResource(colorTone02.darkerBy10());
        //scrollbar knob outline and dots, folder tab, 
        //selected iframe outline and dots, tooltip outline,
        //slider knob dots

        primary2 = new ColorUIResource(colorTone02.lighterBy10());//
        //internal frame background, component focus
        //menu item background, selected scrollbar knob background, folders line
        //slider ticks, selected tree item outline

        primary3 = new ColorUIResource(colorTone03.getColor());
        //selected iframe top bar
        //selected table row, selected list item
        //tooltip, tree structure, tree folders
        //scrollbar knob left outline, selected slider knob highlight

        secondary2 = new ColorUIResource(colorTone01.lighterBy20());
        //table grid, slider outline and ticks, titled border outline 
        //iframes side lines,scrollbar left, upper outline, unselected tab, 
        //pressed menuitem, menubar separator
        //slider disabled ticks, slider internal track
        //pressed button, selected toolbar button, filechooser file background,
        //disabled text                      

    }

    @Override
    public void addCustomEntriesToTable(UIDefaults table) {
        super.addCustomEntriesToTable(table);
        UIManager.put("TabbedPane.selected", colorTone02.darkerBy10());
        UIManager.put("TabbedPane.background", colorTone01.getColor());
        UIManager.put("TabbedPane.darkShadow", colorTone01.darkerBy20());
        UIManager.put("Table.gridColor", colorTone01.getColor());
        UIManager.put("ProgressBar.border", BorderFactory.createLineBorder(colorTone01.darkerBy20()));
        UIManager.put("TabbedPane.tabInsets", new Insets(1, 10, 8, 10));
        //If you enable this it will look a bit better but it affects 
        //other themes
    }

    @Override
    public String getName() {
        return "Dawn";
    }

    @Override
    public ColorUIResource getPrimary1() {
        return primary1;
    }

    @Override
    public ColorUIResource getPrimary2() {
        return primary2;
    }

    @Override
    public ColorUIResource getPrimary3() {
        return primary3;
    }

    @Override
    public ColorUIResource getSecondary1() {
        return secondary1;
    }

    @Override
    public ColorUIResource getSecondary2() {
        return secondary2;
    }

    @Override
    public ColorUIResource getSecondary3() {
        return secondary3;
    }

    @Override
    protected ColorUIResource getWhite() {
        //component outlines and backgrounds
        return new ColorUIResource(colorTone01.darkerBy30());
    }

    @Override
    protected ColorUIResource getBlack() {
        //text
        return new ColorUIResource(colorTone01.getWhite());
    }

    @Override
    public FontUIResource getControlTextFont() {
        return fontUIResource;
    }

    @Override
    public FontUIResource getSystemTextFont() {
        return fontUIResource;
    }

    @Override
    public FontUIResource getUserTextFont() {
        return fontUIResource;
    }

    @Override
    public FontUIResource getMenuTextFont() {
        return fontUIResource;
    }

    @Override
    public FontUIResource getWindowTitleFont() {
        return fontUIResource;
    }

    @Override
    public FontUIResource getSubTextFont() {
        return fontUIResource;
    }
}
