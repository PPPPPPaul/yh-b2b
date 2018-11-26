package com.yh.custom;

import com.yh.pojo.TbItemParam;

import java.io.Serializable;

public class TbItemParamList extends TbItemParam implements Serializable {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
