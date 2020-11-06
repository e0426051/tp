package seedu.duke.log;

import seedu.duke.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Logger {
    public static void createLog(File log) {
        try {
            if (!log.getParentFile().exists()) {
                log.getParentFile().mkdirs();
            }
            if (!log.exists()) {
                log.createNewFile();
            }
        } catch (IOException e) {
            Ui.showError("Error creating logfile.");
        }
    }

    public static String getAbsolutePathInString(String relativePath) {
        Path path = Paths.get(relativePath).toAbsolutePath();
        return path.toString();
    }
}
