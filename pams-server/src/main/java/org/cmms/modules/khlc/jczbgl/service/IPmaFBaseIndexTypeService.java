package org.cmms.modules.khlc.jczbgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khlc.jczbgl.entity.PmaFBaseIndexType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 基础指标类型表
 * @Author: jeecg-boot
 * @Date:   2021-01-18
 * @Version: V1.0
 */

public interface IPmaFBaseIndexTypeService extends IService<PmaFBaseIndexType> {
        List<PmaFBaseIndexType> listTree(String dirType);
        int add(PmaFBaseIndexType pmaFBaseIndexType);
        /**
         * 获取当前目录下所有指标的父id
         * */
        List<Integer> listChildTree(int value);
}
