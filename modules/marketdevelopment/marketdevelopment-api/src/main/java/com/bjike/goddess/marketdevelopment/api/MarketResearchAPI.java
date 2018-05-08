//package com.bjike.goddess.marketdevelopment.api;
//
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.marketdevelopment.bo.MarketResearchBO;
//import com.bjike.goddess.marketdevelopment.dto.MarketResearchDTO;
//import com.bjike.goddess.marketdevelopment.to.CollectTO;
//import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
//import com.bjike.goddess.marketdevelopment.to.MarketResearchTO;
//
//import java.util.List;
//
///**
// * 市场调研业务接口
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 07:16 ]
// * @Description: [ 市场调研业务接口 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//public interface MarketResearchAPI {
//
//    /**
//     * 导航权限
//     */
//    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return null;
//    }
//
//    /**
//     * 保存市场调研数据
//     *
//     * @param to 市场调研传输对象
//     * @return
//     * @throws SerException
//     */
//    default MarketResearchBO save(MarketResearchTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 修改市场调研数据
//     *
//     * @param to 市场调研传输对象
//     * @return
//     * @throws SerException
//     */
//    default MarketResearchBO update(MarketResearchTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 删除市场调研数据
//     *
//     * @param to 市场调研传输对象
//     * @return
//     * @throws SerException
//     */
//    default MarketResearchBO delete(MarketResearchTO to) throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据业务类型查询市场调研数据
//     *
//     * @param type 业务类型
//     * @return
//     * @throws SerException
//     */
//    default List<MarketResearchBO> findByType(String type) throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据业务方向科目查询市场调研数据
//     *
//     * @param course 业务方向科目
//     * @return
//     * @throws SerException
//     */
//    default List<MarketResearchBO> findByCourse(String course) throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据业务类型和方向科目查询市场调研数据
//     *
//     * @param type   业务类型
//     * @param course 业务方向科目
//     * @return
//     * @throws SerException
//     */
//    default List<MarketResearchBO> findByCourseType(String type, String course) throws SerException {
//        return null;
//    }
//
//    /**
//     * 根据id获取市场调研数据
//     *
//     * @param id 市场调研数据id
//     * @return
//     * @throws SerException
//     */
//    default MarketResearchBO getById(String id) throws SerException {
//        return null;
//    }
//
//    /**
//     * 列表
//     *
//     * @param dto 市场调研数据传输对象
//     * @return
//     * @throws SerException
//     */
//    default List<MarketResearchBO> maps(MarketResearchDTO dto) throws SerException {
//        return null;
//    }
//
//    /**
//     * 获取总条数
//     *
//     * @return
//     * @throws SerException
//     */
//    default Integer getTotal() throws SerException {
//        return null;
//    }
//
//    /**
//     * 导出
//     *
//     * @param to 导出查询条件传输对象
//     * @return
//     * @throws SerException
//     */
//    default byte[] exportExcel(CollectTO to) throws SerException {
//        return null;
//    }
//}