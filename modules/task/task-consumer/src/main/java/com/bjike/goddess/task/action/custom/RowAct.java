package com.bjike.goddess.task.action.custom;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.GET;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.task.api.FieldAPI;
import com.bjike.goddess.task.api.RowAPI;
import com.bjike.goddess.task.api.TableAPI;
import com.bjike.goddess.task.bo.TableBO;
import com.bjike.goddess.task.dto.RowDTO;
import com.bjike.goddess.task.to.RowTO;
import com.bjike.goddess.task.vo.FieldVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 行
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-16 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
@RequestMapping("row")
public class RowAct extends BaseFileAction {
    @Autowired
    private RowAPI rowAPI;
    @Autowired
    private FieldAPI fieldAPI;
    @Autowired
    private TableAPI tableAPI;

    /**
     * 列表
     *
     * @param dto
     * @return {name:'data',type:'string',defaultValue:'',description:'json.'}
     * @version v1
     */
    @GetMapping("v1/list")
    public String list(@Validated({GET.class}) RowDTO dto, BindingResult rs) throws ActException {
        try {
            String result = rowAPI.list(dto);
            return result;
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据量
     *
     * @param dto
     * @return {name:'data',type:'int',defaultValue:'',description:'数量.'}
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated({GET.class}) RowDTO dto, BindingResult rs) throws ActException {
        try {
            Long count = rowAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 行数据
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @des 添加数据时的 parameter如:添加姓名年龄两个字段(确保数据库列存在,不存在先添加列属性),?姓名=xxx&年龄=xxx
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) RowTO to, BindingResult rs, HttpServletRequest request) throws ActException {
        try {
            List<FieldVO> fields = fieldAPI.list(to.getTableId(), to.getNode());
            Map<String, String> fieldValMap = new HashMap<>();
            for (FieldVO field : fields) {
                String val = request.getParameter(field.getName());
                if (StringUtils.isNotBlank(val)) {
                    fieldValMap.put(field.getName(), val);
                } else {
                    if (field.isNeed()) {
                        throw new SerException(field.getName() + "不能为空");
                    }
                }
            }
            rowAPI.add(fieldValMap, to.getTableId(), to.getNode());
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 列id
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            rowAPI.delete(id);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel导入
     *
     * @param dto
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/excel/import")
    public Result excelImport(HttpServletRequest request, @Validated({ADD.class}) RowDTO dto, BindingResult rs) throws ActException {
        try {
            List<InputStream> inputStreams = getInputStreams(request);
            rowAPI.excelImport(inputStreams, dto);
            return ActResult.initialize(true);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel导出
     *
     * @param dto
     * @return {name:'data',type:'byte',defaultValue:'',description:'文件流.'}
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/excel/export")
    public Result excelExport(@Validated({GET.class}) RowDTO dto, BindingResult rs, HttpServletResponse response) throws ActException {
        try {
            try {
                TableBO table = tableAPI.findById(dto.getTableId());
                byte[] bytes = rowAPI.excelExport(dto);
                writeOutFile(response, bytes, table.getName() + ".xlsx");
            } catch (Exception e) {

            }
            return ActResult.initialize(rowAPI.excelExport(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
