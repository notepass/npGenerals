package de.notepass.general.java7addons.InternalConfig;

import de.notepass.general.java7addons.gui.GroupBox;
import de.notepass.general.util.Util;
import javafx.geometry.Insets;

import java.io.File;

/**
 * <p>This class contains the default Internal Configuration.
 * If you can't change something in the config-file you can hopefully here</p>
 */
public class InternalConfigDummy extends de.notepass.general.internalConfig.InternalConfigDummy {
    /**
     * CSS-Files needed to be loaded for the new JavaFX-Elements
     */
    final public static String[] CSS_FILES = {GroupBox.cssFile, Util.createLoadString("style/General.css")};

    //GUI Configuration
    /**
     * Default-Padding value for the FX-GUI
     */
    final public static Insets GUI_DEFAULT_PADDING = new Insets(10,10,10,10);
    /**
     * Default VGap for the GridPanes
     */
    final public static double GUI_DEFAULT_VGAP = 10;
    /**
     * Default HGap for the GridPanes
     */
    final public static double GUI_DEFAULT_HGAP = 10;
    /**
     * Default Spacing for the GridPanes
     */
    final public static double GUI_DEFAULT_SPACING = 5;



    //Config for GroupBoxes
    /**
     * Default VGap for the GridPane of the groupBox-Element
     */
    final public static double GROUPBOX_DEFAULT_VGAP = 5;
    /**
     * Default HGap for the GridPane of the groupBox-Element
     */
    final public static double GROUPBOX_DEFAULT_HGAP = 5;
    /**
     * Default padding for the GridPane of the groupBox-Element
     */
    final public static Insets GROUPBOX_DEFAULT_PADDING = new Insets(5,5,5,5);
}