package com.zst.cache.command;

import com.zst.cache.data.RESPArray;
import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPData;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * RESP命令管理
 */
public class CommandManager {
    private static Map<String, Command> commandMap = new LinkedHashMap<>();

    public static void registerCommand(String commandName, Command command) {
        if (commandName == null || commandName.isEmpty() || command == null) {
            throw new IllegalArgumentException();
        }
        commandMap.put(commandName, command);
    }

    /**
     * 根据输入数据获取对应的命令
     *
     * @param inputData
     * @return
     */
    public static Command getCommand(RESPData inputData) {
        /*
            目前看到的Redis客户端向服务端发起的command，都是array格式的，实际command在第一行，后续都是参数
            比如:
            *2
            GET \r\n
            ｛key} \r\b
         */
        RESPArray array = (RESPArray) inputData;
        RESPBulkString commandName = (RESPBulkString) array.getValue().get(0);
        return commandMap.get(commandName.getValue());
    }
}
