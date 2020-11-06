package seedu.duke.command.project;

import seedu.duke.exception.DukeException;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DURATION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;
import static seedu.duke.command.CommandSummary.DESCRIPTION;


public class CreateProjectCommand extends ProjectCommand {

    private final ProjectManager projectManager;

    public CreateProjectCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, true);
        this.projectManager = projectManager;
    }

    public void execute() {

        String title;
        title = this.parameters.get(TITLE).trim();

        String description;
        description = parameters.get(DESCRIPTION).trim();

        int duration;
        duration = Integer.parseInt(parameters.get(DURATION).trim());

        int sd;
        sd = Integer.parseInt(parameters.get(SPRINT_DURATION).trim());

        try {
            if (sd == 0) {
                throw new DukeException("Sprint duration cannot be zero.");
            }
            if (sd > duration || (duration % sd) != 0) {
                throw new DukeException("Project duration must be in multiples of Sprint intervals.");
            }
        } catch (DukeException e) {
            e.printExceptionMessage();
            return;
        }
        projectManager.addProject(title, description, duration, sd);
        Ui.showToUserLn("Project successfully created.");
        Project addedProj = projectManager.getSelectedProject();
        printCreatedProject(addedProj);

    }

    private void printCreatedProject(Project addProj) {
        Ui.showToUserLn("\tTitle: " + addProj.getTitle());
    }

}
