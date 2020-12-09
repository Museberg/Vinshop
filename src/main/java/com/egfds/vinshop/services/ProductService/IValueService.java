package com.egfds.vinshop.services.ProductService;

import com.egfds.vinshop.models.Value;
import com.egfds.vinshop.services.ICrudService;

import java.util.List;

public interface IValueService extends ICrudService<Value, Long> {
    public void deleteByProductId(Long productId);
    public List<Value> getByProductId(Long productId);
}
