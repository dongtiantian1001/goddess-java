package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.entity.AsseTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @Author: [zhangzhiguang]
 * @Date: [2018-05-07 13:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.gitgeek]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class AssetTempSerImpl extends ServiceImpl<AsseTemp, AssetDTO> implements assetTemp{
    @Autowired
    private AssetDataSer assetDataSer;

    @Override
    public AsseTemp save(AsseTemp entity) throws SerException {
        if(entity !=null){
            super.save(entity);
        }
        return entity;
    }
}
