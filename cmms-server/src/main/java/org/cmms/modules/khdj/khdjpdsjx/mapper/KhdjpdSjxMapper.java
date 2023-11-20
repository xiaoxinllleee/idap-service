package org.cmms.modules.khdj.khdjpdsjx.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khdj.khdjpdsjx.entity.KhdjpdSjx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户等级评定数据项
 * @Author: cmms
 * @Date:   2019-10-16
 * @Version: V1.0
 */
public interface KhdjpdSjxMapper extends BaseMapper<KhdjpdSjx> {

    KhdjpdSjx queryBySjxid(Map<String,String> sql);

}
