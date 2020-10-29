package seedu.duke.command.sprint;

import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.Sprint;
import seedu.duke.model.sprint.SprintRetrospective;
import seedu.duke.model.sprint.SprintRetrospectiveManager;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;

public class RemoveSprintTaskCommand extends SprintCommand {
    /**
     * Parameters for the command.
     */
    private final ArrayList<Integer> taskIds;

    public RemoveSprintTaskCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList, true);
        this.taskIds = new ArrayList<>();
    }

    public void execute() {
        try {
            checkProjectExist();
            chooseProject();
            checkSprintExist();
            prepareParameters();
            checkTasksExist(false);

            //Valid Command
            Ui.showToUser(this.projOwner.toIdString());
            removeTasks();
        } catch (DukeException e) {
            e.printExceptionMessage();
        }
    }

    /**
     * Prepare the parameters.
     */
    private void removeTasks() {
        for (int taskId : taskIds) {
            this.sprintOwner.removeSprintTask(taskId);

            //Update Task
            Task removedTask = this.projOwner.getProjectBacklog().getTask(taskId);
            removedTask.removeFromSprint(this.sprintOwner.getId());

            //Output to user
            Ui.showToUserLn(String.format("\t%s  removed from sprint %s.",
                    projOwner.getProjectBacklog().getTask(taskId).getTitle(),
                    this.sprintOwner.getId()));
        }
    }

    /**
     * Prepare the parameters.
     */
    private void prepareParameters() throws DukeException {
        if (checkTagSpecified("task")) {
            chooseSprint();
            parseParamsToInt(parameters.get("task").split(" "));
        } else {
            selectCurrentSprint();
            parseParamsToInt(parameters.values().toArray(new String[0]));
        }
    }

    /**
     * Parse parameters into Integers.
     */
    private void parseParamsToInt(String[] taskIds) {
        for (String id : taskIds) {
            this.taskIds.add(Integer.parseInt(id));
        }
    }
}
