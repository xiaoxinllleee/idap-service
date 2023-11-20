package org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.entity.YgghdkxxtjYb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 员工管户贷款信息统计月报
 * @Author: jeecg-boot
 * @Date:   2022-09-27
 * @Version: V1.0
 */
public interface IYgghdkxxtjYbService extends IService<YgghdkxxtjYb> {

    void InitData(String tjyf);

}
