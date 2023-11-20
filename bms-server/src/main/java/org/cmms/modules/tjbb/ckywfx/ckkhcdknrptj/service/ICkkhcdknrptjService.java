package org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.entity.Ckkhcdknrptj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存款客户存贷款年日平统计
 * @Author: jeecg-boot
 * @Date:   2021-08-19
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface ICkkhcdknrptjService extends IService<Ckkhcdknrptj> {
    void pCkkhcdknrptj(String tjyf);
}
