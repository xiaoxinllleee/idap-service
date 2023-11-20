package org.cmms.modules.tjfx.birthdayreminder.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.modules.tjfx.birthdayreminder.dto.srtxDto;
import org.cmms.modules.tjfx.birthdayreminder.entity.myBirthdayCustomerEntity;
import org.cmms.modules.tjfx.birthdayreminder.entity.wdsrkhFileEntity;
import org.cmms.modules.tjfx.birthdayreminder.mapper.srtxFileMapper;
import org.cmms.modules.tjfx.birthdayreminder.mapper.srtxmyBirthdayCustomerMapper;
import org.cmms.modules.tjfx.birthdayreminder.service.srtxmyBirthdayCustomerService;
import org.cmms.modules.tjfx.birthdayreminder.vo.historySrtxVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class srtxmyBirthdayCustomerServiceImpl extends ServiceImpl<srtxmyBirthdayCustomerMapper, myBirthdayCustomerEntity> implements srtxmyBirthdayCustomerService {

    @Autowired
    private srtxmyBirthdayCustomerMapper srtxmyBirthdayCustomerMapper;
    @Autowired
    private srtxFileMapper srtxFileMapper;


    /**
     * 判断是否信贷部或者管理员
     *
     * @param UserName
     * @return
     */
    private boolean judageIfBelongAdminAndCreditDepart(String UserName) {
        String userId = srtxmyBirthdayCustomerMapper.selectUserIdByUserName(UserName);
        List<String> roleID = srtxmyBirthdayCustomerMapper.selectRoleId(userId);
        if (roleID.size()>0) {
            List<String> RoleCode = srtxmyBirthdayCustomerMapper.selectRoleCode(roleID);
            if (RoleCode.contains("admin")) {
                return true;
            }
        }
        List<String> DepartCode = srtxmyBirthdayCustomerMapper.selectCreditDepartCode(UserName);
        if (DepartCode.size()>0&&DepartCode.contains("30")) {
            return true;
        }
        return false;
    }

    public String DateCastString(String date) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(date.substring(0, 2));
        stringBuffer.append(date.substring(3, 5));
        return stringBuffer.toString();
    }

    ;

    @Override
    public IPage<myBirthdayCustomerEntity> selectmyBirthdayCustomerEntityByListPage(srtxDto srtxDt, Integer pageNo, Integer pageSize, HttpServletRequest request) {

        myBirthdayCustomerEntity myBirthdayCustomerEntity = new myBirthdayCustomerEntity();
        BeanUtils.copyProperties(srtxDt, myBirthdayCustomerEntity);
        String sszh = null;
        if (StringUtils.isNotBlank(srtxDt.getSszh())) {
            sszh = srtxDt.getSszh();
        }
        /*  srtxDt.setSszh(null);*/
        QueryWrapper<myBirthdayCustomerEntity> queryWrapper = QueryGenerator.initQueryWrapper(myBirthdayCustomerEntity, request.getParameterMap());
        String token = request.getHeader("X-Access-Token");
        String UserName = JwtUtil.getUsername(token);

        /**
         * 判断用户是否是管理员或者属于信贷部门
         */
        if (!judageIfBelongAdminAndCreditDepart(UserName)) {
            queryWrapper.eq("operate_people", UserName);
        }
        if (StringUtils.isNotBlank(sszh)) {
            String begin = "select zzbz from hr_bas_organization start with zzbz=";
            String behind = "connect by prior zzbz = sjzzbz";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(begin);
            stringBuffer.append("'");
            stringBuffer.append(sszh);
            stringBuffer.append("'");
            stringBuffer.append(behind);
            //"select zzbz from hr_bas_organization start with zzbz="+sszh+" connect by prior zzbz = sjzzbz"
            queryWrapper.inSql("sszh", stringBuffer.toString());
        }
        Page<myBirthdayCustomerEntity> page = new Page<myBirthdayCustomerEntity>(pageNo, pageSize);
        Date daysBegin = srtxDt.getDaysBegin();
        Date daysEnd = srtxDt.getDaysEnd();
        IPage<myBirthdayCustomerEntity> pageList=null;
        if (daysBegin != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String daysEndSrrq = simpleDateFormat.format(daysEnd).substring(5, 10);
            String daysbegginSrrq = simpleDateFormat.format(daysBegin).substring(5, 10);
            daysbegginSrrq = DateCastString(daysbegginSrrq);
            daysEndSrrq = DateCastString(daysEndSrrq);
            if (daysEndSrrq != null && daysbegginSrrq != null) {
                queryWrapper.between("csrqstr", daysbegginSrrq, daysEndSrrq);
                pageList = this.page(page, queryWrapper);
            }
        } else {
            pageList = this.page(page, queryWrapper);
        }
        return pageList;
    }


    @Override
    public historySrtxVo getUploadPictureImfomationDetailById(String id, HttpServletRequest req) {

        historySrtxVo historySrtxDto = new historySrtxVo();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(id)) {
            queryWrapper.eq("id", id);

            myBirthdayCustomerEntity myBirthdayCustomerEntity = this.baseMapper.selectOne(queryWrapper);
            if (myBirthdayCustomerEntity != null) {
                BeanUtils.copyProperties(myBirthdayCustomerEntity, historySrtxDto);
                String QsId = myBirthdayCustomerEntity.getQsId();
                if (QsId != null) {
                    QueryWrapper queryWrapperTwo = new QueryWrapper();
                    queryWrapperTwo.eq("SRTX_ID", QsId);
                    List<wdsrkhFileEntity> wdsrkhFileEntityList = srtxFileMapper.selectList(queryWrapperTwo);
                    historySrtxDto.setImgList(wdsrkhFileEntityList);
                }
            }

        }


        return historySrtxDto;
    }
}
