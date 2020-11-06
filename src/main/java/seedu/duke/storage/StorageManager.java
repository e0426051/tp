package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import seedu.duke.model.project.ProjectManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageManager {
    private static final String DEFAULT_FILEPATH = "./data/data.json";

    private final Path filepath;
    private final ProjectManager projectManager;

    public StorageManager(String filename, ProjectManager projectManager) throws IOException {
        if (filename == null || filename.isBlank()) {
            filepath = Paths.get(DEFAULT_FILEPATH);
        } else {
            filepath = Paths.get("./data", filename);
        }
        this.projectManager = projectManager;
        init();
    }

    //Public functions to be invoked

    /**
     * Save all projects into JSON data file.
     * File name of the data file is specified when the StorageManager object is instantiated
     */
    public void save() throws IOException {
        FileWriter fw = new FileWriter((filepath.toFile()));
        Jsoner.serialize(projectManager, fw);
        fw.close();
    }

    /**
     * Load the data file and deserialize it as ProjectManager.
     * File name of the data file is specified when the StorageManager object is instantiated.
     * If JSON is empty or invalid, no operations will be done to ProjectManager.
     *
     * @throws IOException Thrown when there is error opening the file or reading to the file.
     */
    public void load() throws IOException {
        if (!Files.exists(filepath)) {
            return; //file does not exist, start from a new
        }
        String rawData = loadRawData();
        JsonObject rawJson = Jsoner.deserialize(rawData, new JsonObject());
        try {
            projectManager.fromJson(rawJson);
        } catch (ClassCastException e) {
            projectManager.clearProjects();
            throw e;
        }
    }

    //Private functions    
    private void init() throws IOException {
        initDataDir();
    }

    private void initDataDir() throws IOException {
        Path path = filepath.getParent();
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    private String loadRawData() throws IOException {
        return Files.readString(filepath);
    }
}
