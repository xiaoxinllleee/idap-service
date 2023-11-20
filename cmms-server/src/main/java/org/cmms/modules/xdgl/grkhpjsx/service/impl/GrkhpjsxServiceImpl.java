package org.cmms.modules.xdgl.grkhpjsx.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.constant.CacheConstant;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.mapper.SysDictMapper;
import org.cmms.modules.system.service.ISysDictItemService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.xdgl.grkhpjsx.entity.Grkhpjsx;
import org.cmms.modules.xdgl.grkhpjsx.mapper.GrkhpjsxMapper;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrkhpjsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 个人客户评级授信
 * @Author: jeecg-boot
 * @Date:   2020-07-10
 * @Version: V1.0
 */
@Service
public class GrkhpjsxServiceImpl extends ServiceImpl<GrkhpjsxMapper, Grkhpjsx> implements IGrkhpjsxService {

    @Autowired
    ISysDictService sysDictService;
    @Autowired
    ISysUserService sysUserService;

    @Override
    public String querySplcProcessId(String key) {
        return baseMapper.querySplcProcessId(key);
    }

    @Override
    public void updateGrpjsxxx(String spid ,String zzpddj, BigDecimal zzsxed, String yj, String hhbm) {
        baseMapper.updateGrpjsxxx(spid,zzpddj,zzsxed,yj,hhbm);
    }

    @Override
    public void updateGrpjsxxxZjhm(String spid, String zzpddj, BigDecimal zzsxed, String yj, String zjhm) {
        baseMapper.updateGrpjsxxxByZjhm(spid, zzpddj, zzsxed, yj, zjhm);
    }

    @Override
    public void updateGrpjsxxxZjhm2( String zzpddj, BigDecimal zzsxed, String yj, String zjhm, int status,String cpzl,BigDecimal cpzlll) {
        baseMapper.updateGrpjsxxxByZjhm2( zzpddj, zzsxed, yj, zjhm, status,cpzl,cpzlll);
    }

    @Override
    public void updateGrpjsxxxZjhmAndStatus(String zjhm, int status) {
        baseMapper.updateGrpjsxxxZjhmAndStatus(zjhm, status);
    }

    @Override
    public void updateGrpjsxed( String pddj,BigDecimal zzsxed, String yj, String hhbm) {
        baseMapper.updateGrpjsxed(pddj,zzsxed,yj,hhbm);
    }
    @Override
    public void updateGrpjsxjled( String pddj,BigDecimal zzsxed, String yj, String spid) {
        baseMapper.updateGrpjsxjled(pddj,zzsxed,yj,spid);
    }

    @Override
    public List<Grkhpjsx> getByListZjhm(List<String> zjhms) {
        return baseMapper.getByListZjhm(zjhms);
    }

    @Override
    public List<Grkhpjsx> jointbDebtor(String hhbm) {
        return baseMapper.jointbDebtor(hhbm) ;
    }

    @Override
    public String getfxjlBySskhjl(String sskhjl) {

        String s = sysDictService.queryTableDictTextByKey("SYS_USER", "ORG_CODE", "USERNAME", sskhjl);
        String s2 = sysDictService.queryTableDictTextByKey("SYS_ROLE", "ID", "ROLE_CODE", "fxjl");

        List<SysUser> userByRoleId = sysUserService.findUserByRoleId(s2, s);
        if (CollUtil.isNotEmpty(userByRoleId))
            return userByRoleId.get(0).getUsername();
        return null;
    }
}
