package net.megaplanet.simplisticeconomy.files;

import com.google.common.io.Files;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.charset.Charset;

public abstract class AbstractFile extends YamlConfiguration {

    private final File file;
    private Plugin plugin;

    public AbstractFile(Plugin plugin, String fileName) {
        this.file = new File(plugin.getDataFolder(), fileName);
        this.plugin = plugin;
    }

    public void load() {
        // first we clear any old data to avoid any issues
        map.clear();

        if(!file.exists()) {
            generateFile();
        }

        try {
            FileInputStream stream = new FileInputStream(file);

            // create the InputStreamReader with Charset UTF-8,
            // this allows loading files with special characters
            // such as accents
            InputStreamReader reader = new InputStreamReader(stream, Charset.forName("UTF-8"));
            BufferedReader input = new BufferedReader(reader);

            String currentLine;
            StringBuilder whole = new StringBuilder("");

            // read all the lines from the file
            while ((currentLine = input.readLine()) != null) {
                whole.append(currentLine).append("\n");
            }

            input.close();
            loadFromString(whole.toString());
        } catch (InvalidConfigurationException e1) {
            System.err.println("Error while loading file " + file.getName());
            System.err.println("This is due to an invalid YAML syntax");
            e1.printStackTrace();

            // rename the file to broken
            file.renameTo(new File(plugin.getDataFolder(), file.getName().substring(0, file.getName().length()-4) + ".broken-" + System.currentTimeMillis() + ".yml"));

            // generate a new file
            load();
        } catch (IOException e) {
            System.err.println("Error while loading file " + file.getName());
            e.printStackTrace();
        }
    }


    private void generateFile() {
        try {
            // create parent directory and the file
            Files.createParentDirs(file);
            file.createNewFile();

            // create a bufferedwriter to write into the file
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));

            // write and close the writer
            InputStream resource = plugin.getResource(file.getName());
            InputStreamReader reader = new InputStreamReader(resource, Charset.forName("UTF-8"));
            BufferedReader input = new BufferedReader(reader);

            String currentLine;
            while ((currentLine = input.readLine()) != null) {
                writer.write(currentLine + "\n");
            }

            input.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
