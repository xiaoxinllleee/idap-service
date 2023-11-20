package org.cmms.modules.workplace.service.impl;

import org.cmms.modules.workplace.entity.WorkPlaceXendSjmx;
import org.cmms.modules.workplace.mapper.WorkPlaceXendSjmxMapper;
import org.cmms.modules.workplace.service.IWorkPlaceXendSjmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 小额农贷工作台明细
 * @Author: Penghr
 * @Date:   2020-08-30
 * @Version: V1.0
 */
@Service
public class WorkPlaceXendSjmxServiceImpl extends ServiceImpl<WorkPlaceXendSjmxMapper, WorkPlaceXendSjmx> implements IWorkPlaceXendSjmxService {

    @Autowired
    private WorkPlaceXendSjmxMapper mapper;

    @Override
    public Integer queryXendZKhsByKhjl(String yggh) {
        return mapper.queryXendZKhsByKhjl(yggh);
    }

    @Override
    public Integer queryXendZKhsByZzbz(String zzbz) {
        return mapper.queryXendZKhsByZzbz(zzbz);
    }

    @Override
    public Integer queryXendByxzKhsByKhjl(String yggh) {
        return mapper.queryXendByxzKhsByKhjl(yggh);
    }

    @Override
    public Integer queryXendByxzKhsByZzbz(String zzbz) {
        return mapper.queryXendByxzKhsByZzbz(zzbz);
    }

    @Override
    public Double queryXendSxjeByKhjl(String yggh) {
        return mapper.queryXendSxjeByKhjl(yggh);
    }

    @Override
    public Double queryXendSxjeByZzbz(String zzbz) {
        return mapper.queryXendSxjeByZzbz(zzbz);
    }

    @Override
    public Double queryXendYxjeByKhjl(String yggh) {
        return mapper.queryXendYxjeByKhjl(yggh);
    }

    @Override
    public Double queryXendYxjeByZzbz(String zzbz) {
        return mapper.queryXendYxjeByZzbz(zzbz);
    }

    @Override
    public Integer getXendCjkhsForKhjl(String yggh) {
        return mapper.getXendCjkhsForKhjl(yggh);
    }

    @Override
    public Integer getXendSxkhsForKhjl(String yggh) {
        return mapper.getXendSxkhsForKhjl(yggh);
    }

    @Override
    public Integer getXendYxkhsForKhjl(String yggh) {
        return mapper.getXendYxkhsForKhjl(yggh);
    }

    @Override
    public Integer getXendWsxKhsForKhjl(String yggh) {
        return mapper.getXendWsxKhsForKhjl(yggh);
    }

    @Override
    public Double getXendSxjeForKhjl(String yggh) {
        return mapper.getXendSxjeForKhjl(yggh);
    }

    @Override
    public Double getXendYxjeForKhjl(String yggh) {
        return mapper.getXendYxjeForKhjl(yggh);
    }

    @Override
    public Integer getXendCjkhsForZhhz(String zzbz) {
        return mapper.getXendCjkhsForZhhz(zzbz);
    }

    @Override
    public Integer getXendSxkhsForZhhz(String zzbz) {
        return mapper.getXendSxkhsForZhhz(zzbz);
    }

    @Override
    public Integer getXendYxkhsForZhhz(String zzbz) {
        return mapper.getXendYxkhsForZhhz(zzbz);
    }

    @Override
    public Integer getXendWsxKhsForZhhz(String zzbz) {
        return mapper.getXendWsxKhsForZhhz(zzbz);
    }

    @Override
    public Double getXendSxjeForZhhz(String zzbz) {
        return mapper.getXendSxjeForZhhz(zzbz);
    }

    @Override
    public Double getXendYxjeForZhhz(String zzbz) {
        return mapper.getXendYxjeForZhhz(zzbz);
    }

    @Override
    public List<Map> queryXendKhPddjForKhjl(String yggh) {
        return mapper.queryXendKhPddjForKhjl(yggh);
    }

    @Override
    public List<Map> queryXendKhPddjForZhhz(String zzbz) {
        return mapper.queryXendKhPddjForZhhz(zzbz);
    }

    @Override
    public void WorkPlaceAutoMission() {
        mapper.WorkPlaceAutoMission();
    }
}
