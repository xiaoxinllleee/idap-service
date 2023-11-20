package org.cmms.modules.ywgl.djkyw.djkkhzr.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.djkyw.djkkhzr.entity.Djkkhzr;

/**
 * @Description: 贷记卡考核责任
 * @Author: jeecg-boot
 * @Date:   2021-12-06
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IDjkkhzrService extends IService<Djkkhzr> {

    void CallPkgUpdateDkzrr(@Param("tjrq") String tjrq);

}
