package org.cmms.modules.performance.depositcustomer.ckzhtzyj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.depositcustomer.ckzhtzyj.entity.Ckzhtzyjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存款账号拓展移交信息
 * @Author: jeecg-boot
 * @Date:   2023-03-31
 * @Version: V1.0
 */
public interface CkzhtzyjxxMapper extends BaseMapper<Ckzhtzyjxx> {
    String getProcessIdByProcessKey(@Param("processkey") String processKey);

    List<Ckzhtzyjxx> getListByIds(@Param("ids") List<String> ids);

}
