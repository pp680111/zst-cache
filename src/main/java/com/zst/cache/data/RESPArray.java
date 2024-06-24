package com.zst.cache.data;


import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class RESPArray implements RESPData {
    private List<RESPData> value;

    @Override
    public List<String> toLines() {
        List<String> ret = new ArrayList<>();
        if (value == null) {
            ret.add("*-1");
        } else if (value.size() == 0) {
            ret.add("*0");
        } else {
            ret.add("*" + value.size());
        }

        if (value != null) {
            value.forEach(item -> ret.addAll(item.toLines()));
        }
        return ret;
    }
}
