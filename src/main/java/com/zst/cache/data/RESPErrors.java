package com.zst.cache.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * RESP协议-错误信息
 */
@Getter
@Setter
@AllArgsConstructor
public class RESPErrors {
    private String type;
    private String error;
}
