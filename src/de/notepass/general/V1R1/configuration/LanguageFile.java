package de.notepass.general.V1R1.configuration;

import de.notepass.general.V1R1.logger.Log;
import de.notepass.general.V1R1.util.VisualProperties;

import java.io.File;
import java.io.IOException;

/**
 * <p>This class represents the functions f a language-file</p>
 */
public class LanguageFile {

    /**
     * <p>Default file for the running session</p>
     */
    private static File sessionDefaultFile = new File(InternalConfigDummy.LANG_ROOT.getAbsoluteFile() + "/" + new ConfigurationFile().readConfig("langFile", true));

    /**
     * <p>Returns the pre-delivered language file</p>
     *
     * @return Default content of the default language file
     */
    public static VisualProperties getDefaultContent() {
        VisualProperties vprop = new VisualProperties();
        vprop.addComment("Example language file for the english language");
        vprop.setProperty("code", "en_GB");
        vprop.setProperty("display", "English");
        vprop.addEmptyLine();
        vprop.setProperty("stdErrorTitle", "Fatal Error");
        vprop.setProperty("stdErrorPrefix", "");
        vprop.setProperty("stdErrorSuffix", "");
        vprop.addEmptyLine();
        vprop.setProperty("stdWarnPrefix", "");
        vprop.setProperty("stdWarnSuffix", "");
        vprop.addEmptyLine();
        vprop.setProperty("stdInfoPrefix", "");
        vprop.setProperty("stdInfoSuffix", "");
        vprop.addEmptyLine();
        vprop.setProperty("infoStartSession", "===Starting session===");
        vprop.setProperty("infoStopSession", "===Stopping session===");
        return vprop;
    }

    /**
     * <p>Path to the default language file (not session default, the hard-coded default file)</p>
     */
    final public static File STANDARD_LANGUAGE_FILE = new File(InternalConfigDummy.LANG_ROOT + "/en_GB.properties");

    /**
     * <p>File for the current Object</p>
     */
    private File dynamicLangFile;

    /**
     * <p>Creates a new LanguageFile-Object, that uses the given file</p>
     *
     * @param langFile File to use
     */
    public LanguageFile(File langFile) {
        this.dynamicLangFile = langFile;
    }

    /**
     * <p>Creates a new LanguageFile-Object, that uses the session default configuration file</p>
     */
    public LanguageFile() {
        this.dynamicLangFile = sessionDefaultFile;
    }

    /**
     * <p>This method translates a text.
     * It needs the ID of the text to translate.
     * Normally there are many more of these methods, specialised for the class</p>
     *
     * @param id         Name iof the text to translate
     * @param showErrors Decide if errors should be shown
     * @return Translated text
     */
    public String translate(String id, boolean showErrors) {
        try {
            if (!this.dynamicLangFile.exists()) {
                VisualProperties vprop = LanguageFile.getDefaultContent();
                vprop.saveToFile(STANDARD_LANGUAGE_FILE);

                new LanguageFile().save(vprop);
                if (showErrors) {
                    Log.logWarning("Couldn't find the language file. Writing default language file...");
                }
            }
            VisualProperties properties = new VisualProperties();
            properties.loadFromFile(this.dynamicLangFile);
            String translatedText = properties.getProperty(id);
            if (translatedText != null) {
                return translatedText;
            } else {
                if (showErrors) {
                    Log.logWarning("Couldn't translate text with ID " + id + "... This is normally an error in the language-file...");
                }
                return null;
            }
        } catch (Exception e) {
            if (showErrors) {
                Log.logError(e);
                Log.logWarning("Couldn't translate text with ID " + id + "... This is normally an error in the language-file...");
            }
            return null;
        }
    }

    /**
     * <p>Reads the Language-File from the disk and returns the content</p>
     *
     * @return Content of the file as VisualProperties
     * @throws IOException
     */
    public VisualProperties read() throws IOException {
        File langFile = getLanguageFile();
        VisualProperties visualProperties = new VisualProperties();
        visualProperties.loadFromFile(langFile);
        return visualProperties;
    }

    /**
     * <p>Saves the given visual properties into the Language-File</p>
     *
     * @param vprop
     * @throws IOException
     */
    public void save(VisualProperties vprop) throws IOException {
        vprop.saveToFile(this.dynamicLangFile);
    }

    /**
     * <p>Returns the current language-file</p>
     *
     * @return Language file path as File-Object
     */
    public File getLanguageFile() {
        return this.dynamicLangFile;
    }

    /**
     * <p>Sets the current Language-File as session default, so that every new generated languageFile-object that uses the standard config-file now uses this file</p>
     */
    public void setAsSessionDefault() {
        sessionDefaultFile = this.dynamicLangFile;
    }


}
