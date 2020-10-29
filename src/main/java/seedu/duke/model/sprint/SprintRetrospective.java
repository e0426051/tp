package seedu.duke.model.sprint;

import com.github.cliftonlabs.json_simple.JsonObject;
import seedu.duke.model.project.Project;
import seedu.duke.storage.JsonableObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class SprintRetrospective implements JsonableObject {
    private String achievement;
    private String improvement;
    private String commitment;
    private String title;
    private int sprintId;
    private int id;
    private Project thisProject;

    public SprintRetrospective() {
    }

    public SprintRetrospective(String title, int sprintId,
            String achievement, String improvement, String commitment, Project proj) {
        //achievement = what went well
        //improvement = what can improve
        //goal = what improvements to implement for next sprint
        this.title = title;
        this.sprintId = sprintId;
        this.achievement = achievement;
        this.improvement = improvement;
        this.commitment = commitment;
        this.thisProject = proj;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        StringBuilder retrospectiveString = new StringBuilder();
        retrospectiveString.append("Sprint Retrospective");
        retrospectiveString.append(String.format("\n[Retrospective Title: %s]", this.title));
        retrospectiveString.append(String.format("\n[Corresponding Sprint ID: %d]", this.sprintId));
        retrospectiveString.append(String.format("\n[What went well: %s]", this.achievement));
        retrospectiveString.append(String.format("\n[What needs to improve: %s]", this.improvement));
        retrospectiveString.append(String.format("\n[What to commit to in next sprint: %s]\n", this.commitment));
        return retrospectiveString.toString();
    }

    public int getSprintId() {
        return sprintId;
    }

    @Override
    public String toJson() {
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
    public void toJson(Writer writer) throws IOException {
        final JsonObject jObj = new JsonObject();
        jObj.put("sprintId", sprintId);
        jObj.put("title", title);
        jObj.put("achievement", achievement);
        jObj.put("improvement", improvement);
        jObj.put("commitment", commitment);
        jObj.put("project", thisProject.getProjectID());
        jObj.toJson(writer);
    }

    public void fromJson(JsonObject jsonObj, Project project) {
        thisProject = project;
        sprintId = JsonableObject.parseInt(jsonObj,"sprintId");
        title = JsonableObject.parseString(jsonObj, "title");
        achievement = JsonableObject.parseString(jsonObj, "achievement");
        improvement = JsonableObject.parseString(jsonObj, "improvement");
        commitment = JsonableObject.parseString(jsonObj, "commitment");
    }

    @Override
    public void fromJson(JsonObject jsonObj) {
        fromJson(jsonObj, null);
    }
}
