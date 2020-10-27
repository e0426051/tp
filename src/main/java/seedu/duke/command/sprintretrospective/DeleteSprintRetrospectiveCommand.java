package seedu.duke.command.sprintretrospective;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.SprintRetrospective;
import seedu.duke.model.sprint.SprintRetrospectiveManager;
import seedu.duke.ui.Messages;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class DeleteSprintRetrospectiveCommand extends SprintRetrospectiveCommand {

    public DeleteSprintRetrospectiveCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
        super(parameters, projectList,true);
    }

    public void execute() {

        assert !projectList.isEmpty() : "No project\n";
        if (projectList.isEmpty()) {
            Ui.showError("Please create a project first.");
            return;
        }

        Project proj = projectList.getSelectedProject();
        assert !(proj.getSprintList().size() == 0) : "There are no completed sprints!\n";
        if (proj.getSprintList().size() == 0) {
            Ui.showError("Please create and finish a sprint first.");
            return;
        }

        if (parameters.isEmpty()) {
            Ui.showError("Missing parameters.");
        }
        int offset = 0;
        for (int i = 0; i < parameters.size(); i++) {
            try {
                int id = Integer.parseInt(parameters.get(Integer.toString(i)));
                if (i < proj.getRetrospectiveList().getCurrentIndex()) {
                    SprintRetrospective retrospective = proj.getRetrospectiveList().getRetrospective(id - offset);
                    Ui.showToUserLn("The corresponding retrospective "
                            + retrospective.getTitle()
                            + " has been removed from the sprint.");
                    proj.getRetrospectiveList().removeRetrospective(id - offset);
                    offset++;
                } else {
                    Ui.showError("The retrospective ID entered does not exist");
                }
            } catch (NumberFormatException e) {
                Ui.showError("Please enter an integer!");
            } catch (IndexOutOfBoundsException e) {
                Ui.showError("ID entered is invalid!");
            }
        }
    }
}
