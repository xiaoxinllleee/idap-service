package org.cmms.modules.dklldj.lldjgl.glzhgl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Component
public interface CbsInvmBaseMapper extends BaseMapper<CbsInvmBase> {
    //获取定活期余额
    String dhqye(String zjhm);

    //获取存入交易额
    Map crxx(@Param("zjhm") String zjhm);

    //获取支出信息
    Map zcxx(@Param("zjhm") String zjhm);

    //获取存款账户数
    int ckzhs(@Param("zjhm") String zjhm);

    //获取 口袋零钱记录数 网银记录数 手机银行记录数 etc记录数 农信银记录数
    Map getIntermediaryBusiness(@Param("zjhm") String zjhm);

    //获取表外贷款
    String bwdk(@Param("zjhm") String zjhm);

    //获取授信额度
    String credit(@Param("zjhm") String zjhm);

    //是否信贷
    int sfxd(@Param("zjhm") String zjhm);

    //最小开户时间 存款日平
    Map khsjAndCkrp(@Param("zjhm") String zjhm);

    // 获取主账号（客户账户明细综合查询）
    String getAccNoByMastAcctAndSubAcctNo(String paramOne, String paramTwo);
}
