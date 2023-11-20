package org.cmms.modules.khgl.grkhgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-07-06
 * @Version: V1.0
 */
@Component
public interface KhhmcxxMapper extends BaseMapper<Khhmcxx> {

    public Khhmcxx selectByMainId(String zjhm);

    public List<Khhmcxx>selectByhhbm(String hhbm,String zjhm);
    public List<Khhmcxx>selectByhhbmId(String hhbm,String id);
    public List<Khhmcxx>jointbDebtor(@Param("hhbm")String hhbm);
    public List<Khhmcxx>getListByListHhbm(@Param("hhbms")List<String> hhbms);
    public boolean deleteByMainId(@Param("zjhm")String zjhm);

    /**
     * 新增时输入的证件号码若已在小额农贷授信采集中录入，则从客户花名册中带回部分基础信息数据
     * @param zjhm
     * @return
     */
    List<Khhmcxx> GetKhhmcPartialInfoByZjhm(@Param("zjhm") String zjhm);

    /**
     * 新增时，若该客户已在小额农贷授信中采集，则通过"HHBM"&"ID"获取"KHGL_KHHMCXX"家庭成员信息
     * @param hhbm
     * @param id
     * @return
     */
    List<Khhmcxx> getJtcyxxByHmcHhbmAndId(@Param("hhbm") String hhbm,@Param("id") String id);

    public List<Khhmcxx> selectAllByZjhm(String zjhm);
    public List<Khhmcxx> selectAllButNoSelfByZjhm(String zjhm);
}
