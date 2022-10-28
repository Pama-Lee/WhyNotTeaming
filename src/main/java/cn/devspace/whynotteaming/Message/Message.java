package cn.devspace.whynotteaming.Message;

public interface Message {

    int MESSAGE_LEVEL = 0;

    void sendLog();
    void sendMessage();

    static String Format(String Message, int[] Color) {
        return null;
    }
}
