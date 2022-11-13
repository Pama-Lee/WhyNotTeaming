package cn.devspace.whynotteaming.Manager.Command;

import cn.devspace.whynotteaming.Manager.AnnotationManager;
import cn.devspace.whynotteaming.Message.Log;
import cn.devspace.whynotteaming.Server.Server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CommandManager {


    public static void ConsoleSupport(String command) {
        if (checkCommandStyle(command) && checkCommand(command)) {
            String[] commands = command.split(" ");

            try {
                Set<CommandBase> c = Server.CommandMap.get(getCommandBody(command)).keySet();
                for (CommandBase cb : c) {
                    Server.CommandMap.get(getCommandBody(command)).get(cb).invoke(cb.getClass().getConstructor().newInstance(), (Object) getCommandArgs(command));
                }
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

        }else{
            Log.sendWarn(Server.getInstance().Translators("Command.NotFound",getCommandBody(command)));
        }
    }

    public static void registerCommand(CommandBase CB) {
        Map<String, String> map = AnnotationManager.getCommandsAnnotation(CB.getClass());
        for (String method : map.keySet()) {
            try {
                Map<CommandBase, Method> min = new HashMap<>();
                min.put(CB, CB.getClass().getMethod(method, String[].class));
                Server.CommandMap.put(map.get(method), min);
            } catch (NoSuchMethodException e) {
                Log.sendWarn(Server.getInstance().TranslateOne("Command.NotFoundMethod", map.get(method), method));
            }
        }

    }

    public static boolean checkCommandStyle(String command) {
        char c = command.charAt(0);
        return c == '/';
    }

    public static String getCommandBody(String command) {
        if (checkCommandStyle(command)) {
            String[] commands = command.split(" ");
            return commands[0].replace("/", "");
        }
        return null;
    }

    public static boolean checkCommand(String command) {
        boolean res = false;
        Set<String> cm = Server.CommandMap.keySet();
        for (String cmd : cm) {
            if (Objects.equals(cmd, getCommandBody(command))) res = true;
        }
        return res;
    }

    public static String[] getCommandArgs(String command) {
        if (checkCommandStyle(command)) {
            String[] commands = command.split(" ");
            if (commands.length > 1) {
                String[] args = new String[commands.length - 1];
                for (int i = 1; i < commands.length; i++) {
                    args[i - 1] = commands[i];
                }
                return args;
            } else {
                return null;
            }
        }

        return null;
    }

}
