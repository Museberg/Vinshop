package com.egfds.vinshop.services.FarmSummaryJPA;

import com.egfds.vinshop.models.FarmSummary;
import com.egfds.vinshop.repositories.FarmSummary.IFarmSummaryRepo;
import com.egfds.vinshop.services.FarmSummaryService.IFarmSummaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Override
    public void savePicturesToDirectory(MultipartFile[] files) throws IOException {
        String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static";
        new File(uploadDirectory).mkdir();
        for (MultipartFile file: files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }
    }
}
