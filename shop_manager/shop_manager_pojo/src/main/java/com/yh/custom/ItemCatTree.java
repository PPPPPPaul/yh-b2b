package com.yh.custom;

import java.io.Serializable;
import java.util.List;

public class ItemCatTree implements Serializable {
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
