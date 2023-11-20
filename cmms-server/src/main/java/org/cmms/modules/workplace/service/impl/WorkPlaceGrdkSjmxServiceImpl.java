package org.cmms.modules.workplace.service.impl;

import org.cmms.modules.workplace.entity.WorkPlaceGrdkSjmx;
import org.cmms.modules.workplace.mapper.WorkPlaceGrdkSjmxMapper;
import org.cmms.modules.workplace.service.IWorkPlaceGrdkSjmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 个人贷款工作台明细
 * @Author: Penghr
 * @Date:   2020-08-30
 * @Version: V1.0
 */
@Service
public class WorkPlaceGrdkSjmxServiceImpl extends ServiceImpl<WorkPlaceGrdkSjmxMapper, WorkPlaceGrdkSjmx> implements IWorkPlaceGrdkSjmxService {

    @Autowired
    private WorkPlaceGrdkSjmxMapper mapper;

    @Override
    public Integer queryGrdkZKhsByKhjl(String yggh) {
        return mapper.queryGrdkZKhsByKhjl(yggh);
    }

    @Override
    public Integer queryGrdkZKhsByZzbz(String zzbz) {
        return mapper.queryGrdkZKhsByZzbz(zzbz);
    }

    @Override
    public Integer queryGrdkByxzKhsByKhjl(String yggh) {
        return mapper.queryGrdkByxzKhsByKhjl(yggh);
    }

    @Override
    public Integer queryGrdkByxzKhsByZzbz(String zzbz) {
        return mapper.queryGrdkByxzKhsByZzbz(zzbz);
    }

    @Override
    public Double queryGrdkSxjeByKhjl(String yggh) {
        return mapper.queryGrdkSxjeByKhjl(yggh);
    }

    @Override
    public Double queryGrdkSxjeByZzbz(String zzbz) {
        return mapper.queryGrdkSxjeByZzbz(zzbz);
    }

    @Override
    public Double queryGrdkYxjeByKhjl(String yggh) {
        return mapper.queryGrdkYxjeByKhjl(yggh);
    }

    @Override
    public Double queryGrdkYxjeByZzbz(String zzbz) {
        return mapper.queryGrdkYxjeByZzbz(zzbz);
    }

    @Override
    public Integer getGrdkCjkhsForKhjl(String yggh) {
        return mapper.getGrdkCjkhsForKhjl(yggh);
    }

    @Override
    public Integer getGrdkSxkhsForKhjl(String yggh) {
        return mapper.getGrdkSxkhsForKhjl(yggh);
    }

    @Override
    public Integer getGrdkYxkhsForKhjl(String yggh) {
        return mapper.getGrdkYxkhsForKhjl(yggh);
    }

    @Override
    public Integer getGrdkWsxkhsForKhjl(String yggh) {
        return mapper.getGrdkWsxkhsForKhjl(yggh);
    }

    @Override
    public Double getGrdkSxjeForKhjl(String yggh) {
        return mapper.getGrdkSxjeForKhjl(yggh);
    }

    @Override
    public Double getGrdkYxjeForKhjl(String yggh) {
        return mapper.getGrdkYxjeForKhjl(yggh);
    }

    @Override
    public Integer getGrdkCjkhsForZhhz(String zzbz) {
        return mapper.getGrdkCjkhsForZhhz(zzbz);
    }

    @Override
    public Integer getGrdkSxkhsForZhhz(String zzbz) {
        return mapper.getGrdkSxkhsForZhhz(zzbz);
    }

    @Override
    public Integer getGrdkYxkhsForZhhz(String zzbz) {
        return mapper.getGrdkYxkhsForZhhz(zzbz);
    }

    @Override
    public Integer getGrdkWsxkhsForZhhz(String zzbz) {
        return mapper.getGrdkWsxkhsForZhhz(zzbz);
    }

    @Override
    public Double getGrdkSxjeForZhhz(String zzbz) {
        return mapper.getGrdkSxjeForZhhz(zzbz);
    }

    @Override
    public Double getGrdkYxjeForZhhz(String zzbz) {
        return mapper.getGrdkYxjeForZhhz(zzbz);
    }

    @Override
    public List<Map> queryGrdkKhPddjForKhjl(String yggh) {
        return mapper.queryGrdkKhPddjForKhjl(yggh);
    }

    @Override
    public List<Map> queryGrdkKhPddjForZhhz(String zzbz) {
        return mapper.queryGrdkKhPddjForZhhz(zzbz);
    }
}
