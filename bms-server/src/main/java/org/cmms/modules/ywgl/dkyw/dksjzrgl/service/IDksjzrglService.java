package org.cmms.modules.ywgl.dkyw.dksjzrgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.Dksjzrgl;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description: 贷款数据责任管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IDksjzrglService extends IService<Dksjzrgl> {

    List<Dksjzrgl> deleteDkzh(String dkzh , String tableName);

    List<Dksjzrgl> saveTjyf(List list , String tableName);
}
