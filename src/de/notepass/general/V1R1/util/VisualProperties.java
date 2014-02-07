package de.notepass.general.V1R1.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * <p>This class represents a special properties-format, that generates configuration which are better readable for the end-user</p>
 */
public class VisualProperties {
    /**
     * <p>ENUM to choose the type of an entry</p>
     */
    private enum Type {
        VALUE, COMMENT, EMPTY_LINE
    }

    /**
     * <p>ArrayList to save all entry's. One entry consist of a 3-Entry's big Object-Array:<br>
     * [0] - Entry type</br>
     * [1] - Entry name</br>
     * [2] - Entry value</p>
     */
    private ArrayList<Object[]> entrys = new ArrayList<Object[]>();

    /**
     * <p>Constructor to convert Java-Properties ({@link java.util.Properties}) to VisualProperties</p>
     *
     * @param javaProperties The Properties in the default java format
     */
    public VisualProperties(java.util.Properties javaProperties) {
        Object[] propertiesValues = javaProperties.values().toArray();
        String[] propertiesNames = javaProperties.stringPropertyNames().toArray(new String[javaProperties.stringPropertyNames().size()]);

        if (propertiesNames.length == propertiesValues.length) {
            for (int i = 0; i < propertiesNames.length; i++) {
                entrys.add(new Object[]{Type.VALUE, propertiesNames[i], propertiesValues[i]});
            }
        }
    }

    /**
     * <p>Constructor to create a plain object</p>
     */
    public VisualProperties() {
    }

    /**
     * <p>Sets a specified property. If already set, the property will be overwritten...</p>
     *
     * @param name  Name of the property
     * @param value Value to asign to the property
     * @return boolean - true if operation was successful
     */
    public boolean setProperty(String name, String value) {
        if (!name.contains("=")) {
            for (int i = 0; i < entrys.size(); i++) {
                if ((Type) entrys.get(i)[0] == Type.VALUE) {
                    if (((String) entrys.get(i)[1]).equals(name)) {
                        entrys.set(i, new Object[]{Type.VALUE, name, value});
                        return true;
                    }
                }
            }
            entrys.add(new Object[]{Type.VALUE, name, value});
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>Add a comment to the properties-file</p>
     *
     * @param comment Comment to add
     * @return boolean - true if operation was successful
     */
    public boolean addComment(String comment) {
        entrys.add(new Object[]{Type.COMMENT, comment, comment});
        return true;
    }

    /**
     * <p>Add a line break</p>
     *
     * @return boolean - true if operation was successful
     */
    public boolean addEmptyLine() {
        entrys.add(new Object[]{Type.EMPTY_LINE, "\r\n", "\r\n"});
        return true;
    }

    /**
     * <p>Saves the Properties to an textfile</br>
     * PLEASE NOTE: The java properties and visual properties save files may seem to be the same, but can (and will) not be compatible to each other</p>
     *
     * @param saveFile - File to save to
     * @throws IOException
     */
    public void saveToFile(File saveFile) throws IOException {


        String saveString = "";

        for (Object[] scopeEntry : entrys) {
            switch ((Type) scopeEntry[0]) {
                case VALUE:
                    saveString += (String) scopeEntry[1] + "=" + (String) scopeEntry[2] + "\r\n";
                    break;

                case COMMENT:
                    saveString += "#" + (String) scopeEntry[1] + "\r\n";
                    break;

                case EMPTY_LINE:
                    saveString += "\r\n";
                    break;
            }
        }

        Util.stringToTextfile(saveFile, saveString);
    }

    /**
     * <p>Loads a visual property from a file</br>
     * PLEASE NOTE: The java properties and visual properties save files may seem to be the same, but can (and will) not be compatible to each other</p>
     *
     * @param source - Sourcefile
     * @throws IOException
     */
    public void loadFromFile(File source) throws IOException {
        String[] content = Util.textFileToString(source).split(Pattern.quote("\r\n"));
        if (content != null) {
            for (String scopeString : content) {
                if (!scopeString.equals("")) {
                    if (scopeString.contains("#")) {
                        if (scopeString.indexOf("#") > 0) {
                            scopeString.substring(scopeString.indexOf("="), scopeString.length());
                            scopeString.substring(0, scopeString.indexOf("="));
                        } else {
                            this.addComment(scopeString.substring(1, scopeString.length()));
                        }
                    } else {
                        this.setProperty(scopeString.substring(0, scopeString.indexOf("=")), scopeString.substring(scopeString.indexOf("=") + 1, scopeString.length()));
                    }
                } else {
                    this.addEmptyLine();
                }
            }
        }
    }

    /**
     * <P>Return the value of a given key</P>
     *
     * @param name Key of the property-entry
     * @return String - Value of the entry
     */
    public String getProperty(String name) {
        for (Object[] scopeObject : entrys) {
            if ((Type) scopeObject[0] == Type.VALUE) {
                if (((String) scopeObject[1]).equals(name)) {
                    return (String) scopeObject[2];
                }
            }
        }
        return null;
    }

    /**
     * <p>Converts Visual properties to java properties</br>
     * You will loose stuff like linebreaks and comments...</p>
     *
     * @return
     */
    public Properties toProperties() {
        Properties properties = new Properties();
        for (Object[] scopeObject : entrys) {
            if ((Type) scopeObject[0] == Type.VALUE) {
                properties.setProperty((String) scopeObject[1], (String) scopeObject[2]);
            }
        }
        return properties;
    }

}
