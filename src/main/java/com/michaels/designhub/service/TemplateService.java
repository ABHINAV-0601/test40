package com.michaels.designhub.service;

import com.michaels.designhub.entity.Template;
import com.michaels.designhub.repository.TemplateRepo;
import com.michaels.designhub.response.UpdateTemplateResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TemplateService {
    public static final String SUCCESS = "Success";
    final
    TemplateRepo templateRepo;

    public TemplateService(TemplateRepo templateRepo) {
        this.templateRepo = templateRepo;
    }

    public List<Template> getTemplateList(String storeId) {
        return templateRepo.findTemplateByStoreId(storeId);
    }

    @Transactional
    public UpdateTemplateResponse updateTemplate(Integer templateId) {
        UpdateTemplateResponse updateTemplateResponse = new UpdateTemplateResponse();
        Date date = new Date();
        int code = templateRepo.updateTemplate(templateId,date);
        updateTemplateResponse.setStatusCode(code);
        if (code == 0){
            updateTemplateResponse.setStatusMessage("Invalid template_id");
        }else{
            updateTemplateResponse.setStatusMessage(SUCCESS);
        }
        return updateTemplateResponse;
    }

    @Transactional
    public UpdateTemplateResponse createTemplate(Template template) {
        UpdateTemplateResponse response = new UpdateTemplateResponse();
        Date date = new Date();
        if ("default".equals(template.getType())){
            templateRepo.updateTemplateByTypeAndIsActive(date,template.getStoreId());
            template.setCreatedAt(date);
            template.setUpdateAc(date);
            template.setIsActive(true);
            templateRepo.save(template);
            // fail is error
            response.setStatusCode(1);
            response.setStatusMessage(SUCCESS);
        }else if ("global".equals(template.getType())){
            int count = templateRepo.selectTemplateByTypeAndIsActive(template.getType());
            if (count < 3){
                template.setCreatedAt(date);
                template.setUpdateAc(date);
                template.setIsActive(true);
                templateRepo.save(template);
                // fail is error
                response.setStatusCode(1);
                response.setStatusMessage(SUCCESS);
            }else{
                response.setStatusCode(200);
                response.setStatusMessage("No records inserted ,max limit reached for the requested store for global type.");
            }
        }else {
            int count = templateRepo.selectCoutByTypeAndIsActiveAndStoreId(template.getType(),template.getStoreId());
            if (count < 10){
                template.setCreatedAt(date);
                template.setUpdateAc(date);
                template.setIsActive(true);
                templateRepo.save(template);
                // fail is error
                response.setStatusCode(1);
                response.setStatusMessage(SUCCESS);
            }else{
                response.setStatusCode(200);
                response.setStatusMessage("No records inserted ,max limit reached for the requested store for local type.");
            }
        }
        return response;
    }
}
