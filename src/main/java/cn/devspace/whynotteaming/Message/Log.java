package cn.devspace.whynotteaming.Message;

public class Log extends MessageBase{


    public static void sendLog(String Message){
        System.out.print(BaseFormat(METHOD_LOG,Message));
    }

    public static void sendWarn(String Message){
        System.out.print(BaseFormat(METHOD_WARN,Message));
    }

    public static String BaseFormat(String METHOD,String Message){
        String Prefix = switch (METHOD) {
            case "LOG" -> Format(PREFIX_LOG, BLUE_COLOR)+Format(PREFIX,WHITE_COLOR)+"\t";
            case "WARN" -> Format(PREFIX_WARN, RED_COLOR)+Format(PREFIX,WHITE_COLOR)+"\t";
            default -> null;
        };
        return Prefix+Message+"\n";
    }
}
