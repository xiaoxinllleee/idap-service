package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.mapper;

import org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity.KhjldkxxtjJb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理贷款信息统计季报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
public interface KhjldkxxtjJbMapper extends BaseMapper<KhjldkxxtjJb> {

    void InitData(String tjyf);

}
