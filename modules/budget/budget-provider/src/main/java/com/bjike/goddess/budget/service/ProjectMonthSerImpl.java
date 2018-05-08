package com.bjike.goddess.budget.service;

import com.bjike.goddess.budget.bo.*;
import com.bjike.goddess.budget.dto.ProjectMonthDTO;
import com.bjike.goddess.budget.entity.ProjectMonth;
import com.bjike.goddess.budget.enums.GuideAddrStatus;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.to.ProjectMonthTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 项目收入月业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:59 ]
 * @Description: [ 项目收入月业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "budgetSerCache")
@Service
public class ProjectMonthSerImpl extends ServiceImpl<ProjectMonth, ProjectMonthDTO> implements ProjectMonthSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case DETAIL:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ProjectMonthBO save(ProjectMonthTO to) throws SerException {
        checkAddIdentity();
        ProjectMonth projectMonth = BeanTransform.copyProperties(to, ProjectMonth.class, true);
        super.save(projectMonth);
        return BeanTransform.copyProperties(projectMonth, ProjectMonthBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProjectMonthTO to) throws SerException {
        checkAddIdentity();
        ProjectMonth projectMonth = super.findById(to.getId());
        projectMonth = BeanTransform.copyProperties(to, ProjectMonth.class, true);
        super.update(projectMonth);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void deleteAll() throws SerException {
        List<ProjectMonth> list = super.findAll();
        for (ProjectMonth p : list) {
            super.remove(p.getId());
        }
    }

    @Override
    public List<ProjectWeekListBO> listProjectMonth(ProjectMonthDTO dto) throws SerException {
        List<String> areas = findArea();
        List<ProjectWeekListBO> projectWeekListBOS = new ArrayList<>();
        if (areas != null && areas.size() > 0) {
            for (String area : areas) {

                List<String> projects = findProjectByArea(area);
                List<ProjectBO> projectBOList = new ArrayList<>();
                if (projects != null && projects.size() > 0) {
                    for (String project : projects) {
                        List<String> projectNames = findNameByArPro(area, project);
                        List<ProjectNameListBO> projectNameListBOList = new ArrayList<>();

                        if (projectNames != null && projectNames.size() > 0) {
                            for (String projectName : projectNames) {

                                List<Integer> years = findYearByArProName(area, project, projectName);
                                List<YearListBO> yearListBOList = new ArrayList<>();
                                if (years != null && years.size() > 0) {
                                    for (Integer year : years) {
                                        List<MonthsListBO> monthsListBOList = findMonthByArProNaYe(area, project, projectName, year);
                                        for (MonthsListBO monthsListBO : monthsListBOList) {
                                            monthsListBO.setIncomeDifferences(monthsListBO.getTargetIncome() - monthsListBO.getPlanIncome());
                                            monthsListBO.setWorkDifferences(monthsListBO.getActualWork() - monthsListBO.getTargetWork());
                                        }
                                        YearListBO yearListBO = new YearListBO();
                                        yearListBO.setYear(year);
                                        yearListBO.setMonthListBOList2(monthsListBOList);
                                        yearListBOList.add(yearListBO);
                                    }
                                }
                                ProjectNameListBO projectNameListBO = new ProjectNameListBO();
                                projectNameListBO.setProjectName(projectName);
                                projectNameListBO.setYearListBOList(yearListBOList);
                                projectNameListBOList.add(projectNameListBO);
                            }

                        }
                        ProjectBO projectBO = new ProjectBO();
                        projectBO.setProject(project);
                        projectBO.setProjectNameListBOList(projectNameListBOList);
                        projectBOList.add(projectBO);
                    }
                }
                ProjectWeekListBO projectWeekListBO = new ProjectWeekListBO();
                projectWeekListBO.setArrival(area);
                projectWeekListBO.setProjectBOList(projectBOList);
                projectWeekListBOS.add(projectWeekListBO);
            }
        }
        int limit = dto.getLimit();
        int start = limit * dto.getPage();
        return projectWeekListBOS.stream().skip(start).limit(limit).collect(Collectors.toList());

    }

    /**
     * 获取地区
     *
     * @return
     * @throws SerException
     */
    public List<String> findArea() throws SerException {
        List<ProjectMonth> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectMonth::getArrival).distinct().collect(Collectors.toList());
    }

