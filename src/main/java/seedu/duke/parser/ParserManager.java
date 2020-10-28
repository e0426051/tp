package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.EmptyCommand;
import seedu.duke.command.help.HelpCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.model.project.ProjectManager;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.command.CommandSummary.BYE;
import static seedu.duke.command.CommandSummary.HELP;
import static seedu.duke.command.CommandSummary.MEMBER;
import static seedu.duke.command.CommandSummary.PROJECT;
import static seedu.duke.command.CommandSummary.SPRINT;
import static seedu.duke.command.CommandSummary.STORAGE;
import static seedu.duke.command.CommandSummary.TASK;


public class ParserManager {
    //Groups of 3: (command) (action) (options)
    private static final Pattern CMD_PATTERN = Pattern.compile("(\\w+)\\s\\/(\\w+)\\s*(.*)");
    //Groups of 2: (option name) (option value)
    private static final Pattern ARGS_PATTERN = Pattern.compile("-(\\w+)\\s([^-]+)");
    private final Hashtable<String, String> parameters = new Hashtable<>();
    private boolean exit = false;


    public Command parser(String userInput, ProjectManager projectListManager) {
        if (userInput.equals(BYE)) {
            System.out.println(BYE);
            exit = true;
            return null;
        }
        if (userInput.equals(HELP)) {
            return new HelpCommand(parameters);
        }

        Matcher cmdMatcher = CMD_PATTERN.matcher(userInput);
        if (cmdMatcher.matches()) { //Need to check if it matches, groupCount will always show 3
            String command = cmdMatcher.group(1); //capture first group (command)
            String action = cmdMatcher.group(2); //capture 2nd group (action)
            String rawArgs = cmdMatcher.group(3); //capture 3rd group (options)
            Matcher parameterMatcher = ARGS_PATTERN.matcher(rawArgs); //match the option

            if (!rawArgs.contains("-")) {
                parameters.clear();
                if (!rawArgs.isBlank()) {
                    String[] arguments = rawArgs.split(" ");
                    for (int i = 0; i < arguments.length; i++) {
                        parameters.put("" + i, arguments[i]);
                    }
                }
            } else {
                parameters.clear();
                while (parameterMatcher.find()) { //go through each occurrence of options
                    //put the options into the hashtable (similar to dictionary)
                    parameters.put(parameterMatcher.group(1), parameterMatcher.group(2).trim());
                }
            }

            try {
                switch (command.toLowerCase()) {
                case HELP:
                    return new HelpParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case PROJECT:
                    return new ProjectParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case MEMBER:
                    return new MemberParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case TASK:
                    return new TaskParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case SPRINT:
                    return new SprintParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case STORAGE:
                    return new StorageParser().parseMultipleCommandsExceptions(parameters, action, projectListManager);
                case RETROSPECTIVE:
                    return new RetrospectiveParser()
                            .parseMultipleCommandsExceptions(parameters, action, projectListManager);
                default:
                    return new InvalidCommand(parameters);
                }
            } catch (DukeException e) {
                e.printExceptionMessage();
            }
        } else {
            return new InvalidCommand(parameters);
        }
        return new EmptyCommand(parameters);
    }

    public static boolean isStringContainsNumber(String s) {
        return Pattern.compile("[0-9]").matcher(s).find();
    }

    public boolean isExit() {
        return exit;
    }
}