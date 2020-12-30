package com.egfds.vinshop.services.EmailNewsletterService;

import com.egfds.vinshop.models.EmailNewsletter;
import com.egfds.vinshop.services.ICrudService;

import java.util.Set;

public interface IEmailNewsletterService extends ICrudService<EmailNewsletter, Long> {
    String convertEmailSetToCSV(Set<EmailNewsletter> emailNewsletterSet);
}
