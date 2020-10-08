package seedu.duke.command;

import seedu.duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import seedu.duke.Task;

public class TaskCommand {

    ArrayList<Task> tasks = new ArrayList<>();

    public void addTaskCommand(String title, String desc, String priority,
                               Hashtable<String, String> input) throws DukeException {
        /*
        Example of how to use the hashtable and how to throw the exception.
         */
        if (input.get(title) != null) {
            System.out.println(input.get(title));
        } else {
            throw new DukeException("Please enter a title!");
        }
        if (input.get(desc) != null) {
            System.out.println(input.get(desc));
        } else {
            throw new DukeException("Please enter a description!");
        }
        if (input.get(priority) != null) {
            System.out.println(input.get(priority));
        } else {
            throw new DukeException("Please enter a priority!");
        }

        // Insert actual code for adding tasks here.
        tasks.add(input.size(), new Task(input.get(title), input.get(desc), input.get(priority)));

    }

    public void deleteTaskCommand(ArrayList<String> input) {

        // Insert actual code for deleting tasks here.
        ArrayList<Integer> taskNrArray = new ArrayList<>(); //A integer arraylist with all numbers

        for (String indexInString : input) { //put all numbers into arraylist
            int index = Integer.parseInt(indexInString);
            taskNrArray.add(index);
        }

        Collections.sort(taskNrArray); //sort array so that it deletes the largest number first
        Collections.reverse(taskNrArray); //otherwise will out of bounds exception

        for (int taskNumber : taskNrArray) {
            System.out.println("Tasks deleted: " + tasks.get(taskNumber - 1));
            tasks.remove(taskNumber - 1); //remove task.
        }
    }

    public void viewTaskCommand(String input)
                                throws NumberFormatException {
        int id = Integer.parseInt(input) - 1;
        System.out.println(tasks.get(id).getTitle());
        System.out.println(tasks.get(id).getDescription());
        System.out.println(tasks.get(id).getPriority());
        System.out.println(tasks.get(id).getDone());
    }

    public void changeTaskPriorityCommand(String taskId, String priority,
                                          Hashtable<String, String> input) throws DukeException {
        /*
        Example of how to use the hashtable and how to throw the exception.
         */
        if (input.get(taskId) != null) {
            System.out.println(input.get(taskId));
        } else {
            throw new DukeException("Please enter a title!");
        }
        if (input.get(priority) != null) {
            System.out.println(input.get(priority));
        } else {
            throw new DukeException("Please enter a priority!");
        }

        // Insert actual code for changing task priority here.
        int id = Integer.parseInt(input.get(taskId)) - 1;
        tasks.get(id).setPriority(input.get(priority));
    }

    public void doneTaskCommand(String input)
                                throws NumberFormatException {
        //set task as done
        int id = Integer.parseInt(input) - 1;
        tasks.get(id).setAsDone();
    }
}
