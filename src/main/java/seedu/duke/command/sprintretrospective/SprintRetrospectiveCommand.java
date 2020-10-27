package seedu.duke.command.sprintretrospective;

import seedu.duke.command.Command;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.SprintManager;

import java.util.Hashtable;

public abstract class SprintRetrospectiveCommand extends Command {
    protected ProjectManager projectList;
    //protected SprintManager sprintList = projectList.getSelectedProject().getSprintList();
    public SprintRetrospectiveCommand(Hashtable<String, String> parameters, ProjectManager projectList, boolean shouldSave) {
        super(parameters, shouldSave);
        this.projectList = projectList;
    }

    public abstract void execute();
}
