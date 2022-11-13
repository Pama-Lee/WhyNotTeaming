package cn.devspace.whynotteaming.Manager;


import cn.devspace.whynotteaming.Message.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 主路由
 */
public class RouteManager extends ManagerBase {

    protected static Map<String, ArrayList<String>> ALLOW_ROUTE = new HashMap<>();

    public RouteManager() {
        ALLOW_ROUTE = loadRoute();
    }

    public Map<String, ArrayList<String>> loadRoute() {
        try {
            return getSingeYaml(System.getProperty("user.dir") + "/resources/route.yml", true);
        } catch (Exception fn) {
            Log.sendError(fn.toString(), 3);
            return null;
        }
    }

    public Map<String, ArrayList<String>> getRouteMap() {
        return ALLOW_ROUTE;
    }

    public String getLanguage() {
        return new SettingManager().getSetting("app.language");
    }


}
