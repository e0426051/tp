package seedu.duke.model.member;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Member implements Jsonable {

    private String userId;
    private ArrayList<Integer> allocatedTaskIds;
    
    public Member() {
    }

    public Member(String userId) {
        this.userId = userId;
        allocatedTaskIds = new ArrayList<>();

    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getAllocatedTaskIds() {
        return allocatedTaskIds;
    }

    public void setAllocatedTaskIds(ArrayList<Integer> allocatedTaskIds) {
        this.allocatedTaskIds = allocatedTaskIds;
    }

    public void allocateTask(int taskid) {
        allocatedTaskIds.add(taskid);
    }

    @Override
    public boolean equals(Object o) {
        /* Check if o is an instance of Member or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Member)) {
            return false;
        }
        Member v = (Member) o;
        // Compare the userId of members and return accordingly
        return userId.equals(v.userId);
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
        final JsonObject jMember = new JsonObject();
        jMember.put("userId", userId);
        final JsonArray jTasks = new JsonArray();
        jTasks.addAll(allocatedTaskIds);
        jMember.put("allocatedTaskIds", jTasks);
        jMember.toJson(writer);
    }
}