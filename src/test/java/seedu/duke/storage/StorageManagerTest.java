package seedu.duke.storage;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.junit.jupiter.api.Test;
import seedu.duke.model.project.ProjectManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageManagerTest {
    private static final String TEST_FILENAME = "test-data.json";
    private static final String TEST_FILEPATH_STR = String.format("./data/%s", TEST_FILENAME);
    private static final Path TEST_FILEPATH = Paths.get(TEST_FILEPATH_STR);

    @Test
    void load() {
        assertDoesNotThrow(() -> {
            ProjectManager projMgr = new ProjectManager();
            StorageManager sm = new StorageManager(TEST_FILENAME, projMgr);
            String expectedStr = Files.readString(Paths.get("test-data/LOAD_1.json"));
            JsonObject expected = Jsoner.deserialize(expectedStr, new JsonObject());
            initTestFile(expected);
            sm.load();
        });
    }

    @Test
    void load_emptyFile_noExceptions() {
        assertDoesNotThrow(() -> {
            ProjectManager projMgr = new ProjectManager();
            StorageManager sm = new StorageManager("randomFile", projMgr);
            sm.load();
        });
    }
    
    @Test
    void load_incorrectData_exceptionThrown() {
        // JSON file should be loaded successfully if data file is valid
        assertThrows(ClassCastException.class, () -> {
            ProjectManager projMgr = new ProjectManager();
            StorageManager sm = new StorageManager(TEST_FILENAME, projMgr);
            String expectedStr = Files.readString(Paths.get("test-data/LOAD_2.json"));
            JsonObject expected = Jsoner.deserialize(expectedStr, new JsonObject());
            initTestFile(expected);
            sm.load();
        });
    }

    @Test
    void save() {
        assertDoesNotThrow(() -> {
            ProjectManager projMgr = new ProjectManager();
            StorageManager sm = new StorageManager(TEST_FILENAME, projMgr);
            String expectedStr = Files.readString(Paths.get("test-data/SAVE_1.json"));
            projMgr.fromJson((JsonObject) Jsoner.deserialize(expectedStr));
            sm.save();
        });
    }

    @Test
    void save_noPerm_exceptionThrown() {
        assertThrows(IOException.class, () -> {
            File testFile = TEST_FILEPATH.toFile();
            if (testFile.exists()) {
                testFile.delete();
            }
            testFile.createNewFile();
            testFile.setReadOnly();
            ProjectManager projMgr = new ProjectManager();
            StorageManager sm = new StorageManager(TEST_FILENAME, projMgr);
            String expectedStr = Files.readString(Paths.get("test-data/SAVE_1.json"));
            projMgr.fromJson((JsonObject) Jsoner.deserialize(expectedStr));
            sm.save();
        });
    }

    private void initTestFile(Object data) throws IOException {
        if (Files.exists(TEST_FILEPATH)) {
            Files.delete(TEST_FILEPATH);
        }
        FileWriter fw = new FileWriter(TEST_FILEPATH_STR);
        Jsoner.serialize(data, fw);
        fw.close();
    }
}