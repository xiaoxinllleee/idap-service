package org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.entity.YgghdkxxtjJb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 员工管户贷款信息统计季报
 * @Author: jeecg-boot
 * @Date:   2022-09-27
 * @Version: V1.0
 */
public interface IYgghdkxxtjJbService extends IService<YgghdkxxtjJb> {


    void InitData(String tjyf);
}
