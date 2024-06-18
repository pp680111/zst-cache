package com.zst.cache.data;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * RESP协议-大字符串
 */
@Getter
@Setter
public class RESPBulkString {
    private List<String> value;
}
