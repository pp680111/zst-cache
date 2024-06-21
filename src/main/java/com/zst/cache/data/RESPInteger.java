package com.zst.cache.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

/**
 * RESP协议-64bit整数类型数据
 *
 * 在java中使用long表示
 */
@Getter
@Setter
@AllArgsConstructor
public class RESPInteger implements RESPData {
    private long value;

    @Override
    public List<String> toLines() {
        return Collections.singletonList(String.format(":%d", value));
    }
}
