package org.cmms.modules.tjfx.birthdayreminder.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.util.JwtUtil;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.tjfx.birthdayreminder.dto.myBirthdayCustomersDto;
import org.cmms.modules.tjfx.birthdayreminder.dto.srtxDto;
import org.cmms.modules.tjfx.birthdayreminder.dto.statusDto;
import org.cmms.modules.tjfx.birthdayreminder.entity.myBirthdayCustomerEntity;
import org.cmms.modules.tjfx.birthdayreminder.entity.srtx;
import org.cmms.modules.tjfx.birthdayreminder.entity.wdsrkhFileEntity;
import org.cmms.modules.tjfx.birthdayreminder.mapper.srtxFileMapper;
import org.cmms.modules.tjfx.birthdayreminder.mapper.srtxMapper;
import org.cmms.modules.tjfx.birthdayreminder.mapper.srtxmyBirthdayCustomerMapper;
import org.cmms.modules.tjfx.birthdayreminder.service.IsrtxService;
import org.cmms.modules.tjfx.birthdayreminder.service.srtxmyBirthdayCustomerService;
import org.cmms.modules.tjfx.birthdayreminder.vo.historySrtxVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Description: sd
 * @Author: jeecg-boot
 * @Date: 2022-07-14
 * @Version: V1.0
 */
@Service
public class srtxServiceImpl extends ServiceImpl<srtxMapper, srtx> implements IsrtxService {

    @Autowired
    private srtxFileMapper srtxFileMapper;

    @Autowired
    private srtxmyBirthdayCustomerService birthdayCustomerService;
    @Autowired
    private srtxmyBirthdayCustomerMapper birthdayCustomerMapper;
    @Autowired
    private  srtxMapper srtxMapper;


    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;


    /**
     * 查询是否锁定
     *
     * @param srt
     * @param username
     * @return
     */
    private boolean judgeIfStatusAlreadyChange(srtx srt, String username) {
        if (srt != null) {
            String operate = srt.getOperate();
            if ("1".equals(operate)) {
                if (srt.getOperatePeople() != null && !srt.getOperatePeople().equals(username)) {

                    return false;
                }
            }

        }


        return true;
    }

