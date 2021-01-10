package com.egfds.vinshop.services.FarmSummaryService;

import com.egfds.vinshop.models.FarmSummary;
import com.egfds.vinshop.services.ICrudService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFarmSummaryService extends ICrudService<FarmSummary, Long> {
    void savePicturesToDirectory(MultipartFile[] files) throws IOException;
}
