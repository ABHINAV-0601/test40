package com.michaels.designhub.repository;

import com.michaels.designhub.dto.UtilsDto;

/**
 * @Description: ICommonDao
 * @Author: Qiang Hu
 * @Date: 10/26/2022 9:49 AM
 */
public interface ICommonDao {

    Object callFunction(UtilsDto utilsDto);
}
