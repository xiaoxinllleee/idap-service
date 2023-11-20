package org.cmms.modules.dklldj.lldjgl.glzhgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@DS("sjxf")
public interface ICbsInvmBaseService extends IService<CbsInvmBase> {
    // 获取主账号（客户账户明细综合查询）
    String getAccNoByMastAcctAndSubAcctNo(String paramOne, String paramTwo);
}
