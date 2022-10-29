package cn.devspace.whynotteaming.Manager;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * 主路由
 */
public class RouteManager extends ManagerBase{

    protected final static HashMap<String, ArrayList<String>> ALLOW_ROUTE = new HashMap<>();

    public RouteManager(){
    }

    public String getLanguage(){
        return new SettingManager().getSetting("app.language");
    }


}
