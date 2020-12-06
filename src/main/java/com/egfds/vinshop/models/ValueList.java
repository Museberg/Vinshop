package com.egfds.vinshop.models;

import javax.persistence.Entity;
import java.util.List;

public class ValueList {

    private List<Value> values;

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public ValueList(List<Value> values) {
        this.values = values;
    }
}
