package com.egfds.vinshop.services.ProductJPA;

import com.egfds.vinshop.models.Stock;
import com.egfds.vinshop.repositories.ProductRepos.IStockRepo;
import com.egfds.vinshop.services.ProductService.IStockService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class StockJPA implements IStockService {

    private final IStockRepo iStockRepo;

    public StockJPA(IStockRepo iStockRepo) {
        this.iStockRepo = iStockRepo;
    }

    @Override
    public Set<Stock> findAll() {
        Set<Stock> stocks = new HashSet<>();
        iStockRepo.findAll().forEach(stocks::add);
        return stocks;
    }

    @Override
    public Stock save(Stock object) {
        return iStockRepo.save(object);
    }

    @Override
    public void delete(Stock object) {
        iStockRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        iStockRepo.deleteById(aLong);
    }

    @Override
    public Optional<Stock> findById(Long aLong) {
        return iStockRepo.findById(aLong);
    }
}
