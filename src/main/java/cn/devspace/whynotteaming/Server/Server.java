package cn.devspace.whynotteaming.Server;

import cn.devspace.whynotteaming.Lang.LangBase;
import cn.devspace.whynotteaming.Message.MessageBase;

public class Server {

    public static final String VERSION = "0.0.1-alpha";
    public static final String AUTHOR = "Pama Lee";

    public LangBase lang;

    public Server(){
        //init LangLIB
        this.lang = new LangBase();
        //init MessageBase
        MessageBase message = new MessageBase();
        String test = getLanguage().TranslateOne("Test.Test","hello");
        String author = Server.getAuthor();
        author = message.BaseFormat(MessageBase.METHOD_LOG,author);
        System.out.print(author);
        System.out.print(test);
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
