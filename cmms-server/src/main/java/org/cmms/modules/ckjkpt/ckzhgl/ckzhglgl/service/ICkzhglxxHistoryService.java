package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity.CkzhglxxHistory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存款账号关联管理(历史备份数据)
 * @Author: jeecg-boot
 * @Date:   2021-11-01
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkzhglxxHistoryService extends IService<CkzhglxxHistory> {

}
