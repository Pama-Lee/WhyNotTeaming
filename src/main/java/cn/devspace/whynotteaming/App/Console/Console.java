package cn.devspace.whynotteaming.App.Console;

import cn.devspace.whynotteaming.App.Console.Thread.ConsoleThread;
import cn.devspace.whynotteaming.Manager.Annotation.Commands;
import cn.devspace.whynotteaming.Manager.Command.CommandBase;
import cn.devspace.whynotteaming.Manager.Command.CommandManager;
import cn.devspace.whynotteaming.Plugin.AppBase;

import java.util.Arrays;


public class Console extends AppBase implements CommandBase {

    @Commands(Command = "help")
    public String help(String[] args) {
        sendLog("这是Help命令!");
        sendLog("参数:" + Arrays.toString(args));
        return "123";
    }

    @Override
    public void onLoad() {
        sendLog("控制台正在启动");
        CommandManager.registerCommand(this);
    }

    @Override
    public void onEnabled() {
        ConsoleThread CT = new ConsoleThread();
        CT.start();
    }
}