    @Override
    @Transactional
    public Boolean changeOperateStatus(statusDto statusDto, HttpServletRequest request) {

        if (StringUtils.isNotBlank(statusDto.getOperate()) && StringUtils.isNotBlank(statusDto.getId())) {
            String token = request.getHeader("X-Access-Token");
            String UserName = JwtUtil.getUsername(token);
            srtx srt = this.baseMapper.selectOne(new QueryWrapper<srtx>().eq("id", statusDto.getId()));
            boolean isAlreadyChange = judgeIfStatusAlreadyChange(srt, UserName);
            if (isAlreadyChange) {
                srt.setOperate(statusDto.getOperate());
                srt.setOperatePeople(UserName);
                UpdateWrapper wrapper = new UpdateWrapper<>().eq("id", srt.getId());
                wrapper.set("operate", srt.getOperate());
                wrapper.set("operate_people", UserName);
                this.baseMapper.update(null, wrapper);
                String qs_id = null;
                if (statusDto.getOperate().equals("0")) {
                    //改为未锁定删除历史记录删除文件
                    LambdaQueryWrapper<myBirthdayCustomerEntity> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(myBirthdayCustomerEntity::getId, statusDto.getId());
                    myBirthdayCustomerEntity myBirthdayCustomerEntity = birthdayCustomerMapper.selectOne(queryWrapper);
                    if (myBirthdayCustomerEntity != null) {
                        qs_id = myBirthdayCustomerEntity.getQsId();
                        birthdayCustomerMapper.deleteById(qs_id);
                        LambdaQueryWrapper<wdsrkhFileEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                        lambdaQueryWrapper.eq(wdsrkhFileEntity::getSrtxId, qs_id);
                        srtxFileMapper.delete(lambdaQueryWrapper);
                    }
                } else if (statusDto.getOperate().equals("1")) {
                    //已锁定插入历史记录
                    /*  birthdayCustomerMapper.deleteById(new QueryWrapper<>().eq("id",statusDto.getId()));*/
                    myBirthdayCustomerEntity myBirthdayCustomerEntity = new myBirthdayCustomerEntity();
                    BeanUtils.copyProperties(srt, myBirthdayCustomerEntity);
                    myBirthdayCustomerEntity.setOperatePeople(UserName);
                    myBirthdayCustomerEntity.setQsId(statusDto.getId());
                    myBirthdayCustomerEntity.setCreateTime(new Date());
                    birthdayCustomerMapper.insert(myBirthdayCustomerEntity);
                }
                return true;
            }
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
    public IPage<srtx> queryByPageList(HttpServletRequest req, srtxDto srtxDto, Integer pageNo, Integer pageSize) {
        srtx s = new srtx();
        req.getParameterMap();
        BeanUtils.copyProperties(srtxDto, s);
        IPage<srtx> pageList = null;
        QueryWrapper<srtx> queryWrapper = QueryGenerator.initQueryWrapper(s, req.getParameterMap());

        String token = req.getHeader("X-Access-Token");
        String UserName = JwtUtil.getUsername(token);
        /*if(!UserName.equals("amdin")){
            queryWrapper.eq("operate_people",UserName);
        }*/
        queryWrapper.and((wrapper) -> {
            wrapper.ne("ckye", 0).or().ne("dkye", "0");
         /*   return wrapper;*/
        });
        Page<srtx> page = new Page<srtx>(pageNo, pageSize);
        if (StringUtils.isNotBlank(s.getSszh())) {
            String begin = "select zzbz from hr_bas_organization start with zzbz=";
            String behind = "connect by prior zzbz = sjzzbz";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(begin);
            stringBuffer.append("'");
            stringBuffer.append(s.getSszh());
            stringBuffer.append("'");
            stringBuffer.append(behind);
            queryWrapper.inSql("sszh", stringBuffer.toString());
        }
        pageList = this.page(page, queryWrapper);
        Date daysBegin = srtxDto.getDaysBegin();
        Date daysEnd = srtxDto.getDaysEnd();
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
    public void initDataBySheduler() {

        srtxMapper.initDataBySheduler();


    }

    /**
     * 返回已锁定的用户的数
     *
     * @return
     */
    @Override
    public JSONObject selectImportCostumerNumber() {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        System.out.println(sysUser.getOrgCode());
//        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByYwjgdm(sysUser.getOrgCode());
        /*        List<String> yejgdmList = hrBasOrganizationService.selectDepartIdListByUserID(sysUser.getId());*/
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sszh",sysUser.getOrgCode());
        queryWrapper.eq("operate", "1");
        queryWrapper.select("id");
        Long number = this.baseMapper.selectCount(queryWrapper);

        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("operate", "1");
        queryWrapper1.eq("operate_people",sysUser.getUsername());
        queryWrapper1.select("id");
        Long number1 = this.baseMapper.selectCount(queryWrapper1);
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("zhtxs",number);
        jsonObject.put("grtxs",number1);

        return jsonObject;
    }


    /**
     * ipad图片上传
     */
    @Override
    @Transactional
    public boolean insertIntoDataAndUploadImage(myBirthdayCustomersDto birthdayCustomersDto, HttpServletRequest req) {
        myBirthdayCustomerEntity customerEntity = birthdayCustomersDto.getBirthdayCustomers();
        String bz = customerEntity.getBz();
        if (bz == null) {
            bz = "";
        }
        String Qs_id = customerEntity.getQsId();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (Qs_id != null) {
            LambdaQueryWrapper<wdsrkhFileEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(wdsrkhFileEntity::getSrtxId, Qs_id);
            LambdaUpdateWrapper<myBirthdayCustomerEntity> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(myBirthdayCustomerEntity::getQsId, Qs_id);
            updateWrapper.set(myBirthdayCustomerEntity::getBz, bz);
            birthdayCustomerMapper.update(null, updateWrapper);
            srtxFileMapper.delete(queryWrapper);
            if (birthdayCustomersDto.getWdsrkhFileList().size() > 0) {
                birthdayCustomersDto.getWdsrkhFileList().stream().forEach((dsrkhFileEntity -> {

                    wdsrkhFileEntity wdsrkhFile = new wdsrkhFileEntity();
                    BeanUtils.copyProperties(dsrkhFileEntity, wdsrkhFile);
                    wdsrkhFile.setCreateTime(new Date());
                    wdsrkhFile.setUpdateTime(new Date());
                    wdsrkhFile.setCreateBy(sysUser.getUsername());
                    wdsrkhFile.setSrtxId(customerEntity.getQsId());
                    srtxFileMapper.insert(wdsrkhFile);

                }));
            }
        }

        return true;
    }


    /**
     * 获取上传图片的生日客户所有信息
     *
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @Override
    public IPage<historySrtxVo> getUploadPictureImfomation(srtxDto srtxDt, Integer pageNo, Integer pageSize, HttpServletRequest req) {


        IPage<historySrtxVo> pageList = new Page<>();
        List<historySrtxVo> historySrtxDtoList = new ArrayList<>();
        IPage<myBirthdayCustomerEntity> iPage = birthdayCustomerService.selectmyBirthdayCustomerEntityByListPage(srtxDt, pageNo, pageSize, req);
        List<myBirthdayCustomerEntity> birthdayCustomerEntityList = iPage.getRecords();
        birthdayCustomerEntityList.stream().forEach((birthdayCustomer) -> {
            historySrtxVo historySrtxVo = new historySrtxVo();
            BeanUtils.copyProperties(birthdayCustomer, historySrtxVo);
            if (historySrtxVo.getSex().equals("1")) {
                historySrtxVo.setSex("男");
            } else {
                historySrtxVo.setSex("女");
            }
            if (historySrtxVo.getGzrwSfdb().equals("1")) {
                historySrtxVo.setGzrwSfdb("是");
            } else {
                historySrtxVo.setGzrwSfdb("否");
            }
            LambdaQueryWrapper<wdsrkhFileEntity> wrapper = new LambdaQueryWrapper<>();
            if (birthdayCustomer.getQsId() != null) {
                wrapper.eq(wdsrkhFileEntity::getSrtxId, birthdayCustomer.getQsId());
            }

            List<wdsrkhFileEntity> wdsrkhFileEntityList = srtxFileMapper.selectList(wrapper);
            historySrtxVo.setImgList(wdsrkhFileEntityList);
            historySrtxDtoList.add(historySrtxVo);
        });

        pageList.setRecords(historySrtxDtoList);
        pageList.setTotal(iPage.getTotal());
        pageList.setSize(iPage.getSize());
        pageList.setCurrent(iPage.getCurrent());
        pageList.setPages(iPage.getPages());
        return pageList;

    }


}
