package cn.devspace.whynotteaming.Plugin;

import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;

import java.io.File;
import java.util.List;

public class AppLoader implements Loader {

    protected Description description;

    public AppLoader(Server server){
        loadApps(server);
    }

    protected final void loadApps(Server server){
        File AppDir = new File(server.AppPath);
        if (AppDir.isDirectory()){
            String[] appList = AppDir.list();
            for (String App:appList){
                Log.sendLog(App);
            }
        }
    }


    @Override
    public Description getDescription() {
        return description;
    }

}
