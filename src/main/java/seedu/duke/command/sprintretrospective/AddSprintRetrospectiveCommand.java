package seedu.duke.command.sprintretrospective;

import seedu.duke.model.project.Project;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.model.sprint.SprintRetrospective;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.ACHIEVEMENT;
import static seedu.duke.command.CommandSummary.COMMITMENT;
import static seedu.duke.command.CommandSummary.IMPROVEMENT;
import static seedu.duke.command.CommandSummary.SPRINT_ID;
import static seedu.duke.command.CommandSummary.TITLE;

public class AddSprintRetrospectiveCommand extends SprintRetrospectiveCommand {
    public AddSprintRetrospectiveCommand(Hashtable<String, String> parameters, ProjectManager projectList) {
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

        String achievement;
        String improvement;
        String title;
        title = parameters.get(TITLE);
        achievement = parameters.get(ACHIEVEMENT);
        improvement = parameters.get(IMPROVEMENT);

        String commitment;
        int sprintId;
        commitment = parameters.get(COMMITMENT);
        sprintId = Integer.parseInt(parameters.get(SPRINT_ID).trim());

        assert !(sprintId > proj.getSprintList().size()) : "Sprint added is invalid!\n";
        if (sprintId > proj.getSprintList().size()) {
            Ui.showError("Please enter a valid sprint ID.");
            return;
        }

        proj.getRetrospectiveList().addRetrospective(sprintId, title, achievement, improvement, commitment);
        SprintRetrospective addedRetrospective = proj.getRetrospectiveList().getLatestRetrospective();
        Ui.showToUserLn("The sprint retrospective is successfully created:");
        Ui.showToUserLn(addedRetrospective.toString());

    }
}
