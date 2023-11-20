package org.cmms.modules.rwzx.rwcj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.constant.CacheConstant;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import org.cmms.modules.rwzx.rwcj.entity.*;
import org.cmms.modules.rwzx.rwcj.mapper.TaskBfrwBaseMapper;
import org.cmms.modules.rwzx.rwcj.service.ITaskBfrwBaseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 基础拜访任务
 * @Author: jeecg-boot
 * @Date:   2023-04-01
 * @Version: V1.0
 */
@Service
public class TaskBfrwBaseServiceImpl extends ServiceImpl<TaskBfrwBaseMapper, TaskBfrwBase> implements ITaskBfrwBaseService {

    @Override
    public int selectCountByYxid(String yxid,String status) {
        return baseMapper.selectCountByYxid(yxid,status);
    }

    @Override
    public int selectCountByYxid(String yxid) {
        return baseMapper.selectCountByYxid(yxid,"1");
    }

    @Override
    public Page<WdrwVO> getPageWdrw(Page page,String yggh) {
        return baseMapper.getPageWdrw(page,yggh);
    }

    @Override
    public Page<WdrwVO> getPageRwtj(Page page, String yggh,String wgbh) {
        return baseMapper.getPageRwtj(page, yggh,wgbh);
    }

    @Override
    public List<ZfpxVO> getZpfxVOList(String yxid) {
        return baseMapper.getZpfxVOList(yxid);
    }

    @Override
    public int dkYYMM(String yymm) {
        yymm = "ZMCBSBORMBASE"+yymm;
        try {
            return baseMapper.dkYYMM(yymm);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public BigDecimal dkYYMM(String yymm, String wgbh, String yggh) {
        yymm = "ZMCBSBORMBASE"+yymm;
        try {
            return baseMapper.dkYYMM2(yymm, wgbh, yggh);
        }catch (Exception e){
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal ckYYMM(String yymm, String wgbh, String yggh) {
        yymm = "ZMCBSINVMBASE"+yymm;
        try {
            BigDecimal ckByJgdm = baseMapper.ckYYMM2(yymm, wgbh, yggh);
            return ckByJgdm;
        }catch (Exception e){
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public int ckYYMM(String yymm) {
        yymm = "ZMCBSINVMBASE"+yymm;
        try {
            Integer ckByJgdm = baseMapper.ckYYMM(yymm);
            return ckByJgdm;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int ckYYMM(String yymm, String type) {
        yymm = "ZMCBSINVMBASE"+yymm;
        if (StringUtils.isBlank(type))
            type = "S";
        try {
            Integer ckByJgdm = baseMapper.ckYYMMType(yymm, type);
            return ckByJgdm;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer dqckdqs() {
        return baseMapper.dqckdqs();
    }

    @Override
    public Integer dqdkrs() {
        return baseMapper.dqdkrs();
    }

    @Override
    public Page<CbsInvmBase> getCkdq(Page page) {
        return baseMapper.getCkdq(page);
    }

    @Override
    public Page<CbsBormBase> getDkdq(Page page) {
        return baseMapper.getDkdq(page);
    }

    @Override
    public BigDecimal ckZhhj() {
        return baseMapper.ckZhhj();
    }

    @Override
    public BigDecimal dkZhhj() {
        return baseMapper.dkZhhj();
    }

    @Override
    public List<CbsInvmBase> getCKZHList() {
        return baseMapper.getCKZHList();
    }

    @Override
    public List<CbsBormBase> getDKZHList() {
        return baseMapper.getDKZHList();
    }

    @Override
    public Page<WdrwSearchResultVO> getWdrwSearchResultVOList(Page page,WdrwSearchVO wdrwSearchVO) {
        return baseMapper.getWdrwSearchResultVOList(page, wdrwSearchVO);
    }
}
