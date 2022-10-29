package cn.devspace.whynotteaming.Message;

import org.fusesource.jansi.Ansi;

/**
 * 信息基础类，用于定义如控制台输出，日志输出相关基础
 */
public class MessageBase implements Message{

    int MESSAGE_LEVEL = 1;

    public final static int[] RED_COLOR = {255,0,0};
    public final static int[] GREEN_COLOR = {100,180,80};
    public final static int[] WHITE_COLOR = {255,255,255};
    public final static int[] YELLOW_COLOR = {250,200,10};
    public final static int[] BLUE_COLOR = {153,204,255};

    public final static String METHOD_LOG = "LOG";
    public final static String METHOD_WARN = "WARN";
    public final static String METHOD_ERROR = "ERROR";
    protected final static String PREFIX = "WhyNotTeaming----->>";
    protected final static String PREFIX_LOG = "<<INFO>>";
    protected final static String PREFIX_WARN = "<<!>>";
    protected final static String PREFIX_ERROR = "<ERROR>";

    @Override
    public void sendLog(){

    }
    @Override
    public void sendMessage(){

    }

    /**
     * 输出格式化基础函数
     * @param Message
     * @param Color
     * @return
     */
    public static String Format(String Message, int[] Color){
        Ansi tool = new Ansi();
        Ansi Format = tool.fgRgb(Color[0],Color[1],Color[2]).a(Message).reset();
        return Format.toString();
    }




}
