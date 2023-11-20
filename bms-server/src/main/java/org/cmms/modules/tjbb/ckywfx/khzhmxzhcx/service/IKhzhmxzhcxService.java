package org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.entity.Khzhmxzhcx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户账户明细综合查询
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IKhzhmxzhcxService extends IService<Khzhmxzhcx> {
    String getAccNoByMastAcctAndSubAcctNo(String paramOne, String paramTwo);
}
