package com.michaels.designhub.service;

import com.michaels.designhub.repository.TemplateRepo;
import com.michaels.designhub.response.UpdateTemplateResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TemplateServiceTest {
    private TemplateService templateService;

    @Mock
    private TemplateRepo templateRepositoryMock;


    @Test
    void testUpdateTemplate() {
        Integer templateId = 456;
        Date date = new Date();
        UpdateTemplateResponse expected = new UpdateTemplateResponse();
        expected.setStatusCode(0);
        expected.setStatusMessage("Invalid template_id");
        MockitoAnnotations.openMocks(this);
        when(templateRepositoryMock.updateTemplate(templateId, date)).thenReturn(0);
        templateService = new TemplateService(templateRepositoryMock);
        assertEquals(expected, templateService.updateTemplate(templateId));
    }

}
