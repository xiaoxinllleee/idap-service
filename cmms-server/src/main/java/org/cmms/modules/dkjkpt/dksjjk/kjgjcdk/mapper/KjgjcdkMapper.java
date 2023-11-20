package org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dksjjk.kjgjcdk.entity.Kjgjcdk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 跨机构交叉贷款
 * @Author: jeecg-boot
 * @Date:   2020-04-01
 * @Version: V1.0
 */
public interface KjgjcdkMapper extends BaseMapper<Kjgjcdk> {

    void InitData();

}
