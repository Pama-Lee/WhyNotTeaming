package cn.devspace.whynotteaming;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.RouteManager;
import cn.devspace.whynotteaming.Message.Log;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Router extends RouteManager{


    public String route;
    public String method;

    private LangBase lang;

    public Map<String,ArrayList<String>> RouteMap;

    public Router(String route, String method){
        this.route = route;
        this.method = method;
        this.lang = new LangBase();

        Yaml yaml = new Yaml();
        String path = this.getClass().getResource("/route.yml").getPath();
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Map<String, ArrayList<String>> Yaml = yaml.loadAs(bf,Map.class);
        this.RouteMap = Yaml;
    }

    public String start(String route, String method){
        if (!getRoute(route) || !getMethod(method)){
            Log.sendWarn(this.lang.TranslateOne("Route.Error",route,method));
            return ResponseString(1,0,TranslateOne("Route.Error.User",route));
        }

        return null;
    }

    /**
     *返回请求的路由存在情况
     */
    public boolean getRoute(String Route){
        Set<String> routes = this.RouteMap.keySet();
        return routes.contains(Route);
    }

    public boolean getMethod(String Method){
        Set<String> routes = this.RouteMap.keySet();
        for (String route: routes) {
            ArrayList<String> Methods = this.RouteMap.get(route);
            if (Methods.contains(Method)) return true;
        }
        return false;
    }

    protected void loadRoute(Map<String,ArrayList<String>> Yaml){

    }


}
