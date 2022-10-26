package cn.devspace.whynotteaming.Server;

public class Server {

    public static final String VERSION = "0.0.1-alpha";
    public static final String AUTHOR = "Pama Lee";

    public Server(){
        System.out.print("Testing");
    }


    public static String getServerVersion(){
        return VERSION;
    }

    public static String getAuthor(){
        return AUTHOR;
    }

}
