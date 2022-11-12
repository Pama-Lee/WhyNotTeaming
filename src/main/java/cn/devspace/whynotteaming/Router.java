package cn.devspace.whynotteaming;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.RouteManager;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Plugin.AppBase;
import cn.devspace.whynotteaming.Plugin.AppLoader;
import cn.devspace.whynotteaming.Server.Server;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Router extends RouteManager {


    public String route;
    public String method;

    private LangBase lang;

    public Map<String, ArrayList<String>> RouteMap;

    public Router(String route, String method) {
        this.route = route;
        this.method = method;
        this.lang = Server.getInstance().getLanguages();

        this.RouteMap = getRouteMap();
    }

    //开启路由分发
    public String start(String route, String method) {
        return getStart(route, method,null);
    }

    //携带请求体的路由分发
    public String start(String route, String method, Map<String,String> Request){
        if (Request.isEmpty()){
            Log.sendLog(this.RouteMap.toString());
            Log.sendWarn(this.lang.TranslateOne("Route.Error", route, method));
            return ResponseString(1, 0, TranslateOne("Route.Error.User", route));
        }else {
            return getStart(route, method,Request);
        }
    }

   //匹配路由和方法
    private String getStart(String route, String method, Map<String,String> Req) {
        if (!getRoute(route)) {
            Log.sendLog(this.RouteMap.toString());
            Log.sendWarn(this.lang.TranslateOne("Route.Error", route, method));
            return ResponseString(1, 0, TranslateOne("Route.Error.User", route));
        }else {
            AppBase al = Server.AppList.get(route);
            if (Req!=null && !Req.isEmpty()) return al.onCall(route,method,Req);
            return al.onCall(route,method);
            //return ResponseString(200,1,TranslateOne("Route.Success",route));
        }
    }

    /**
     * 返回请求的路由存在情况
     */
    public boolean getRoute(String Route) {
        Set<String> routes = this.RouteMap.keySet();
        return routes.contains(Route);
    }

    public boolean getMethod(String Method) {
        Set<String> routes = this.RouteMap.keySet();
        for (String route : routes) {
            ArrayList<String> Methods = this.RouteMap.get(route);
            if (Methods.contains(Method)) return true;
        }
        return false;
    }

    protected void loadRoute(Map<String, ArrayList<String>> Yaml) {

    }


}
