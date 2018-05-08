package com.bjike.goddess.financeinit.action.financeinit;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.financeinit.api.AccountanCourseAPI;
import com.bjike.goddess.financeinit.bo.AccountAddDateBO;
import com.bjike.goddess.financeinit.bo.AccountanCourseBO;
import com.bjike.goddess.financeinit.bo.CourseDateBO;
import com.bjike.goddess.financeinit.bo.SecondSubjectDataBO;
import com.bjike.goddess.financeinit.dto.AccountanCourseDTO;
import com.bjike.goddess.financeinit.entity.AccountanCourse;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.excel.AccountanCourseExport;
import com.bjike.goddess.financeinit.to.AccountanCourseTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会计科目
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("accountancourse")
public class AccountanCourseAction extends BaseFileAction {
    @Autowired
    private AccountanCourseAPI accountanCourseAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = accountanCourseAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资产类总条数
     *
     * @param accountanCourseDTO 会计科目dto
     * @des 获取资产类总条数
     * @version v1
     */
    @GetMapping("v1/asseCount")
    public Result asseCount(AccountanCourseDTO accountanCourseDTO) throws ActException {
        try {
            Long count = accountanCourseAPI.countCourse(accountanCourseDTO, CategoryName.ASSETS);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 负债类总条数
     *
     * @param accountanCourseDTO 会计科目dto
     * @des 获取负债类总条数
     * @version v1
     */
    @GetMapping("v1/liabiCount")
    public Result liabiCount(AccountanCourseDTO accountanCourseDTO) throws ActException {
        try {
            Long count = accountanCourseAPI.countCourse(accountanCourseDTO, CategoryName.LIABILITIES);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 共同类总条数
     *
     * @param accountanCourseDTO 会计科目dto
     * @des 获取共同类总条数
     * @version v1
     */
    @GetMapping("v1/commCount")
    public Result commCount(AccountanCourseDTO accountanCourseDTO) throws ActException {
        try {
            Long count = accountanCourseAPI.countCourse(accountanCourseDTO, CategoryName.COMMON);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 权益类总条数
     *
     * @param accountanCourseDTO 会计科目dto
     * @des 获取权益类总条数
     * @version v1
     */
    @GetMapping("v1/rightCount")
    public Result rightCount(AccountanCourseDTO accountanCourseDTO) throws ActException {
        try {
            Long count = accountanCourseAPI.countCourse(accountanCourseDTO, CategoryName.RIGHTSINTERESTS);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 成本类总条数
     *
     * @param accountanCourseDTO 会计科目dto
     * @des 获取成本类总条数
     * @version v1
     */
    @GetMapping("v1/costCount")
    public Result costCount(AccountanCourseDTO accountanCourseDTO) throws ActException {
        try {
            Long count = accountanCourseAPI.countCourse(accountanCourseDTO, CategoryName.COST);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 损益类总条数
     *
     * @param accountanCourseDTO 会计科目dto
     * @des 获取损益类总条数
     * @version v1
     */
    @GetMapping("v1/profiCount")
    public Result profiCount(AccountanCourseDTO accountanCourseDTO) throws ActException {
        try {
            Long count = accountanCourseAPI.countCourse(accountanCourseDTO, CategoryName.PROFITLOSS);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个会计科目
     *
     * @param id 会计科目id
     * @return class AccountanCourseVO
     * @des 根据id获取会计科目
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            AccountanCourseVO accountanCourseVO = BeanTransform.copyProperties(
                    accountanCourseAPI.getOneById(id), AccountanCourseVO.class);
            return ActResult.initialize(accountanCourseVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资产类列表
     *
     * @param accountanCourseDTO 会计科目dto
     * @return class AccountanCourseVO
     * @des 获取资产类列表
     * @version v1
     */
    @GetMapping("v1/accouList")
    public Result findListAccoun(AccountanCourseDTO accountanCourseDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<AccountanCourseVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    accountanCourseAPI.listCourse(accountanCourseDTO, CategoryName.ASSETS), AccountanCourseVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 负债类列表
     *
     * @param accountanCourseDTO 会计科目dto
     * @return class AccountanCourseVO
     * @des 获取负债类列表
     * @version v1
     */
    @GetMapping("v1/liabiList")
    public Result findliabiList(AccountanCourseDTO accountanCourseDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<AccountanCourseVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    accountanCourseAPI.listCourse(accountanCourseDTO, CategoryName.LIABILITIES), AccountanCourseVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 共同类列表
     *
     * @param accountanCourseDTO 会计科目dto
     * @return class AccountanCourseVO
     * @des 获取共同类列表
     * @version v1
     */
    @GetMapping("v1/commonList")
    public Result findCommonList(AccountanCourseDTO accountanCourseDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<AccountanCourseVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    accountanCourseAPI.listCourse(accountanCourseDTO, CategoryName.COMMON), AccountanCourseVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 权益类列表
     *
     * @param accountanCourseDTO 会计科目dto
     * @return class AccountanCourseVO
     * @des 获取权益类列表
     * @version v1
     */
    @GetMapping("v1/rightList")
    public Result findRightList(AccountanCourseDTO accountanCourseDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<AccountanCourseVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    accountanCourseAPI.listCourse(accountanCourseDTO, CategoryName.RIGHTSINTERESTS), AccountanCourseVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 成本类列表
     *
     * @param accountanCourseDTO 会计科目dto
     * @return class AccountanCourseVO
     * @des 获取资产类列表
     * @version v1
     */
    @GetMapping("v1/costList")
    public Result findCostList(AccountanCourseDTO accountanCourseDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<AccountanCourseVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    accountanCourseAPI.listCourse(accountanCourseDTO, CategoryName.COST), AccountanCourseVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 损益类列表
     *
     * @param accountanCourseDTO 会计科目dto
     * @return class AccountanCourseVO
     * @des 获取损益类列表
     * @version v1
     */
    @GetMapping("v1/profitList")
    public Result findProfitList(AccountanCourseDTO accountanCourseDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<AccountanCourseVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    accountanCourseAPI.listCourse(accountanCourseDTO, CategoryName.PROFITLOSS), AccountanCourseVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加一级会计科目
     *
     * @param accountanCourseTO 会计科目数据to
     * @return class AccountanCourseVO
     * @des 添加会计科目
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/addOne")
    public Result addOneAccount(@Validated(value = ADD.class) AccountanCourseTO accountanCourseTO, BindingResult bindingResult) throws ActException {
        try {
            AccountanCourseBO accountanCourseBO = accountanCourseAPI.addOneCourse(accountanCourseTO);
            return ActResult.initialize(BeanTransform.copyProperties(accountanCourseBO, AccountanCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加二级会计科目
     *
     * @param accountanCourseTO 会计科目数据to
     * @return class AccountanCourseVO
     * @des 添加会计科目
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/addSend")
    public Result addSendAccount(@Validated(value = ADD.class) AccountanCourseTO accountanCourseTO, BindingResult bindingResult) throws ActException {
        try {
            AccountanCourseBO accountanCourseBO = accountanCourseAPI.addSendCourse(accountanCourseTO);
            return ActResult.initialize(BeanTransform.copyProperties(accountanCourseBO, AccountanCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加三级会计科目
     *
     * @param accountanCourseTO 会计科目数据to
     * @return class AccountanCourseVO
     * @des 添加会计科目
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/addThird")
    public Result addThirdAccount(@Validated(value = ADD.class) AccountanCourseTO accountanCourseTO, BindingResult bindingResult) throws ActException {
        try {
            AccountanCourseBO accountanCourseBO = accountanCourseAPI.addThreeCourse(accountanCourseTO);
            return ActResult.initialize(BeanTransform.copyProperties(accountanCourseBO, AccountanCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑会计科目
     *
     * @param accountanCourseTO 会计科目数据bo
     * @return class AccountanCourseVO
     * @des 编辑会计科目
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAccount(@Validated(value = EDIT.class) AccountanCourseTO accountanCourseTO, BindingResult bindingResult) throws ActException {
        try {
            AccountanCourseBO accountanCourseBO = accountanCourseAPI.editCourse(accountanCourseTO);
            return ActResult.initialize(BeanTransform.copyProperties(accountanCourseBO, AccountanCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除会计科目
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAccount(@PathVariable String id) throws ActException {
        try {
            accountanCourseAPI.deleteCourse(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
//            String token=request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<AccountanCourseExport> tos = ExcelUtil.mergeExcelToClazz(is, AccountanCourseExport.class, excel);
            accountanCourseAPI.importExcel(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出会计科目
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response, @RequestParam CategoryName belongCategory) throws ActException {
        try {
            String fileName = "会计科目.xlsx";
            super.writeOutFile(response, accountanCourseAPI.exportExcel(belongCategory), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * excel模板下载
     *
     * @des 下载模板团体意外险购买名单
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "会计科目模板.xlsx";
            super.writeOutFile(response, accountanCourseAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 根据会计科目名称获取所属类型
     *
     * @param accountanName 会计科目名称
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/belongByName")
    public Result belongByName(@RequestParam String accountanName) throws ActException {
        try {
            CategoryName categoryName = accountanCourseAPI.belongByName(accountanName);
            return ActResult.initialize(categoryName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据代码获取会计科目名称和方向
     *
     * @param code 代码
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findByCode")
    public Result findByCode(@RequestParam String code) throws ActException {
        try {
            CourseDateBO courseDateBO = accountanCourseAPI.findByCode(code);
            CourseDateVO courseDateVO = BeanTransform.copyProperties(courseDateBO, CourseDateVO.class);
            return ActResult.initialize(courseDateVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据报销人获取一级二级三级科目
     *
     * @param name 报销人姓名
     * @return class SubjectDataVO
     * @version v1
     */
    @GetMapping("v1/findSubjects")
    public Result findSubjects(@RequestParam String name) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(accountanCourseAPI.findSubjects(name), SubjectDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 借款根据报销人获取三级科目和一级科目
     *
     * @param name 报销人姓名
     * @return class SubjectDataVO
     * @version v1
     */
    @GetMapping("v1/findSubjects1")
    public Result findSubjects1(@RequestParam String name) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(accountanCourseAPI.findSubjects1(name), SubjectDatasVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据一级科目代码获取二级科目
     *
     * @param firstSubjectCode 一级科目代码
     * @version v1
     */
    @GetMapping("v1/findSecondSubject")
    public Result findSecondSubject(@RequestParam String firstSubjectCode) throws ActException {
        try {
            List<SecondSubjectDataBO> list = accountanCourseAPI.findSecondSubject(firstSubjectCode);
            return ActResult.initialize(BeanTransform.copyProperties(list, SecondSubjectDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有二级科目
     *
     * @param id 一级科目id
     * @des 根据一级科目的id获取所有二级科目
     * @version v1
     */
    @GetMapping("v1/listSecondSubject/{id}")
    public Result listSecondByCode(@PathVariable String id) throws ActException {
        try {
            List<AccountAddDateBO> list = accountanCourseAPI.findSecondName(id);

            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有三级科目
     *
     * @param id 二级科目id
     * @des 根据二级科目的id获取所有三级科目
     * @version v1
     */
    @GetMapping("v1/listThirdSubject/{id}")
    public Result listThirdByCode(@PathVariable String id) throws ActException {
        try {
            List<AccountAddDateBO> list = accountanCourseAPI.findThirdName(id);

            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据一级科目id获取二级科目列表
     *
     * @param id 一级科目id
     * @version v1
     */
    @GetMapping("v1/findSecondList/{id}")
    public Result findSecondList(@PathVariable String id) throws ActException {
        try {
            List<AccountanCourseVO> list = BeanTransform.copyProperties(accountanCourseAPI.findSendSubjectByOne(id),AccountanCourseBO.class);
            return ActResult.initialize(BeanTransform.copyProperties(list, AccountanCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据二级科目id获取三级科目列表
     *
     * @param id 一级科目id
     * @version v1
     */
    @GetMapping("v1/findThirdList/{id}")
    public Result findThirdList(@PathVariable String id) throws ActException {
        try {
            List<AccountanCourseVO> list = BeanTransform.copyProperties(accountanCourseAPI.findThirdSubjectBySend(id),AccountanCourseBO.class);
            return ActResult.initialize(BeanTransform.copyProperties(list, AccountanCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
