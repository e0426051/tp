package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.parser.ParserManager;
import seedu.duke.storage.StorageManager;
import seedu.duke.ui.Ui;

import java.io.IOException;

import static java.lang.System.exit;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private static final String dataFilename = "data.json";

    private static ParserManager parser = new ParserManager();
    private static StorageManager sm;
    private static ProjectManager projectManager = new ProjectManager();

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the program.
     */
    private void run() {
        init();
        runInstructions();
        destroy();
    }

    /**
     * Welcome the user and initialise the local storage.
     */
    private void init() {
        try {
            sm = new StorageManager(dataFilename, projectManager);
        } catch (IOException e) {
            Ui.showError("Unable to create data/ directory, please "
                    + "ensure that we are allowed to create the data/ directory!");
        }
        try {
            sm.load();
        } catch (IOException e) {
            Ui.showError("Unable to load the data file properly, "
                    + "proceeding in empty state.");
        } catch (ClassCastException e) {
            Ui.showError("Data file is corrupted, "
                    + "proceeding in empty state. Old data.json will be cleared when the next save happens.");
        }
        Ui.showWelcomeScreen();
    }

    private void destroy() {
        try {
            sm.save();
        } catch (IOException e) {
            Ui.showError("Unable to save data successfully, your data might be lost.");
        }
    }

    /**
     * Get the user input and runs the instructions.
     * Exits when ExitCommand returns "true"
     */
    private static void runInstructions() {
        String input;
        while (!parser.isExit()) {
            input = Ui.getUserCommand();
            Command command = parser.parser(input, projectManager);
            if (command != null) {
                command.execute();
                if (command.shouldSave) {
                    try {
                        sm.save();
                    } catch (IOException e) {
                        Ui.showError("Unable to save data successfully, "
                                + "please check your data/ directory.");
                    }
                }
            }
        }
    }
}