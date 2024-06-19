package com.zst.cache.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * RESP协议-64bit整数类型数据
 *
 * 在java中使用long表示
 */
@Getter
@Setter
@AllArgsConstructor
public class RESPInteger {
    private long value;
}