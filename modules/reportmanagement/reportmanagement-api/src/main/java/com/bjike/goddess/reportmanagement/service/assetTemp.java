package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.entity.AsseTemp;

/**
 * @Author: [zhangzhiguang]
 * @Date: [2018-05-07 11:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.gitgeek]
 */
public interface assetTemp extends Ser<AsseTemp, AssetDTO> {
    default AsseTemp save(AsseTemp entity) throws SerException {
        return entity;
    }

}
