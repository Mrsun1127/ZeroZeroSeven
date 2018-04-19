package com.ffn.zerozeroseven.bean;

import java.io.Serializable;

/**
 * 测试 gridView加载的数据类型
 */

public class ItemFilterEntity implements Serializable {

    private long id;
    private String name;
    private boolean isCheck;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
