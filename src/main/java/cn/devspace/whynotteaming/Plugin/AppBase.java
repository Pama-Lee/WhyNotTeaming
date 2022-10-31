package cn.devspace.whynotteaming.Plugin;


import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.ManagerBase;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

abstract public class AppBase extends ManagerBase {

    protected String callback = null;

    public AppBase() {

    }

    private Server server;

    public AppBase(Server server) {
        this.server = server;
    }

    public void onLoad() {

    }

    public String onCall(String route,String method){
        return null;
    }



    protected void sendLog(String log){
        Log.sendAppMessage(new Exception().getStackTrace()[1].getClassName(),log);
    }

    public static void loadApps(Server server) {
        File AppDir = new File(server.AppPath);
        if (AppDir.isDirectory()) {
            String[] appList = AppDir.list();
            for (String App : appList) {
                AppLoader appLoader = new AppLoader(server, App);
                String main = appLoader.getDescription().getMain();
                try {
                    //App的初始化
                    Class<?> c = Class.forName(main);
                    AppBase app = (AppBase) c.getDeclaredConstructor().newInstance();

                    Log.AppStart(Translators("App.Start",App));
                    //开始执行onload
                    app.onLoad();
                    server.AppClass.put(App, app);
                } catch (ClassNotFoundException e) {
                    Log.sendError(e.toString(), 3);
                    throw new RuntimeException(e);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }

}
