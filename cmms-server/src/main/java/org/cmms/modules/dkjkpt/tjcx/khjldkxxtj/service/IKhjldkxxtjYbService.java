package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjYb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理贷款信息统计月报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
public interface IKhjldkxxtjYbService extends IService<KhjldkxxtjYb> {

    void InitData(String tjyf);

}
