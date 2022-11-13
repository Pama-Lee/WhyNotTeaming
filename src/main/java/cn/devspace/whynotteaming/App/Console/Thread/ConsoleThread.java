package cn.devspace.whynotteaming.App.Console.Thread;

import cn.devspace.whynotteaming.Manager.Command.CommandManager;
import cn.devspace.whynotteaming.Message.Log;

import java.util.Scanner;

import static cn.devspace.whynotteaming.Message.Log.sendLog;

public class ConsoleThread extends Thread {


    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        sendLog("send:");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            // 读取整行
            String command = scanner.nextLine();
            CommandManager.ConsoleSupport(command);
        }
    }
}
