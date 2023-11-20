package org.cmms.modules.workplace.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.workplace.entity.WorkPlaceXendSjmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 小额农贷工作台明细
 * @Author: Penghr
 * @Date:   2020-08-30
 * @Version: V1.0
 */
public interface IWorkPlaceXendSjmxService extends IService<WorkPlaceXendSjmx> {

    /**
     * 根据客户经理（员工工号）获取小额农贷总客户数
     * @param yggh
     * @return
     */
    Integer queryXendZKhsByKhjl(String yggh);

    /**
     * 根据组织标识获取小额农贷总客户数（支行行长）
     * @param zzbz
     * @return
     */
    Integer queryXendZKhsByZzbz(String zzbz);

    /**
     * 根据客户经理（员工工号）获取小额农贷本月新增客户数
     * @param yggh
     * @return
     */
    Integer queryXendByxzKhsByKhjl(String yggh);

    /**
     * 根据组织标识获取小额农贷本月新增客户数（支行行长）
     * @param zzbz
     * @return
     */
    Integer queryXendByxzKhsByZzbz(String zzbz);

    /**
     * 根据客户经理（员工工号）获取小额农贷授信金额
     * @param yggh
     * @return
     */
    Double queryXendSxjeByKhjl(String yggh);

    /**
     * 根据组织标识获取小额农贷授信金额（支行行长）
     * @param zzbz
     * @return
     */
    Double queryXendSxjeByZzbz(String zzbz);

    /**
     * 根据客户经理（员工工号）获取小额农贷用信金额
     * @param yggh
     * @return
     */
    Double queryXendYxjeByKhjl(String yggh);

    /**
     * 根据组织标识获取小额农贷用信金额（支行行长）
     * @param zzbz
     * @return
     */
    Double queryXendYxjeByZzbz(String zzbz);

    /* 客户经理所属数据 */
    Integer getXendCjkhsForKhjl(String yggh);
    Integer getXendSxkhsForKhjl(String yggh);
    Integer getXendYxkhsForKhjl(String yggh);
    Integer getXendWsxKhsForKhjl(String yggh);
    Double getXendSxjeForKhjl(String yggh);
    Double getXendYxjeForKhjl(String yggh);
    /*支行行长所属数据*/
    Integer getXendCjkhsForZhhz(String zzbz);
    Integer getXendSxkhsForZhhz(String zzbz);
    Integer getXendYxkhsForZhhz(String zzbz);
    Integer getXendWsxKhsForZhhz(String zzbz);
    Double getXendSxjeForZhhz(String zzbz);
    Double getXendYxjeForZhhz(String zzbz);

    // 小额农贷客户等级工作台饼状图数据-
    /* 客户经理 */
    List<Map> queryXendKhPddjForKhjl(@Param("yggh") String yggh);
    /* 支行行长 */
    List<Map> queryXendKhPddjForZhhz(@Param("zzbz") String zzbz);

    // 工作台定时任务
    void WorkPlaceAutoMission();
}
