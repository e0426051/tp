package seedu.duke.model.task;

import com.github.cliftonlabs.json_simple.JsonObject;
import seedu.duke.storage.JsonableObject;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.duke.log.Logger.createLog;
import static seedu.duke.log.Logger.getAbsolutePathInString;

public class Task implements JsonableObject {
    private static final Logger logger = Logger.getLogger(Task.class.getName());

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private boolean isDone;
    private ArrayList<String> memberList;
    private ArrayList<Integer> sprintList;

    public Task() {
    }

    public Task(int id, String title, String description, String priority) {
        this(id, title, description, priority, false);
    }

    public Task(int id, String title, String description, String priority, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = Priority.valueOf(priority);
        this.isDone = isDone;
        this.memberList = new ArrayList<>();
        this.sprintList = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    public void setPriority(String input) {
        priority = Priority.valueOf(input);
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public ArrayList<String> getMemberList() {
        return memberList;
    }

    public ArrayList<Integer> getSprintList() {
        return sprintList;
    }

    public void allocateToMember(String memberId) {
        memberList.add(memberId);
    }

    public void removeFromMember(String memberId) {
        memberList.remove((Object) memberId);
    }

    public void allocateToSprint(int sprintId) {
        sprintList.add(sprintId);
    }

    public void removeFromSprint(int sprintId) {
        sprintList.remove((Object) sprintId);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority.toString();
    }

    public String getTitle() {
        return title;
    }

    public boolean getDone() {
        return isDone;
    }

    public String toSimplifiedString() {
        StringBuilder taskString = new StringBuilder();
        taskString.append(String.format("[Task ID: %d]", this.id));
        taskString.append(String.format("\t[Title: %s]", this.title));
        taskString.append(String.format("\t[Completion: %s]%n", this.isDone ? "Completed" : "Incomplete"));
        return taskString.toString();
    }

    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        taskString.append(String.format("[Task]%n"));
        taskString.append(String.format("\tID: %d%n", this.id));
        taskString.append(String.format("\tTitle: %s%n", this.title));
        taskString.append(String.format("\tDescription: %s%n", this.description));
        taskString.append(String.format("\tPriority: %s%n", this.priority));
        taskString.append(String.format("\tCompletion: %s%n", this.isDone ? "Completed" : "Incomplete"));
        if (!memberList.isEmpty()) {
            taskString.append("\tAssigned to: ");
            for (String member : memberList) {
                taskString.append(String.format("%s ", member));
            }
            taskString.append(System.lineSeparator());
        } else {
            taskString.append(String.format("\tTask have yet to be assigned to anyone%n"));
        }
        return taskString.toString();
    }

    public String toJsonWithoutLogging() {
        final StringWriter writeable = new StringWriter();
        try {
            this.toJson(writeable);
        } catch (IOException e) {
            System.out.println("[Error] Cannot convert this project to JSON");
            e.printStackTrace();
        }
        return writeable.toString();
    }

    @Override
    public String toJson() {
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new SimpleFormatter();
        try {
            logger.setLevel(Level.WARNING);
            logger.addHandler(handler);
            handler.setFormatter(formatter);
        } catch (SecurityException e) {
            Ui.showError("There is a security violation while logging.");
        }
        createLog(new File("./log/scrumptious-task-task-tojson.log"));
        String pathInString = getAbsolutePathInString("./log/scrumptious-task-task-tojson.log");
        try {
            FileHandler fileHandler = new FileHandler(pathInString, true);
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
            final StringWriter writeable = new StringWriter();
            try {
                this.toJson(writeable);
                logger.log(Level.WARNING,"test");


            } catch (IOException e) {
                System.out.println("[Error] Cannot convert this project to JSON");
                e.printStackTrace();
                logger.log(Level.SEVERE, "toJson at Task class", "JSON Conversion error.");
            }
            handler.close();
            fileHandler.close();
            return writeable.toString();
        } catch (IOException e) {
            Ui.showError("Logging error. toJson will run without logging.");
            return toJsonWithoutLogging();
        }
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject jTask = new JsonObject();
        jTask.put("id", id);
        jTask.put("title", title);
        jTask.put("description", description);
        jTask.put("priority", priority.name());
        jTask.put("isDone", isDone);
        jTask.put("memberList", memberList);
        jTask.put("sprintList", sprintList);
        jTask.toJson(writer);
    }

    @Override
    public void fromJson(JsonObject jsonObj) {
        id = JsonableObject.parseInt(jsonObj, "id");
        title = JsonableObject.parseString(jsonObj,"title");
        description = JsonableObject.parseString(jsonObj, "description");
        priority = Priority.valueOf(JsonableObject.parseString(jsonObj, "priority"));
        isDone = JsonableObject.parseBoolean(jsonObj, "isDone");
        memberList = JsonableObject.parseStringList(jsonObj, "memberList");
        sprintList = JsonableObject.parseIntegerList(jsonObj, "sprintList");
    }
}
