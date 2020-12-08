package com.egfds.vinshop.services.UserJPA;

import com.egfds.vinshop.models.Zip;
import com.egfds.vinshop.repositories.UserRepos.IZipRepository;
import com.egfds.vinshop.services.ICrudService;
import com.egfds.vinshop.services.UserService.IZipService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ZipJPA implements IZipService {

    private final IZipRepository zipRepository;

    public ZipJPA(IZipRepository zipRepository) {
        this.zipRepository = zipRepository;
    }

    @Override
    public Set<Zip> findAll() {
        Set<Zip> zipCodes = new HashSet<>();
        zipRepository.findAll().forEach(zipCodes :: add);
        return zipCodes;
    }

    @Override
    public Zip save(Zip object) {
        return zipRepository.save(object);
    }

    @Override
    public void delete(Zip object) {
        zipRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        zipRepository.deleteById(aLong);
    }

    @Override
    public Optional<Zip> findById(Long aLong) {
        return zipRepository.findById(aLong);
    }
}
