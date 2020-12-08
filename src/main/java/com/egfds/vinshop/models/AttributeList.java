package com.egfds.vinshop.models;

import java.util.List;

public class AttributeList {

    private List<Attribute> attributes;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public AttributeList(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public AttributeList() {
    }
}
