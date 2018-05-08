package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.EntryOptionBO;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.bo.UserNameSexBO;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.service.EntryRegisterSer;
import com.bjike.goddess.staffentry.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 入职登记业务接口
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 13:48]
 * @Description: [入职登记业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("entryRegisterApiImpl")
public class EntryRegisterApiImpl implements EntryRegisterAPI {

    @Autowired
    private EntryRegisterSer entryRegisterSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return entryRegisterSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return entryRegisterSer.guidePermission( guidePermissionTO );
    }


    @Override
    public Long countEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        return entryRegisterSer.countEntryRegister(entryRegisterDTO);
    }

    @Override
    public EntryRegisterBO getOne(String id) throws SerException {
        return entryRegisterSer.getOne(id);
    }

    @Override
    public List<EntryRegisterBO> listEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        return entryRegisterSer.listEntryRegister(entryRegisterDTO);
    }

    @Override
    public EntryRegisterBO insertEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                               WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {
        return entryRegisterSer.insertEntryRegister(entryRegisterTO,familyMemberTO,studyExperienceTO,workExperienceTO,credentialTO);
    }

    @Override
    public EntryRegisterBO editEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                             WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {
        return entryRegisterSer.editEntryRegister(entryRegisterTO,familyMemberTO,studyExperienceTO,workExperienceTO,credentialTO);
    }

    @Override
    public void removeEntryRegister(String id) throws SerException {
        entryRegisterSer.removeEntryRegister(id);
    }

    @Override
    public EntryRegisterBO getEntryRegisterDetail(String id) throws SerException {
        return entryRegisterSer.getEntryRegisterDetail(id);
    }

    @Override
    public EntryRegisterBO getByNumber(String number) throws SerException {
        return entryRegisterSer.getByNumber(number);
    }

    @Override
    public List<String> findQQ() throws SerException {
        return entryRegisterSer.findQQ();
    }

    @Override
    public List<String> findSchoolTag() throws SerException {
        return entryRegisterSer.findSchoolTag();
    }

    @Override
    public List<String> findGraduationDate() throws SerException {
        return entryRegisterSer.findGraduationDate();
    }
    public List<EntryRegisterBO> list() throws SerException {
        return entryRegisterSer.list();
    }

    @Override
    public String findEmpNum(String name) throws SerException {
        return entryRegisterSer.findEmpNum(name);
    }

    @Override
    public EntryOptionBO getEntryOptionByEmpNum(String empNumer) throws SerException {
        return entryRegisterSer.getEntryOptionByEmpNum(empNumer);
    }

    @Override
    public String getGender(String username) throws SerException {
        return entryRegisterSer.getGender(username);
    }

    @Override
    public List<EntryRegisterBO> map(EntryRegisterDTO dto) throws SerException {
        return entryRegisterSer.map(dto);
    }

    @Override
    public List<EntryRegisterBO> getEntryRegisterByName(String name) throws SerException {
        return entryRegisterSer.getEntryRegisterByName(name);
    }

    @Override
    public String getEntryTime(String username) throws SerException {
        return entryRegisterSer.getEntryTime(username);
    }
    public List<String> findWorkingEmpNum() throws SerException {
        return entryRegisterSer.findWorkingEmpNum();
    }

    @Override
    public List<EntryOptionBO> findEmpDate() throws SerException {
        return entryRegisterSer.findEmpDate();
    }

    @Override
    public Integer findNumByEntryDate(String[] date, String area, String dep) throws SerException {
        return entryRegisterSer.findNumByEntryDate(date,area,dep);
    }

    @Override
    public Integer findNumByEntryDate(String endDate, String area, String dep) throws SerException {
        return entryRegisterSer.findNumByEntryDate(endDate,area,dep);
    }
    @Override
    public Set<String> names() throws SerException {
        return entryRegisterSer.names();

    }
    @Override
    public Integer findNumByEntryDate(String dep) throws SerException {
        return entryRegisterSer.findNumByEntryDate(dep);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return entryRegisterSer.templateExport();
    }

    @Override
    public List<UserNameSexBO> findSexByUserName(String[] userName) throws SerException {
        return entryRegisterSer.findSexByUserName(userName);
    }

    @Override
    public List<EntryRegisterBO> myTeam(EntryRegisterDTO dto) throws SerException {
        return entryRegisterSer.myTeam (dto);
    }
}
