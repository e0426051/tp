package seedu.duke.model.sprint;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import seedu.duke.storage.JsonableObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;

public class SprintRetrospectiveManager implements JsonableObject {

    private ArrayList<SprintRetrospective> retrospectiveList;
    private int currentIndex;

    public SprintRetrospectiveManager() {
        this.retrospectiveList = new ArrayList<>();
        setCurrentRetrospectiveIndex(0);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int size() {
        return retrospectiveList.size();
    }

    public SprintRetrospective getRetrospective(int id) {
        return retrospectiveList.get(id - 1);
    }

    public void setCurrentRetrospectiveIndex(int id) {
        this.currentIndex = id;
    }

    public void removeRetrospective(int id) {
        SprintRetrospective retrospective = retrospectiveList.get(id - 1);
        retrospectiveList.remove(retrospective);
        currentIndex--;
        return;
    }

    public SprintRetrospective getLatestRetrospective() {
        return retrospectiveList.get(this.currentIndex - 1);
    }

    public void addRetrospective(int sprintId, String title, String achievement,
            String improvement, String commitment) {
        ++currentIndex;
        retrospectiveList.add(new SprintRetrospective(title, sprintId, achievement, improvement, commitment));
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
        final JsonObject jSprintObj = new JsonObject();
        jSprintObj.put("retrospectiveList", retrospectiveList);
        jSprintObj.put("currentIndex", currentIndex);
        jSprintObj.toJson(writer);
    }

    public void fromJson(JsonObject jsonObject) {
        this.currentIndex = ((BigDecimal) jsonObject.get("currentIndex")).intValue();
        JsonArray jsonRetrospectives = new JsonArray((JsonArray) jsonObject.get("retrospectiveList"));

        for (Object o : jsonRetrospectives) {
            SprintRetrospective retrospective = new SprintRetrospective();
            retrospective.fromJson((JsonObject) o);
            retrospectiveList.add(retrospective);
            // // //
            System.out.println("AAA");
        }
    }
}

