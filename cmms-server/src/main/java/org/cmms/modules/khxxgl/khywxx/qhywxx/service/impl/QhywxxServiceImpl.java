package org.cmms.modules.khxxgl.khywxx.qhywxx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.Qhywxx;
import org.cmms.modules.khxxgl.khywxx.qhywxx.mapper.QhywxxMapper;
import org.cmms.modules.khxxgl.khywxx.qhywxx.service.IQhywxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 全行业务信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Service
public class QhywxxServiceImpl extends ServiceImpl<QhywxxMapper, Qhywxx> implements IQhywxxService {

    @Override
    public IPage getYwsjBySjrq(Page page, String rq, String wgbh,String type, String jqlx,String qydm) {
        return baseMapper.getYwsjBySjrq(page,rq,wgbh,type, jqlx,qydm);
    }

    @Override
    public IPage<Nhxq> getWktYwsjBySjrq(Page page, String rq, String wgbh,String type, String jqlx) {
        return baseMapper.getListWktYwsjBySjrq(page,rq,wgbh,type, jqlx);
    }

    @Override
    public List<Qhywxx> getListYwsjBySjrq(String rq, String wgbh, String type, String jqlx) {
        return baseMapper.getListYwsjBySjrq(rq, wgbh, type, jqlx);
    }

    @Override
    public List<Nhxq> getListWktYwsjBySjrq(String rq, String wgbh, String type, String jqlx) {
        return baseMapper.getListWktYwsjBySjrq(rq, wgbh, type, jqlx);
    }

    @Override
    public List<Nhxq> getSbkWktListByWgbh(String wgbh) {
        return baseMapper.getSbkWktListByWgbh(wgbh);
    }

    @Override
    public IPage<Nhxq> getSbkWktByWgbh(Page page, String wgbh) {
        return baseMapper.getSbkWktListByWgbh(page, wgbh);
    }
}
