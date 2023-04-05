package com.michaels.designhub.service;

import com.michaels.designhub.entity.TrainingLog;
import com.michaels.designhub.repository.TrainingLogRepository;
import com.michaels.designhub.service.impl.UtilsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;


import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UtilsServiceTest {

    @Mock
    private TrainingLogRepository trainingLogRepository;

    @InjectMocks
    private UtilsServiceImpl utilsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTrainingLog(){
        TrainingLog trainingLog = new TrainingLog();
        trainingLog.setId(1);
        trainingLog.setCreated_at(null);

        TrainingLog savedTrainingLog = new TrainingLog();
        savedTrainingLog.setId(trainingLog.getId());
        savedTrainingLog.setCreated_at(new Timestamp(System.currentTimeMillis()));
        when(trainingLogRepository.save(trainingLog)).thenReturn(savedTrainingLog);

        Integer result = utilsService.saveTrainingLog(trainingLog);

        assertEquals(trainingLog.getId(), result);
    }

    @Test
    public void testExitTrainingLog() {

        Integer id = 1;
        TrainingLog trainingLog = new TrainingLog();
        trainingLog.setId(id);
        trainingLog.setCreated_at(new Timestamp(System.currentTimeMillis()));
        trainingLog.setExited_at(null);

        when(trainingLogRepository.getOne(id)).thenReturn(trainingLog);
        when(trainingLogRepository.save(trainingLog)).thenReturn(trainingLog);

        utilsService.exitTrainingLog(id);

        verify(trainingLogRepository).getOne(id);
        verify(trainingLogRepository).save(trainingLog);
    }

}
