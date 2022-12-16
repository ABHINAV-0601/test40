package com.michaels.designhub.repository.impl;

import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.repository.ICommonDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 * @Description: CommonDao
 * @Author: Qiang Hu
 * @Date: 10/26/2022 9:53 AM
 */
@Repository
@Slf4j
public class CommonDao implements ICommonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object callFunction(UtilsDto utilsDto) {
        var target = entityManager.getEntityManagerFactory().createEntityManager();
        StoredProcedureQuery query = target.createStoredProcedureQuery(utilsDto.getFunctionName());
        try {

            String functionalParams = utilsDto.getFunctionParams();
            if(StringUtils.isNotEmpty(functionalParams)){
                functionalParams = StringUtils.strip(functionalParams,"\'");
                query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
                query.setParameter(1, functionalParams);
            }

            return query.getSingleResult();

        }catch (Exception e){
            log.error("Error occurred while calling function - {}",utilsDto.getFunctionName(),e);
        } finally {
            query.unwrap(ProcedureOutputs.class).release();
        }
        return null;
    }
}
