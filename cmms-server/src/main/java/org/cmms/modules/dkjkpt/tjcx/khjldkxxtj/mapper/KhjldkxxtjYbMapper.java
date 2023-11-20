package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.mapper;

import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjYb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理贷款信息统计月报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
public interface KhjldkxxtjYbMapper extends BaseMapper<KhjldkxxtjYb> {

    void InitData(String tjyf);

}
