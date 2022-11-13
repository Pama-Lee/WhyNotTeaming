package cn.devspace.whynotteaming.Message;

import cn.devspace.whynotteaming.Server.Server;

public class Log extends MessageBase {

    private static String plugin = null;

    public static void AppStart(String AppName) {
        System.out.print(BaseFormat(METHOD_APP, AppName));
    }

    public static void sendLog(String Message) {
        System.out.print(BaseFormat(METHOD_LOG, Message));
    }

    public static void sendAppMessage(String App, String Message) {
        plugin = App;
        System.out.print(BaseFormat(METHOD_APPMESSAGE, Message));
    }

    public static void sendWarn(String Message) {
        System.out.print(BaseFormat(METHOD_WARN, Message));
    }

    public static void sendError(String Message, int Code) {
        System.out.print(BaseFormat(METHOD_ERROR, Message));
        Server.Shutdown(Code);
    }

    public static String BaseFormat(String METHOD, String Message) {
        String Prefix = switch (METHOD) {
            case "LOG" -> Format(PREFIX_LOG, BLUE_COLOR) + Format(PREFIX, WHITE_COLOR);
            case "APP" -> Format(PREFIX_APP, GREEN_COLOR) + Format(PREFIX, WHITE_COLOR);
            case "WARN" -> Format(PREFIX_WARN, RED_COLOR) + Format(PREFIX, WHITE_COLOR);
            case "ERROR" -> Format(PREFIX_ERROR, RED_COLOR) + Format(PREFIX, RED_COLOR);

            default -> null;
        };
        if (METHOD.equals("APPMESSAGE")) {
            Prefix = Format("[<" + plugin + ">]", YELLOW_COLOR);
        }
        return Format(getTime(), PINK_COLOR) + "->" + Prefix + "\t" + Format(Message, WHITE_COLOR) + "\n";

    }
}
