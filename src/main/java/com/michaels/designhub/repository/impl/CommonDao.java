package com.michaels.designhub.repository.impl;

import com.michaels.designhub.dto.UtilsDto;
import com.michaels.designhub.repository.ICommonDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Objects;

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
        try {
            StringBuilder nativeQuery = new StringBuilder("select " + utilsDto.getFunctionName());
            String functionalParams = utilsDto.getFunctionParams();
            nativeQuery.append("(");
            if (Objects.nonNull(functionalParams)) {
                nativeQuery.append(functionalParams);
            }
            nativeQuery.append(")");

            Query query = entityManager.createNativeQuery(nativeQuery.toString());

            return query.getSingleResult();

        }catch (Exception e){
            throw new RuntimeException("Exception occurred while calling Utils API with is_a_function="+utilsDto.getIsFunction()+", function_name="+utilsDto.getFunctionName()+" ,function_params="+utilsDto.getFunctionParams(),e);
        }
    }
}
