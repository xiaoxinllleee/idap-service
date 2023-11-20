package org.cmms.modules.workplace.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.workplace.entity.WorkPlaceGrdkSjmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 个人贷款工作台明细
 * @Author: Penghr
 * @Date:   2020-08-30
 * @Version: V1.0
 */
public interface WorkPlaceGrdkSjmxMapper extends BaseMapper<WorkPlaceGrdkSjmx> {

    /**
     * 根据（客户经理）员工工号获取个人贷款总客户数
     * @param yggh
     * @return
     */
    Integer queryGrdkZKhsByKhjl(String yggh);

    /**
     * 根据组织标识获取个人贷款总客户数（支行行长）
     * @param zzbz
     * @return
     */
    Integer queryGrdkZKhsByZzbz(String zzbz);

    /**
     * 根据（客户经理）员工工号获取个人贷款本月新增客户数
     * @param yggh
     * @return
     */
    Integer queryGrdkByxzKhsByKhjl(String yggh);

    /**
     * 根据组织标识获取个人贷款本月新增客户数（支行行长）
     * @param zzbz
     * @return
     */
    Integer queryGrdkByxzKhsByZzbz(String zzbz);

    /**
     * 根据（客户经理）员工工号获取个人贷款授信金额
     * @param yggh
     * @return
     */
    Double queryGrdkSxjeByKhjl(String yggh);

    /**
     * 根据组织标识获取个人贷款授信金额（支行行长）
     * @param zzbz
     * @return
     */
    Double queryGrdkSxjeByZzbz(String zzbz);

    /**
     * 根据（客户经理）员工工号获取个人贷款用信金额
     * @param yggh
     * @return
     */
    Double queryGrdkYxjeByKhjl(String yggh);

    /**
     * 根据组织标识获取个人贷款用信金额（支行行长）
     * @param zzbz
     * @return
     */
    Double queryGrdkYxjeByZzbz(String zzbz);

    /*客户经理所属数据*/
    Integer getGrdkCjkhsForKhjl(String yggh);
    Integer getGrdkSxkhsForKhjl(String yggh);
    Integer getGrdkYxkhsForKhjl(String yggh);
    Integer getGrdkWsxkhsForKhjl(String yggh);
    Double getGrdkSxjeForKhjl(String yggh);
    Double getGrdkYxjeForKhjl(String yggh);
    /*支行行长所属数据*/
    Integer getGrdkCjkhsForZhhz(String zzbz);
    Integer getGrdkSxkhsForZhhz(String zzbz);
    Integer getGrdkYxkhsForZhhz(String zzbz);
    Integer getGrdkWsxkhsForZhhz(String zzbz);
    Double getGrdkSxjeForZhhz(String zzbz);
    Double getGrdkYxjeForZhhz(String zzbz);

    // 个人贷款客户等级工作台饼状图数据
    /* 客户经理 */
    List<Map> queryGrdkKhPddjForKhjl(@Param("yggh") String yggh);
    /* 支行行长 */
    List<Map> queryGrdkKhPddjForZhhz(@Param("zzbz") String zzbz);
}
