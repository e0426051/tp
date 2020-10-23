package seedu.duke.command.project;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectList;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DURATION;
import static seedu.duke.command.CommandSummary.SPRINT_DURATION;
import static seedu.duke.command.CommandSummary.DESCRIPTION;


public class CreateProjectCommand extends ProjectCommand {

    private final ProjectList projectManager;

    public CreateProjectCommand(Hashtable<String, String> parameters, ProjectList projectManager) {
        super(parameters);
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

        Project proj = new Project(title, description, duration, sd);
        Ui.showToUserLn("Project successfully created.");
        projectManager.addProject(proj);
        Ui.showToUserLn(proj.toString());
    }
}