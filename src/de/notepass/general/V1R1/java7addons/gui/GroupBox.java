package de.notepass.general.V1R1.java7addons.gui;

import de.notepass.general.V1R1.util.Util;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

//This is a standard title GroupBox

/**
 * <p>This class contains a standard titled GroupBox</p>
 */

public class GroupBox extends StackPane {

    /**
     * <p>Enum fur the Title Position</p>
     */
    public static enum TitlePos {
        LEFT, CENTER, RIGHT
    }

    ;

    //The titePane will later just Contain the title and a basic markup
    /**
     * <p>The StackPane that contains the title and the content pane</p>
     */
    public StackPane titlePane = new StackPane();
    //The content Pane will contain all elements

    //Both of them are modifiable from the Outside... For Dynamical reasons
    /**
     * <p>The content pane is a GridPane that contains the content elements</p>
     */
    public GridPane contentPane = new GridPane();
    /**
     * <p>The label for the titel</p>
     */
    private Label title = new Label();
    /**
     * <p>Variable containing the needed CSS-Files</p>
     */
    public static String cssFile = Util.createLoadString("style/GroupBox.css");

    /**
     * <p>Setting up the GroupBox with its title and the title position</p>
     *
     * @param titleString - Title of the GroupBox
     * @param tp          - Position of the title
     */
    public GroupBox(String titleString, TitlePos tp) {
        Pos mainPos = Pos.TOP_CENTER;
        String posString = "";

        if (tp == TitlePos.LEFT) {
            mainPos = Pos.TOP_LEFT;
            posString = "-fx-translate-x: 10px;";
        }

        if (tp == TitlePos.RIGHT) {
            mainPos = Pos.TOP_RIGHT;
            posString = "-fx-translate-x: -10px;";
        }

        title.setText(" " + titleString + " ");
        title.getStyleClass().add("GroupBoxTitle");

        StackPane.setAlignment(title, mainPos);
        title.setStyle(posString);

        titlePane.getStyleClass().add("GroupBoxContent");
        titlePane.getChildren().add(contentPane);

        getStyleClass().add("GroupBoxBorder");
        getChildren().addAll(title, titlePane);
    }

    /**
     * <p>Faster version to access the add() method of the {@link #contentPane}</p>
     *
     * @param content - The content that should be added
     * @param column
     * @param row
     */
    public void add(Node content, int column, int row) {
        this.contentPane.add(content, column, row);
    }

    /**
     * @param content    - The content that should be added
     * @param column
     * @param row
     * @param columnSpan
     * @param rowSpan
     */
    public void add(Node content, int column, int row, int columnSpan, int rowSpan) {
        this.contentPane.add(content, column, row, columnSpan, rowSpan);
    }
}
