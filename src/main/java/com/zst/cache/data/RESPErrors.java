package com.zst.cache.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

/**
 * RESP协议-错误信息
 */
@Getter
@Setter
@AllArgsConstructor
public class RESPErrors implements RESPData {
    private String type;
    private String error;

    @Override
    public List<String> toLines() {
        return Collections.singletonList(MessageFormat.format("-{0} {1}", type, error));
    }
}
