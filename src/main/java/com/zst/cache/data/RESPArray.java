package com.zst.cache.data;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * RESP协议-数组
 */
@Getter
@Setter
public class RESPArray implements RESPData {
    private List<RESPData> value;

    @Override
    public List<String> toLines() {
        if (value == null || value.size() == 0) {
            return Collections.emptyList();
        }

        List<String> ret = new ArrayList<>();
        ret.add("*" + value.size());
        value.forEach(item -> ret.addAll(item.toLines()));
        return ret;
    }
}
