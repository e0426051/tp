package seedu.duke.parser;

import seedu.duke.command.MemberCommand;
import seedu.duke.command.ProjectCommand;
import seedu.duke.command.SprintCommand;
import seedu.duke.command.TaskCommand;
import seedu.duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.command.CommandSummary.ADD;
import static seedu.duke.command.CommandSummary.BYE;
import static seedu.duke.command.CommandSummary.CREATE;
import static seedu.duke.command.CommandSummary.DELETE;
import static seedu.duke.command.CommandSummary.PROJECT;
import static seedu.duke.command.CommandSummary.TASK;
import static seedu.duke.command.CommandSummary.MEMBER;
import static seedu.duke.command.CommandSummary.SPRINT;
import static seedu.duke.command.CommandSummary.SPRINT_INTERVAL;
import static seedu.duke.command.CommandSummary.VIEW;
import static seedu.duke.command.CommandSummary.DONE;
import static seedu.duke.command.CommandSummary.PRIORITY;
import static seedu.duke.command.CommandSummary.ASSIGN;
import static seedu.duke.command.CommandSummary.TITLE;
import static seedu.duke.command.CommandSummary.DESCRIPTION;
import static seedu.duke.command.CommandSummary.DEADLINE;
import static seedu.duke.command.CommandSummary.TASK_ID;


public class Parser {
    private static final Pattern CMD_PATTERN = Pattern.compile("(\\w+)\\s\\/(\\w+)\\s(.+)"); //Groups of 3: (command) (action) (options)
    private static final Pattern ARGS_PATTERN = Pattern.compile("-(\\w+)\\s([^-]+)"); //Groups of 2: (option name) (option value)
    private static final Scanner SCAN = new Scanner(System.in);
    private final Hashtable<String, String> PARAMETERS = new Hashtable<>();
    private final ArrayList<String> PARAMS = new ArrayList<>();

    public void parser() {
        String userInput = SCAN.nextLine();

        if (userInput.equals(BYE)) {
            System.out.println(BYE);
            System.exit(0);
        }

        Matcher cmdMatcher = CMD_PATTERN.matcher(userInput);
        if (cmdMatcher.matches()) { //Need to check if it matches, groupCount will always show 3
            String command = cmdMatcher.group(1); //capture first group (command)
            String action = cmdMatcher.group(2); //capture 2nd group (action)
            String rawArgs = cmdMatcher.group(3); //capture 3rd group (options)
            Matcher parameterMatcher = ARGS_PATTERN.matcher(rawArgs); //match the option

            if (!rawArgs.contains("-")) {
                String[] arguments = rawArgs.split(" ");
                PARAMS.addAll(Arrays.asList(arguments));
            } else {
                while (parameterMatcher.find()) { //go through each occurrence of options
                    PARAMETERS.put(parameterMatcher.group(1), parameterMatcher.group(2)); //put the options into the hashtable (similar to dictionary)
                }
            }

            switch (command.toLowerCase()) {
            case PROJECT:
                switch (action.toLowerCase()) {
                case CREATE:
                    try {
                        new ProjectCommand().createProjectCommand(TITLE, DESCRIPTION, DEADLINE, SPRINT_INTERVAL, PARAMETERS);
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case MEMBER:
                switch (action.toLowerCase()) {
                case ADD:
                    new MemberCommand().addMemberCommand(PARAMS);
                    break;
                case DELETE:
                    new MemberCommand().deleteMemberCommand(PARAMS);
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case TASK:
                switch (action.toLowerCase()) {
                case ADD:
                    try {
                        new TaskCommand().addTaskCommand(TITLE, DESCRIPTION, PRIORITY, PARAMETERS);
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                    break;
                case DELETE:
                    new TaskCommand().deleteTaskCommand(PARAMS);
                    break;
                case VIEW:
                    try {
                        new TaskCommand().viewTaskCommand(rawArgs);
                    } catch (NumberFormatException e) {
                        //e.printExceptionMessage();
                        System.out.println("Please enter a number!");
                    }
                    break;
                case PRIORITY:
                    try {
                        new TaskCommand().changeTaskPriorityCommand(TASK_ID, PRIORITY, PARAMETERS);
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                    break;
                case DONE:
                    try {
                        new TaskCommand().doneTaskCommand(rawArgs);
                    } catch (NumberFormatException e) {
                        //e.printExceptionMessage();
                        System.out.println("Please enter a number!");
                    }
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            case SPRINT:
                switch (action.toLowerCase()) {
                case CREATE:
                    new SprintCommand().createSprintCommand(PARAMS);
                    break;
                case ADD:
                    new SprintCommand().addSprintTaskCommand(PARAMS);
                    break;
                case DELETE:
                    new SprintCommand().deleteSprintTaskCommand(PARAMS);
                    break;
                case VIEW:
                    new SprintCommand().viewSprintCommand(PARAMS);
                    break;
                case ASSIGN:
                    new SprintCommand().assignSprintTaskCommand(PARAMS);
                    break;
                default:
                    try {
                        throw new DukeException("Invalid action!");
                    } catch (DukeException e) {
                        e.printExceptionMessage();
                    }
                }
                break;
            default:
                System.out.println("Invalid command!");
            }
        } else {
            System.out.println("Invalid command!");
        }
    }
}