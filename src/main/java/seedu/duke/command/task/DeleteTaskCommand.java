package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Messages;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class DeleteTaskCommand extends Command {
    private final ProjectManager projectManager;

    public DeleteTaskCommand(Hashtable<String,String> parameters, ProjectManager projectManager) {
        super(parameters, true);
        this.projectManager = projectManager;
    }

    public void execute() {

        assert !projectManager.isEmpty() : "No project\n";
        if (projectManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }
        Project proj = projectManager.getSelectedProject();
        if (parameters.isEmpty()) {
            Ui.showError("Missing parameters.");
        }
        for (int i = 0; i < parameters.size(); i++) {
            try {
                int taskId = Integer.parseInt(parameters.get(Integer.toString(i)));
                if (proj.getProjectBacklog().checkTaskExist(taskId)) {
                    Task task = proj.getProjectBacklog().getTask(taskId);
                    Ui.showToUserLn("The corresponding task "
                            + task.getTitle()
                            + " has been removed from project.");
                    proj.getProjectBacklog().removeTask(taskId);
                    ArrayList<Sprint> allSprints = proj.getSprintList().getSprintList();
                    for (Sprint sprint : allSprints) {
                        if (sprint.checkTaskExist(taskId)) {
                            sprint.removeSprintTask(taskId);
                        }
                    }
                } else {
                    Ui.showError(Messages.MESSAGE_INVALID_ID);
                }
            } catch (NumberFormatException e) {
                Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
            }
        }
    }
}
