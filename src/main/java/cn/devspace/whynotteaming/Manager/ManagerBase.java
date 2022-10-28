package cn.devspace.whynotteaming.Manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class ManagerBase {
    public String Map2Json(Map<String, String> Map) {
        Gson gson = new Gson();
        return gson.toJson(Map);
    }

    public Map<String, String> makeResponse(int code, int status, String Message) {
        Map<String, String> Response = new HashMap<>();
        Response.put("code", String.valueOf(code));
        Response.put("status", String.valueOf(status));
        Response.put("message", Message);
        return Response;
    }

    public String ResponseString(int code, int status, String Message){
        return this.Map2Json(this.makeResponse(code,status,Message));
    }
}
