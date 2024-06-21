package com.zst.cache.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

/**
 * RESP协议-简单字符串
 */
@Getter
@Setter
@AllArgsConstructor
public class RESPSimpleString implements RESPData {
    private String value;

    @Override
    public List<String> toLines() {
        return Collections.singletonList(MessageFormat.format("+{0}", value));
    }
}
