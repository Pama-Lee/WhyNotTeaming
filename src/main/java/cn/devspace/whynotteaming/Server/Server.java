package cn.devspace.whynotteaming.Server;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Manager.ManagerBase;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Router;

import java.io.FileNotFoundException;

public class Server extends ManagerBase {

    public static final String VERSION = "0.0.1-alpha";
    public static final String AUTHOR = "Pama Lee";

    public LangBase lang;

    public Server(){
        //init LangLIB
        this.lang = new LangBase();
        String test = getLanguage().TranslateOne("Test.Test","hello");
        String author = Server.getAuthor();
        Log.sendLog(author);
        Log.sendWarn(test);
    }


    public LangBase getLanguage(){
        return this.lang;
    }

    public static String getServerVersion(){
        return VERSION;
    }

    public static String getAuthor(){
        return AUTHOR;
    }

}
