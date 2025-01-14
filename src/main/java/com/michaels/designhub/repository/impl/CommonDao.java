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
            String functionName = utilsDto.getFunctionName();
            // Validate the function name (this can be done through a whitelist approach)
            if (!isValidFunctionName(functionName)) {
                throw new IllegalArgumentException("Invalid function name.");
            }
            StringBuilder nativeQuery = new StringBuilder("select " + functionName +"(");
            String functionalParams = utilsDto.getFunctionParams();
            // Add parameter placeholders for the function's parameters.
            if (Objects.nonNull(functionalParams)) {
                // Split parameters into individual elements if needed
                String[] params = functionalParams.split(",");
                for (int i = 0; i < params.length; i++) {
                    if (i > 0) {
                        nativeQuery.append(", ");
                    }
                    nativeQuery.append("?");
                }
            }
            nativeQuery.append(")");

            Query query = entityManager.createNativeQuery(nativeQuery.toString());

            // Bind the parameters securely (use the actual params in functionalParams)
            if (Objects.nonNull(functionalParams)) {
                String[] params = functionalParams.split(",");
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i + 1, params[i].trim()); // Bind parameter values safely
                }
            }

            return query.getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while calling Utils API with is_a_function=" + utilsDto.getIsFunction() + ", function_name=" + utilsDto.getFunctionName() + " ,function_params=" + utilsDto.getFunctionParams(), e);
        }
    }


    // Example validation method for the function name to ensure it's safe
    private boolean isValidFunctionName(String functionName) {
        // Add any necessary validation logic here
        // For example, check that the function name only contains valid characters
        return functionName != null && functionName.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

}
