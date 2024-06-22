package com.zst.cache.data;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * RESP协议-大字符串
 */
@Getter
@Setter
public class RESPBulkString implements RESPData {
    private int byteCount;
    private String value;

    public RESPBulkString(String value) {
        this.value = value;
        this.byteCount = value == null ? -1 : value.getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    public List<String> toLines() {
        if (value == null) {
            return Collections.emptyList();
        }

        List<String> ret = new ArrayList<>(2);
        ret.add("$" + byteCount);
        ret.add(value);
        return ret;
    }
}
