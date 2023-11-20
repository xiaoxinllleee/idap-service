package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjJb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理贷款信息统计季报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
public interface IKhjldkxxtjJbService extends IService<KhjldkxxtjJb> {


    void InitData(String tjyf);
}
