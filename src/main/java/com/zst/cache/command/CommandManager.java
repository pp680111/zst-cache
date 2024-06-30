package com.zst.cache.command;

import com.zst.cache.command.impl.ClientCommand;
import com.zst.cache.command.impl.ConfigSeriesCommand;
import com.zst.cache.command.impl.GetCommand;
import com.zst.cache.command.impl.InfoCommand;
import com.zst.cache.command.impl.PingCommand;
import com.zst.cache.command.impl.QuitCommand;
import com.zst.cache.command.impl.ScanCommand;
import com.zst.cache.command.impl.SetCommand;
import com.zst.cache.command.impl.TtlCommand;
import com.zst.cache.command.impl.TypeCommand;
import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * RESP命令管理
 */
public class CommandManager {
    private static Map<String, Command> commandMap = new LinkedHashMap<>();

    static {
        // 系统相关
        registerCommand("client",  new ClientCommand());
        registerCommand("ping",  new PingCommand());
        registerCommand("config", new ConfigSeriesCommand());
        registerCommand("quit", new QuitCommand());
        registerCommand("info", new InfoCommand());
        registerCommand("type", new TypeCommand());
        registerCommand("ttl", new TtlCommand());

        // keys
        registerCommand("set", new SetCommand());
        registerCommand("get", new GetCommand());
        registerCommand("scan", new ScanCommand());

    }

    /**
     *  注册命令
     * @param commandName
     * @param command
     */
    public static void registerCommand(String commandName, Command command) {
        if (commandName == null || commandName.isEmpty() || command == null) {
            throw new IllegalArgumentException();
        }
        commandMap.put(commandName, command);
    }

    /**
     *  获取所有命令名称
     * @return
     */
    public static List<String> getCommandNames() {
        return new ArrayList<>(commandMap.keySet());
    }

    /**
     * 根据输入数据获取对应的命令
     *
     * @param inputData
     * @return
     */
    public static Command getCommand(RESPArray inputData) {
        /*
            目前看到的Redis客户端向服务端发起的command，都是array格式的，实际command在第一行，后续都是参数
            比如:
            *2
            GET \r\n
            ｛key} \r\b
         */
        RESPBulkString commandName = (RESPBulkString) inputData.getValue().get(0);
        return commandMap.get(commandName.getValue().toLowerCase());
    }
}
