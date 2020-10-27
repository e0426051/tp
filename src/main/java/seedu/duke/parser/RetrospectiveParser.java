package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.sprintretrospective.AddSprintRetrospectiveCommand;
import seedu.duke.command.sprintretrospective.DeleteSprintRetrospectiveCommand;
import seedu.duke.command.sprintretrospective.ViewSprintRetrospectiveCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.ACHIEVEMENT;
import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.COMMITMENT;
import static seedu.duke.command.CommandSummary.DELETE;
import static seedu.duke.command.CommandSummary.IMPROVEMENT;
import static seedu.duke.command.CommandSummary.SPRINT_ID;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.TITLE;

public class RetrospectiveParser implements ExceptionsParser {
    @Override
    public Command parseMultipleCommandsExceptions(Hashtable<String, String> parameters, String action,
                                                   ProjectManager projectListManager)
            throws DukeException {

        switch (action.toLowerCase()) {
        case ADD:
            if (!parameters.containsKey(SPRINT_ID) || !parameters.containsKey(ACHIEVEMENT)
                    || !parameters.containsKey(IMPROVEMENT) || !parameters.containsKey(COMMITMENT)
                    || !parameters.containsKey(TITLE)) {
                throw new DukeException("Missing parameters.");
            }
            if (parameters.get(SPRINT_ID).isBlank()) {
                throw new DukeException("Please enter sprint ID!");
            }
            if (parameters.get(TITLE).isBlank()) {
                throw new DukeException("Please enter title!");
            }
            if (parameters.get(ACHIEVEMENT).isBlank()) {
                throw new DukeException("Please enter an achievement!");
            }
            if (parameters.get(IMPROVEMENT).isBlank()) {
                throw new DukeException("Please enter an improvement!");
            }
            if (parameters.get(COMMITMENT).isBlank()) {
                throw new DukeException("Please enter a commitment!");
            } else {
                return new AddSprintRetrospectiveCommand(parameters, projectListManager);
            }
        case DELETE:
            assert parameters.get("0") != null : "Invalid Input";
            if (parameters.get("0") == null) {
                Ui.showError("Please do not enter dashes.");
            }
            if (parameters.get("0").isBlank() || !ParserManager.isStringContainsNumber(parameters.get("0"))) {
                throw new DukeException("please provide a retrospective ID!");
            } else {
                return new DeleteSprintRetrospectiveCommand(parameters, projectListManager);
            }
        case VIEW:
            return new ViewSprintRetrospectiveCommand(parameters, projectListManager);
        default:
            throw new DukeException("Invalid action!");
        }
    }
}
