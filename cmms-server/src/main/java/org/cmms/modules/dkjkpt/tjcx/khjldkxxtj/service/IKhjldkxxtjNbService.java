package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjNb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户经理贷款信息统计年报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
public interface IKhjldkxxtjNbService extends IService<KhjldkxxtjNb> {

    void InitData(String tjyf);

}
