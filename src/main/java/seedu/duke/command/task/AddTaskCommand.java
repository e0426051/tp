package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.TITLE;

public class AddTaskCommand extends Command {
    private final ProjectManager projectManager;
    private Project proj;

    public AddTaskCommand(Hashtable<String, String> parameters, ProjectManager projectManager) {
        super(parameters, true);
        this.projectManager = projectManager;
    }

    public void execute() {
        assert !projectManager.isEmpty() : "No project\n";
        if (projectManager.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        String title;
        String description;
        String priority;

        title = parameters.get(TITLE);
        description = parameters.get(DESCRIPTION);
        priority = parameters.get(PRIORITY);


        Project proj = projectManager.getSelectedProject();
        if (!proj.getProjectBacklog().checkValidPriority(priority)) {
            Ui.showError("Invalid priority!");
            return;
        }
        proj.getProjectBacklog().addTask(title, description, priority);
        Task addedTask = proj.getProjectBacklog().getTask(proj.getProjectBacklog().getNextId() - 1);
        Ui.showToUserLn("Task successfully created.");
        Ui.showToUserLn(addedTask.toString());
    }
}


