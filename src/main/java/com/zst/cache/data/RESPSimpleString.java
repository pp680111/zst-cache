package com.zst.cache.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * RESP协议-简单字符串
 */
@Getter
@Setter
@AllArgsConstructor
public class RESPSimpleString {
    private String value;
}
