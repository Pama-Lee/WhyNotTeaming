package cn.devspace.whynotteaming.Message;

import org.fusesource.jansi.Ansi;

public class MessageBase implements Message{

    int MESSAGE_LEVEL = 1;

    public final static int[] RED_COLOR = {255,0,0};
    public final static int[] GREEN_COLOR = {};
    public final static int[] WHITE_COLOR = {};
    public final static int[] YELLOW_COLOR = {};
    public final static int[] BLUE_COLOR = {153,204,255};

    public final static String METHOD_LOG = "LOG";
    public final static String METHOD_WARN = "WARN";

    private final static String PREFIX_LOG = "<<INFO>>WhyNotTeaming>>>>>";
    private final static String PREFIX_WARN = "<<!>>WhyNotTeaming>>>>>";

    @Override
    public void sendLog(){

    }
    @Override
    public void sendMessage(){

    }

    @Override
    public String Format(String Message, int[] Color){
        Ansi tool = new Ansi();
        Ansi Format = tool.fgRgb(Color[0],Color[1],Color[2]).a(Message).reset();
        return Format.toString();
    }

    public String BaseFormat(String Method,String Message){
        String Prefix = null;
        switch (Method){
            case "LOG":
                Prefix = Format(PREFIX_LOG,BLUE_COLOR);
                break;
            case "WARN":
                Prefix = Format(PREFIX_WARN,RED_COLOR);
                break;
        }
        return Prefix+Message+"\n";
    }


}
