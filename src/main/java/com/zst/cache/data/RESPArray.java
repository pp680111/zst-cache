package com.zst.cache.data;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * RESP协议-数组
 */
@Getter
@Setter
public class RESPArray {
    private List<Object> value;
}