    /**
     * 根据地区获取所属项目组
     *
     * @return
     * @throws SerException
     */
    public List<String> findProjectByArea(String area) throws SerException {
        ProjectMonthDTO projectMonthDTO = new ProjectMonthDTO();
        projectMonthDTO.getConditions().add(Restrict.eq("arrival", area));
        List<ProjectMonth> list = super.findByCis(projectMonthDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectMonth::getProject).distinct().collect(Collectors.toList());
    }

    /**
     * 根据地区,所属项目组获取内部项目名称
     *
     * @return
     * @throws SerException
     */
    public List<String> findNameByArPro(String area, String project) throws SerException {
        ProjectMonthDTO projectMonthDTO = new ProjectMonthDTO();
        projectMonthDTO.getConditions().add(Restrict.eq("arrival", area));
        projectMonthDTO.getConditions().add(Restrict.eq("project", project));
        List<ProjectMonth> list = super.findByCis(projectMonthDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectMonth::getProjectName).distinct().collect(Collectors.toList());
    }

    /**
     * 根据地区,所属项目组,内部项目名称获取年度
     *
     * @return
     * @throws SerException
     */
    public List<Integer> findYearByArProName(String area, String project, String projectName) throws SerException {
        ProjectMonthDTO projectMonthDTO = new ProjectMonthDTO();
        projectMonthDTO.getConditions().add(Restrict.eq("arrival", area));
        projectMonthDTO.getConditions().add(Restrict.eq("project", project));
        projectMonthDTO.getConditions().add(Restrict.eq("projectName", projectName));
        List<ProjectMonth> list = super.findByCis(projectMonthDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(ProjectMonth::getYear).distinct().collect(Collectors.toList());
    }

    /**
     * 根据地区,所属项目组,内部项目名称,年度获取月份
     *
     * @return
     * @throws SerException
     */
    public List<MonthsListBO> findMonthByArProNaYe(String area, String project, String projectName, Integer year) throws SerException {
        ProjectMonthDTO projectMonthDTO = new ProjectMonthDTO();
        projectMonthDTO.getConditions().add(Restrict.eq("arrival", area));
        projectMonthDTO.getConditions().add(Restrict.eq("project", project));
        projectMonthDTO.getConditions().add(Restrict.eq("projectName", projectName));
        projectMonthDTO.getConditions().add(Restrict.eq("year", year));
        List<ProjectMonth> list = super.findByCis(projectMonthDTO);
        return BeanTransform.copyProperties(list, MonthsListBO.class);
    }

    @Override
    public List<ProjectMonthBO> list(ProjectMonthDTO dto) throws SerException {
        checkSeeIdentity();
        List<ProjectMonth> list = super.findByCis(dto, true);
        List<ProjectMonthBO> boList = new ArrayList<ProjectMonthBO>();
        for (ProjectMonth a : list) {
            ProjectMonthBO bo = BeanTransform.copyProperties(a, ProjectMonthBO.class);
            bo.setWorkDifferences(a.getActualWork() - a.getTargetWork());
            bo.setIncomeDifferences(a.getTargetIncome() - a.getPlanIncome());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public ProjectMonthBO findByID(String id) throws SerException {
        ProjectMonth projectMonth = super.findById(id);
        ProjectMonthBO bo = BeanTransform.copyProperties(projectMonth, ProjectMonthBO.class);
        bo.setWorkDifferences(projectMonth.getActualWork() - projectMonth.getTargetWork());
        bo.setIncomeDifferences(projectMonth.getTargetIncome() - projectMonth.getPlanIncome());
        return bo;
    }

    /**
     * 查找所有地区
     *
     * @return class String
     * @throws SerException
     */
    private List<String> findAllArrivals() throws SerException {
        List<ProjectMonth> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMonth p : list) {
            set.add(p.getArrival());
        }
        List<String> l = new ArrayList<String>(set);
        return l;
    }

    /**
     * 查找所有项目
     *
     * @return class String
     * @throws SerException
     */
    @Override
    public List<String> findAllProjects() throws SerException {
        List<ProjectMonth> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (ProjectMonth p : list) {
            set.add(p.getProject());
        }
        List<String> l = new ArrayList<String>(set);
        return l;
    }

    @Override
    public List<ProjectMonthCountBO> collect(ProjectMonthDTO dto) throws SerException {
        checkSeeIdentity();
        List<ProjectMonthCountBO> boList = new ArrayList<>();
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;

        ProjectMonthDTO dto1 = new ProjectMonthDTO();
        if (null != dto.getProjects()) {
            for (String project : dto.getProjects()) {
                dto1 = new ProjectMonthDTO();
                dto1 = condition(dto);
                dto1.getConditions().add(Restrict.eq("project", project));
                dto1.getSorts().add("year=desc");
                dto1.getSorts().add("month=asc");
                List<ProjectMonth> projectWeeks = super.findByCis(dto1);
                if (null != projectWeeks && projectWeeks.size() > 0) {
                    List<String> arrivals = projectWeeks.stream().map(ProjectMonth::getArrival).distinct().collect(Collectors.toList());
                    List<Integer> years = projectWeeks.stream().map(ProjectMonth::getYear).distinct().collect(Collectors.toList());
                    List<Integer> months = projectWeeks.stream().map(ProjectMonth::getMonth).distinct().collect(Collectors.toList());
                    for (String area : arrivals) {
                        for (Integer year : years) {
//                            for (Integer month : months) {
                            for (ProjectMonth entity : projectWeeks) {
                                if (entity.getArrival().equals(area) && entity.getYear().equals(year)) {
                                    targetIncomeSum += entity.getTargetIncome();
                                    planIncomeSum += entity.getPlanIncome();
                                    double incomeDifference = entity.getPlanIncome() - entity.getTargetIncome();
                                    incomeDifferencesSum += incomeDifference;
                                    targetWorkSum += entity.getTargetWork();
                                    actualWorkSum += entity.getActualWork();
                                    int workDifference = entity.getActualWork() - entity.getTargetWork();
                                    workDifferencesSum += workDifference;
                                }
                            }
                            if (targetWorkSum != 0) {
                                ProjectMonthCountBO bo = new ProjectMonthCountBO();
                                bo.setArrival(area);
                                bo.setProject(project);
                                bo.setYear(year);
//                                    bo.setMonth(month);
//                                    bo.setProjectName(price);
                                bo.setTargetWorkSum(targetWorkSum);
                                bo.setActualWorkSum(actualWorkSum);
                                bo.setWorkDifferencesSum(workDifferencesSum);
                                bo.setTargetIncomeSum(targetIncomeSum);
                                bo.setPlanIncomeSum(planIncomeSum);
                                bo.setIncomeDifferencesSum(incomeDifferencesSum);
                                boList.add(bo);
                                targetWorkSum = 0;
                                actualWorkSum = 0;
                                workDifferencesSum = 0;
                                targetIncomeSum = 0.00;
                                planIncomeSum = 0.00;
                                incomeDifferencesSum = 0.00;     //置为0
                            }
                        }
                    }
                }

            }
        } else {
            dto1 = new ProjectMonthDTO();
            dto1 = condition(dto);
            dto1.getSorts().add("year=desc");
            dto1.getSorts().add("month=asc");
            List<ProjectMonth> projectWeeks = super.findByCis(dto1);
            if (null != projectWeeks && projectWeeks.size() > 0) {
                List<String> arrivals = projectWeeks.stream().map(ProjectMonth::getArrival).distinct().collect(Collectors.toList());
                List<Integer> years = projectWeeks.stream().map(ProjectMonth::getYear).distinct().collect(Collectors.toList());
                List<Integer> months = projectWeeks.stream().map(ProjectMonth::getMonth).distinct().collect(Collectors.toList());
                List<String> projects = projectWeeks.stream().map(ProjectMonth::getProject).distinct().collect(Collectors.toList());
                for (String area : arrivals) {
                    for (Integer year : years) {
//                        for (Integer month : months) {
                        for (String project : projects) {
                            for (ProjectMonth entity : projectWeeks) {
                                if (entity.getArrival().equals(area) && entity.getYear().equals(year) && entity.getProject().equals(project)) {
                                    targetIncomeSum += entity.getTargetIncome();
                                    planIncomeSum += entity.getPlanIncome();
                                    double incomeDifference = entity.getPlanIncome() - entity.getTargetIncome();
                                    incomeDifferencesSum += incomeDifference;
                                    targetWorkSum += entity.getTargetWork();
                                    actualWorkSum += entity.getActualWork();
                                    int workDifference = entity.getActualWork() - entity.getTargetWork();
                                    workDifferencesSum += workDifference;
                                }
                            }
                            if (targetWorkSum != 0) {
                                ProjectMonthCountBO bo = new ProjectMonthCountBO();
                                bo.setArrival(area);
                                bo.setProject(project);
                                bo.setYear(year);
//                                    bo.setMonth(month);
//                                    bo.setProjectName(price);
                                bo.setTargetWorkSum(targetWorkSum);
                                bo.setActualWorkSum(actualWorkSum);
                                bo.setWorkDifferencesSum(workDifferencesSum);
                                bo.setTargetIncomeSum(targetIncomeSum);
                                bo.setPlanIncomeSum(planIncomeSum);
                                bo.setIncomeDifferencesSum(incomeDifferencesSum);
                                boList.add(bo);
                                targetWorkSum = 0;
                                actualWorkSum = 0;
                                workDifferencesSum = 0;
                                targetIncomeSum = 0.00;
                                planIncomeSum = 0.00;
                                incomeDifferencesSum = 0.00;     //置为0
                            }
                        }
                    }
                }
            }
        }
        return boList;

    }

    @Override
    public OptionBO figureShow() throws SerException {
        List<ProjectMonth> projectMonths = super.findAll();
        List<ProjectMonth> list = new ArrayList<>(0);
        if (null != projectMonths && projectMonths.size() > 0) {
            List<String> projectNames = projectMonths.stream().map(ProjectMonth::getProjectName).distinct().collect(Collectors.toList());
            for (String projectName : projectNames) {
                ProjectMonth entity = findData(projectName);
                if (null != entity) {
                    list.add(entity);
                }
            }
        }

        String text_1 = "项目收入月";

        return getOptionBO(text_1, list);
    }

    private OptionBO getOptionBO(String text_1, List<ProjectMonth> list) throws SerException {
        TitleBO title = new TitleBO();
        title.setText(text_1);

        TooltipBO tooltip = new TooltipBO();

        List<String> nameList = new ArrayList<>(0);
        nameList.add("目标任务量");
        nameList.add("实际任务量");
        nameList.add("目标收入");
        nameList.add("实际收入");
        String[] data1 = (String[]) nameList.toArray(new String[nameList.size()]);
        LegendBO legend = new LegendBO();
        legend.setData(data1);

        List<String> names = list.stream().map(ProjectMonth::getProjectName).distinct().collect(Collectors.toList());
        XAxisBO xAxis = new XAxisBO();
        String[] data2 = (String[]) names.toArray(new String[names.size()]);
        xAxis.setData(data2);

        YAxisBO yAxis = new YAxisBO();

        List<SeriesBO> seriesBOList = new ArrayList<>(0);

        for (String name : nameList) {
            SeriesBO seriesBO = new SeriesBO();
            seriesBO.setName(name);
            if ("目标任务量".equals(name) || "实际任务量".equals(name)) {
                seriesBO.setType("bar");
            } else {
                seriesBO.setType("line");
            }

            List<Double> numbers = new ArrayList<>(0);
            for (ProjectMonth entity : list) {
                for (String projectName : names) {
                    if ("目标任务量".equals(name) && entity.getProjectName().equals(projectName)) {
                        Double value = 0d;
                        Integer val = entity.getTargetWork();
                        value = Double.valueOf(String.valueOf(val));
                        numbers.add(value);
                    }
                    if ("实际任务量".equals(name) && entity.getProjectName().equals(projectName)) {
                        Double value = 0d;
                        Integer val = entity.getActualWork();
                        value = Double.valueOf(String.valueOf(val));
                        numbers.add(value);
                    }
                    if ("目标收入".equals(name) && entity.getProjectName().equals(projectName)) {
                        Double value = 0d;
                        value = entity.getTargetIncome();
                        numbers.add(value);
                    }
                    if ("实际收入".equals(name) && entity.getProjectName().equals(projectName)) {
                        Double value = 0d;
                        value = entity.getPlanIncome();
                        numbers.add(value);
                    }
                }
            }

            Double[] data3 = numbers.toArray(new Double[numbers.size()]);
            seriesBO.setData(data3);
            seriesBOList.add(seriesBO);
        }

        SeriesBO[] seriesBOs = seriesBOList.toArray(new SeriesBO[seriesBOList.size()]);
        OptionBO option = new OptionBO();
        option.setLegend(legend);
        option.setSeries(seriesBOs);
        option.setTitle(title);
        option.setTooltip(tooltip);
        option.setxAxis(xAxis);
        option.setyAxis(yAxis);
        return option;
    }

    private ProjectMonth findData(String projectName) throws SerException {
        String[] files = new String[]{"targetWork", "actualWork", "targetIncome", "planIncome", "projectName"};
        Integer year = LocalDate.now().getYear();
        StringBuilder sql = new StringBuilder("select ifnull(sum(targetWork),0) as targetWork, ");
        sql.append(" ifnull(sum(actualWork),0) as actualWork, ");
        sql.append(" ifnull(sum(targetIncome),0) as targetIncome, ");
        sql.append(" ifnull(sum(planIncome),0) as planIncome, projectName ");
        sql.append(" from budget_projectmonth ");
        sql.append(" where year = '" + year + "' ");
        sql.append(" and projectName = '" + projectName + "' ");

        List<ProjectMonth> list = super.findBySql(sql.toString(), ProjectMonth.class, files);
        return list.get(0).getProjectName() == null ? null : list.get(0);
    }

    /**
     * 查找所有年份
     *
     * @return class Integer
     * @throws SerException
     */
    private List<Integer> findAllYears() throws SerException {
        List<ProjectMonth> list = super.findAll();
        Set<Integer> set = new HashSet<Integer>();
        for (ProjectMonth p : list) {
            set.add(p.getYear());
        }
        List<Integer> l = new ArrayList<Integer>(set);
        return l;
    }

    @Override
    public List<ProjectMonthCountBO> count() throws SerException {
        checkSeeIdentity();
        List<String> arrivals = findAllArrivals();
        List<String> projects = findAllProjects();
        List<Integer> years = findAllYears();
        ProjectMonthDTO dto = new ProjectMonthDTO();
        List<ProjectMonth> list = super.findByCis(dto);
        List<ProjectMonthCountBO> boList = new ArrayList<ProjectMonthCountBO>();
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        for (String arrival : arrivals) {
            for (String project : projects) {
                for (Integer year : years) {
                    for (ProjectMonth projectMonth : list) {
                        if (projectMonth.getArrival().equals(arrival) && projectMonth.getProject().equals(project) && projectMonth.getYear().equals(year)) {
                            targetIncomeSum += projectMonth.getTargetIncome();
                            planIncomeSum += projectMonth.getPlanIncome();
                            double incomeDifference = projectMonth.getTargetIncome() - projectMonth.getPlanIncome();
                            incomeDifferencesSum += incomeDifference;
                            targetWorkSum += projectMonth.getTargetWork();
                            actualWorkSum += projectMonth.getActualWork();
                            int workDifference = projectMonth.getActualWork() - projectMonth.getTargetWork();
                            workDifferencesSum += workDifference;
                        }
                    }
                    if (targetWorkSum != 0) {
                        ProjectMonthCountBO bo = new ProjectMonthCountBO();
                        bo.setArrival(arrival);
                        bo.setProject(project);
                        bo.setYear(year);
                        bo.setTargetIncomeSum(targetIncomeSum);
                        bo.setPlanIncomeSum(planIncomeSum);
                        bo.setIncomeDifferencesSum(incomeDifferencesSum);
                        bo.setTargetWorkSum(targetWorkSum);
                        bo.setActualWorkSum(actualWorkSum);
                        bo.setWorkDifferencesSum(workDifferencesSum);
                        boList.add(bo);
                        targetIncomeSum = 0.00;
                        planIncomeSum = 0.00;
                        incomeDifferencesSum = 0.00;
                        targetWorkSum = 0;
                        actualWorkSum = 0;
                        workDifferencesSum = 0;
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public List<ProjectMonthCountBO> conditionsCount(ProjectMonthDTO dto1) throws SerException {
        checkSeeIdentity();
        List<String> arrivals = findAllArrivals();
        List<Integer> years = findAllYears();
        List<ProjectMonthCountBO> boList = new ArrayList<ProjectMonthCountBO>();
        Double targetIncomeSum = 0.00;
        Double planIncomeSum = 0.00;
        Double incomeDifferencesSum = 0.00;
        Integer targetWorkSum = 0;
        Integer actualWorkSum = 0;
        Integer workDifferencesSum = 0;
        String[] projects = dto1.getProjects();
        if (projects != null) {
            for (String project : projects) {
                ProjectMonthDTO dto = new ProjectMonthDTO();
                dto.getConditions().add(Restrict.eq("project", project));
                List<ProjectMonth> list = super.findByCis(dto);
                for (String arrival : arrivals) {
                    for (Integer year : years) {
                        for (ProjectMonth projectMonth : list) {
                            if (projectMonth.getArrival().equals(arrival) && projectMonth.getYear().equals(year)) {
                                targetIncomeSum += projectMonth.getTargetIncome();
                                planIncomeSum += projectMonth.getPlanIncome();
                                double incomeDifference = projectMonth.getTargetIncome() - projectMonth.getPlanIncome();
                                incomeDifferencesSum += incomeDifference;
                                targetWorkSum += projectMonth.getTargetWork();
                                actualWorkSum += projectMonth.getActualWork();
                                int workDifference = projectMonth.getActualWork() - projectMonth.getTargetWork();
                                workDifferencesSum += workDifference;
                            }
                        }
                        if (targetWorkSum != 0) {
                            ProjectMonthCountBO bo = new ProjectMonthCountBO();
                            bo.setArrival(arrival);
                            bo.setProject(project);
                            bo.setYear(year);
                            bo.setTargetIncomeSum(targetIncomeSum);
                            bo.setPlanIncomeSum(planIncomeSum);
                            bo.setIncomeDifferencesSum(incomeDifferencesSum);
                            bo.setTargetWorkSum(targetWorkSum);
                            bo.setActualWorkSum(actualWorkSum);
                            bo.setWorkDifferencesSum(workDifferencesSum);
                            boList.add(bo);
                            targetIncomeSum = 0.00;
                            planIncomeSum = 0.00;
                            incomeDifferencesSum = 0.00;
                            targetWorkSum = 0;
                            actualWorkSum = 0;
                            workDifferencesSum = 0;
                        }
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public List<ProjectWeekBO> findDetail(String id) throws SerException {
        checkSeeIdentity();
        ProjectMonth projectMonth = super.findById(id);
        if (projectMonth == null) {
            throw new SerException("该对象不存在");
        }
        List<ProjectWeekBO> list = new ArrayList<>(0);
//        String[] arrivals = new String[]{projectMonth.getArrival()};
//        String[] projects = new String[]{projectMonth.getProject()};
//        Integer[] years = new Integer[]{projectMonth.getYear()};
//        Integer[] months = new Integer[]{projectMonth.getMonth()};
//        List<ProjectWeekBO> list = null;
//        for (int i = 0; i < arrivals.length && i < projects.length && i < years.length && i < months.length; i++) {
//            String sql = "SELECT week,targetWork,actualWork,price,targetIncome,planIncome\n" +
//                    "from budget_projectweek\n" +
//                    "where arrival='" + arrivals[i] + "' AND project='" + projects[i] + "' AND year='" + years[i] + "' AND month='" + months[i] + "'";
//            String[] fields = new String[]{"week", "targetWork", "actualWork", "price", "targetIncome", "planIncome"};
//            list = super.findBySql(sql, ProjectWeekBO.class, fields);
//        }
//        for (ProjectWeekBO bo : list) {
//            bo.setArrival(projectMonth.getArrival());
//            bo.setProject(projectMonth.getProject());
//            bo.setYear(projectMonth.getYear());
//            bo.setMonth(projectMonth.getMonth());
//            bo.setIncomeDifferences(bo.getTargetIncome() - bo.getPlanIncome());
//            bo.setWorkDifferences(bo.getActualWork() - bo.getTargetWork());
//        }
        list.add(BeanTransform.copyProperties(projectMonth, ProjectMonthBO.class));
        return list;
    }

    @Override
    public Long countNum(ProjectMonthDTO dto) throws SerException {
        return super.count(dto);
    }

    private ProjectMonthDTO condition(ProjectMonthDTO dto) throws SerException {
        ProjectMonthDTO dto1 = new ProjectMonthDTO();
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto1.getConditions().add(Restrict.eq("arrival", dto.getArea()));
        }
        if (StringUtils.isNotBlank(dto.getTime())) {
            dto1.getConditions().add(Restrict.eq("year", dto.getTime()));
//            dto1.getConditions().add(Restrict.eq("month", dto.getTime().substring(dto.getTime().indexOf("-") + 1, dto.getTime().length())));
        }
        return dto1;
    }
}