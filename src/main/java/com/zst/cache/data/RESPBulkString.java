package com.zst.cache.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * RESP协议-大字符串
 */
@Getter
@Setter
@AllArgsConstructor
public class RESPBulkString {
    private String value;
}
