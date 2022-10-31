package cn.devspace.whynotteaming.Plugin;


import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

abstract public class AppBase extends PluginBase{

    public AppBase(){

    }
    private Server server;
    public AppBase(Server server){
        this.server = server;
    }

    public void onLoad(){

    }

    public static void loadApps(Server server){
        File AppDir = new File(server.AppPath);
        if (AppDir.isDirectory()){
            String[] appList = AppDir.list();
            for (String App:appList){
                AppLoader appLoader = new AppLoader(server,App);
                String main = appLoader.getDescription().getMain();
                try {
                    Class<?> c = Class.forName(main);
                    AppBase app = (AppBase) c.getDeclaredConstructor().newInstance();

                    Log.AppStart(App);

                    app.onLoad();
                    server.AppClass.put(App,app);
                } catch (ClassNotFoundException e) {
                    Log.sendError(e.toString(),3);
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }

}
