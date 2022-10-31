package cn.devspace.whynotteaming.Plugin;

import cn.devspace.whynotteaming.Manager.ManagerBase;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AppLoader implements Loader {

    protected Description description;

    protected String AppName;
    public AppLoader(Server server,String AppName){
        this.AppName = AppName;
        this.description = loadDescription();

        //Log.sendLog(description.getVersion());
    }



    //protected final String get

    public Description loadDescription(){
        try{
            return new Description(new ClassPathResource("app/"+ this.AppName+"/app.yml").getInputStream());
        }catch (IOException ioe){
            Log.sendError(ioe.toString(),2);
            return null;
        }
    }

    @Override
    public Description getDescription() {
        return description;
    }

}
