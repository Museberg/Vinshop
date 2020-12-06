package com.egfds.vinshop.services.ProductService;

import com.egfds.vinshop.models.Attribute;
import com.egfds.vinshop.services.ICrudService;

import java.util.List;

public interface IAttributeService extends ICrudService<Attribute, Long> {
    public List<Attribute> getAttributesByType(long typeId);
}
