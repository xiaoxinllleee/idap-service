package org.cmms.modules.khgl.cqjm.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.modules.khgl.cqjm.entity.*;
import org.cmms.modules.khgl.cqjm.mapper.*;
import org.cmms.modules.khgl.cqjm.service.ICqjmJbxxService;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 城区居民功能包
 * @Author: jeecg-boot
 * @Date:   2020-02-20
 * @Version: V1.0
 */
@Service
public class CqjmJbxxServiceImpl extends ServiceImpl<CqjmJbxxMapper, CqjmJbxx> implements ICqjmJbxxService {

    @Autowired
    private CqjmJbxxMapper jbxxMapper;
    @Autowired
    private CqjmZcfzqkMapper zcfzqkMapper;
    @Autowired
    private CqjmYwhywwlxxMapper ywhywwlxxMapper;
    @Autowired
    private CqjmZcxxMapper zcxxMapper;
    @Autowired
    private CqjmFjxxMapper fjxxMapper;
    @Autowired
    private ISysDictService iSysDictService;

    /**
     * 城区居民 基本信息删除
     * @param id
     * @param dabh
     * @param zjhm
     */
    @Override
    public void deleteByIdAndDabhAndZjhm(String id, String dabh, String zjhm) {
        jbxxMapper.deleteByIdAndDabhAndZjhm(id,dabh,zjhm);
    }

    /**
     * 城区居民-一对多添加保存
     * @param jbxx
     * @param zcxxList
     * @param ywhxgywList
     * @param fjxxList
     */
    @Override
    public void saveMain(CqjmJbxx jbxx, List<CqjmZcxx> zcxxList, List<CqjmYwhywwlxx> ywhxgywList, List<CqjmZcfzqk> zcfzqkList, List<CqjmFjxx> fjxxList) {
        String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
        jbxxMapper.insert(jbxx);
        // 城区居民房产信息
        for (CqjmZcxx zcxx:zcxxList) {
            zcxx.setQydm(qydm);
            zcxx.setZjhm(jbxx.getZjhm());
            zcxxMapper.insert(zcxx);
        }
        /*for (CqjmYwhxgyw ywhxgyw:ywhxgywList) {
            ywhxgyw.setZjhm(jbxx.getZjhm());
            ywhywwlxxMapper.insert(ywhxgyw);
        }*/
        for (CqjmFjxx fjxx:fjxxList) {
            fjxx.setQydm(qydm);
            fjxx.setZjhm(jbxx.getZjhm());
            fjxxMapper.insert(fjxx);
        }
    }

    /**
     * 城区居民-一对多修改保存
     * @param jbxx
     * @param zcxxList
     * @param ywhxgywList
     * @param fjxxList
     */
    @Override
    public void updateMain(CqjmJbxx jbxx, List<CqjmZcxx> zcxxList, List<CqjmYwhywwlxx> ywhxgywList, List<CqjmZcfzqk> zcfzqkList, List<CqjmFjxx> fjxxList) {
        String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
        UpdateWrapper<CqjmJbxx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("zjhm", jbxx.getZjhm());
        jbxxMapper.update(jbxx, updateWrapper);

        // 1-城区居民-房产信息-删除
        zcxxMapper.deleteZcxxByZjhm(jbxx.getZjhm());
        // 2-重新插入-房产信息
        for (CqjmZcxx zcxx:zcxxList) {
            zcxx.setQydm(qydm);
            zcxx.setZjhm(jbxx.getZjhm());
            zcxxMapper.insert(zcxx);
        }
        // 与我行相关业务
        /*for (CqjmYwhxgyw ywhxgyw:ywhxgywList) {
            ywhxgyw.setZjhm(jbxx.getZjhm());
            ywhywwlxxMapper.insert(ywhxgyw);
        }*/
        // 城区居民-附件信息-插入
        for (CqjmFjxx fjxx:fjxxList) {
            fjxx.setQydm(qydm);
            fjxx.setZjhm(jbxx.getZjhm());
            fjxxMapper.insert(fjxx);
        }
    }

    /**
     * 城区居民-一对多删除
     * @param zjhm
     */
    @Override
    public void deleteMain(String zjhm) {
        jbxxMapper.deleteByZjhm(zjhm);
        zcfzqkMapper.deleteZcfzqkByZjhm(zjhm);
        ywhywwlxxMapper.deleteYwwlxxByZjhm(zjhm);
        zcxxMapper.deleteZcxxByZjhm(zjhm);
        //fjxxMapper.deleteByZjhm(zjhm);
    }

    /**
     * 城区居民-一对多批量删除
     * @param idList
     */
    @Override
    public void deleteBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable serializable:idList) {
            jbxxMapper.deleteById(serializable);
            zcfzqkMapper.deleteZcfzqkByZjhm(serializable.toString());
            ywhywwlxxMapper.deleteYwwlxxByZjhm(serializable.toString());
            zcxxMapper.deleteZcxxByZjhm(serializable.toString());
            //fjxxMapper.deleteByZjhm(serializable.toString());
        }
    }
}
