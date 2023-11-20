package org.cmms.modules.ywgl.dkyw.dksjzrgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.Dksjzrgl;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.DksjzrglHistory;

import java.util.List;

/**
 * @Description: 贷款数据责任管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IDksjzrglServiceHistory extends IService<DksjzrglHistory> {

    List<DksjzrglHistory> deleteDkzh(String dkzh, String tableName);

    List<DksjzrglHistory> saveTjyf(List list, String tableName);
}
