package com.egfds.vinshop.services.EmailNewsletterJPA;

import com.egfds.vinshop.models.Cart;
import com.egfds.vinshop.models.EmailNewsletter;
import com.egfds.vinshop.repositories.EmailNewsletterRepos.IEmailNewsletterRepo;
import com.egfds.vinshop.services.EmailNewsletterService.IEmailNewsletterService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class EmailNewsletterJPA implements IEmailNewsletterService {

    private final IEmailNewsletterRepo emailNewsletterRepo;

    public EmailNewsletterJPA(IEmailNewsletterRepo emailNewsletterRepo) {
        this.emailNewsletterRepo = emailNewsletterRepo;
    }

    @Override
    public Set<EmailNewsletter> findAll() {
        Set <EmailNewsletter> set = new HashSet<>();
        emailNewsletterRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public EmailNewsletter save(EmailNewsletter object) {
        return emailNewsletterRepo.save(object);
    }

    @Override
    public void delete(EmailNewsletter object) {
        emailNewsletterRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        emailNewsletterRepo.deleteById(aLong);
    }

    @Override
    public Optional<EmailNewsletter> findById(Long aLong) {
        return findById(aLong);
    }
}
