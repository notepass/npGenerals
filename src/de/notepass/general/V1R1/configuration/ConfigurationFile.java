package de.notepass.general.V1R1.configuration;

import de.notepass.general.V1R1.logger.Log;
import de.notepass.general.V1R1.util.VisualProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * <p>This classs represents the Configuration-File(s)</p>
 */
public class ConfigurationFile {
    /**
     * <p>File for the current Object</p>
     */
    private File dynamicConfigFile;

    /**
     * <p>Default file for the running session</p>
     */
    private static File sessionDefaultConfigFile = InternalConfigDummy.CONFIG_FILE;

    /**
     * <p>Creates a new ConfigurationFile-Object, that uses the session default configuration file</p>
     */
    public ConfigurationFile() {
        this.dynamicConfigFile = InternalConfigDummy.CONFIG_FILE;
    }

    /**
     * <p>Creates a new ConfigurationFile-Object, that uses the given file</p>
     *
     * @param configFile File to use
     */
    public ConfigurationFile(File configFile) {
        this.dynamicConfigFile = sessionDefaultConfigFile;
    }

    /**
     * <p>This method can read a specified config-node</p>
     *
     * @param node Name of the node to read
     * @return Config-node
     */
    //This function can read the Config-File of the program
    public String readConfig(String node, boolean showErrors) {
        try {
            if (!this.dynamicConfigFile.exists()) {
                this.getDefaultContent().saveToFile(this.dynamicConfigFile);
                if (showErrors) {
                    Log.logWarning("Couldn't find the configuration file. Writing default configuration...");
                }
            }
            VisualProperties visualProperties = new VisualProperties();
            Properties mainProperties = visualProperties.toProperties();
            FileInputStream fis = new FileInputStream(this.dynamicConfigFile);
            mainProperties.load(fis);
            return mainProperties.getProperty(InternalConfigDummy.CONFIG_MAIN_PREFIX + node);
        } catch (Exception e) {
            Log.logError(e);
            if (showErrors) {
                Log.logError("Couldn't read the main Config file... Stopping...");
            }
            System.exit(1);
        }
        return null;
    }

    /**
     * <p>Read the configuration file from the Disk</p>
     *
     * @return Properties in the VisualProperties format
     * @throws java.io.IOException
     */
    public VisualProperties read() throws IOException {
        VisualProperties vprop = new VisualProperties();
        vprop.loadFromFile(this.dynamicConfigFile);
        return vprop;
    }

    /**
     * <p>Writes the configuration file to the disk</p>
     *
     * @param vprop Configuration settings
     * @throws java.io.IOException
     */
    public void save(VisualProperties vprop) throws IOException {
        vprop.saveToFile(this.dynamicConfigFile);
    }

    /**
     * <p>Sets the current Config-File as session default, so that every new generated configuration-object that uses the standard config-file now uses this file</p>
     */
    public void setAsSessionDefault() {
        sessionDefaultConfigFile = this.dynamicConfigFile;
    }

    /**
     * <p>Generates a scratch-configuration</p>
     *
     * @return The default configuration
     */
    public static VisualProperties getDefaultContent() {
        VisualProperties vprops = new VisualProperties();
        vprops.addComment("This is a configuration template. Log- & main-settings are maintained here.");
        vprops.addComment("Log setting begin with \"" + InternalConfigDummy.CONFIG_LOG_PREFIX + "\" the main setting begin with \"" + InternalConfigDummy.CONFIG_MAIN_PREFIX + "\"");
        vprops.addEmptyLine();
        vprops.addComment("===================== MAIN CONFIGURATION =====================#");
        vprops.setProperty(InternalConfigDummy.CONFIG_MAIN_PREFIX + "langFile", "en_GB.properties");
        vprops.addEmptyLine();
        vprops.addEmptyLine();
        vprops.addComment("===================== LOG CONFIGURATION =====================#");
        vprops.addComment("his configuration XML will generate an Output like: [19.10.2013 20:14:17] Info: ==== STARTING SESSION ====");
        vprops.addComment("(Relative) path for the log file");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "path", InternalConfigDummy.ROOT_FOLDER.getName() + "/log/npGenerals.txt");
        vprops.addEmptyLine();
        vprops.addComment("What will be logged");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "debug", "false");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "info", "true");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "warn", "true");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "error", "true");
        vprops.addEmptyLine();
        vprops.addComment("Text for Logging");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "debugText", "Debug: ");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "infoText", "Info: ");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "warnText", "Warn: ");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "errorText", "Error: ");
        vprops.addEmptyLine();
        vprops.addComment("Formatting settings for the Date/Time Output");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "dateTimeFormat", "dd.MM.yyyy HH:mm:ss");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "dateTimePrefix", "[");
        vprops.setProperty(InternalConfigDummy.CONFIG_LOG_PREFIX + "dateTimeSuffix", "]");
        return vprops;
    }

    /**
     * <p>Path to the default configuration file (not session default, the hard-coded default file)</p>
     */
    final public static File DEFAULT_CONFIGURATION_FILE = InternalConfigDummy.CONFIG_FILE;

    public File getConfigurationFile() {
        return this.dynamicConfigFile;
    }
}
