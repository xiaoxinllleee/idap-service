package org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.entity.Dkkhglrgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款客户关联人管理
 * @Author: jeecg-boot
 * @Date:   2021-08-10
 * @Version: V1.0
 */
public interface DkkhglrglMapper extends BaseMapper<Dkkhglrgl> {

    void pDkkhglrgl();

    /**
     * 根据`被调查人证件号码`.`借款人证件号码`获取`被关联人信息`
     * @param zjhm 被调查人证件号码
     * @return
     */
    List<Dkkhglrgl> QueryRelatedPartyInfoOne(String zjhm);

    /**
     * 根据`被调查人证件号码`.`关联人证件号码`获取`被关联人信息`
     * @param zjhm 被调查人证件号码
     * @return
     */
    List<Dkkhglrgl> QueryRelatedPartyInfoTwo(String zjhm);

    List<Dkkhglrgl> queryRelatedPersonInfo(String zjhm);

}
