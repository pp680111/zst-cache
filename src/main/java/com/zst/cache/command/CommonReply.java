package com.zst.cache.command;

import com.zst.cache.data.RESPBulkString;
import com.zst.cache.data.RESPErrors;
import com.zst.cache.data.RESPInteger;
import com.zst.cache.data.RESPSimpleString;

/**
 * 用于存放RESP协议的一些通用返回值
 */
public interface CommonReply {
    /**
     * OK
     */
    RESPSimpleString OK = new RESPSimpleString("OK");

    /**
     * NONE
     */
    RESPSimpleString NONE = new RESPSimpleString("none");

    /**
     * 0
     */
    RESPInteger ZERO = new RESPInteger(0);

    /**
     * -2
     */
    RESPInteger NEG_2 = new RESPInteger(-2);

    /**
     * 空Bulk String
     */
    RESPBulkString NULL_BULK_STRING =
            new RESPBulkString(null);

    /**
     * 未知命令错误
     */
    RESPErrors UNKNOWN_COMMAND = new RESPErrors("ERR", "unknown command '%s'");

    /**
     * 未知错误
     */
    RESPErrors UNKNOWN_ERROR = new RESPErrors("ERR", "unknown error");

    /**
     * 命令执行错误
     */
    RESPErrors EXECUTE_FAILED =
            new RESPErrors("ERR", "Executed script failed");

    /**
     * 数据类型错误的标准返回
     */
    RESPErrors WRONG_DATA_TYPE = new RESPErrors("WRONGTYPE", "Operation against a key holding the wrong kind of value");

    /**
     * 参数数量错误的标准返回
     */
    RESPErrors WRONG_ARG_NUMBER = new RESPErrors("ERR", "wrong number of arguments for command");

    /**
     * 参数类型错误的标准返回
     */
    RESPErrors WRONG_ARG_TYPE = new RESPErrors("ERR", "wrong type of arguments for command");
}
