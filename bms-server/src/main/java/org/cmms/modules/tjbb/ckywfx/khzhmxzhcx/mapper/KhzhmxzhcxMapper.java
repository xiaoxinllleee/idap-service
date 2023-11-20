package org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.khzhmxzhcx.entity.Khzhmxzhcx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户账户明细综合查询
 * @Author: jeecg-boot
 * @Date:   2021-10-19
 * @Version: V1.0
 */
public interface KhzhmxzhcxMapper extends BaseMapper<Khzhmxzhcx> {
    String getAccNoByMastAcctAndSubAcctNo(String paramOne, String paramTwo);
}
