package com.egfds.vinshop.services.FarmSummaryJPA;

import com.egfds.vinshop.models.FarmSummary;
import com.egfds.vinshop.repositories.FarmSummary.IFarmSummaryRepo;
import com.egfds.vinshop.services.FarmSummaryService.IFarmSummaryService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FarmSummaryJPA implements IFarmSummaryService {

    private final IFarmSummaryRepo farmSummaryRepo;

    public FarmSummaryJPA(IFarmSummaryRepo farmSummaryRepo) {
        this.farmSummaryRepo = farmSummaryRepo;
    }

    @Override
    public Set<FarmSummary> findAll() {
        Set<FarmSummary> set = new HashSet<>();
        farmSummaryRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public FarmSummary save(FarmSummary object) {
        return farmSummaryRepo.save(object);
    }

    @Override
    public void delete(FarmSummary object) {
        farmSummaryRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        farmSummaryRepo.deleteById(aLong);
    }

    @Override
    public Optional<FarmSummary> findById(Long aLong) {
        return farmSummaryRepo.findById(aLong);
    }
}
