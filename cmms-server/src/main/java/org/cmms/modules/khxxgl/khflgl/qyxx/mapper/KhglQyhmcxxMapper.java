package org.cmms.modules.khxxgl.khflgl.qyxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.qyxx.entity.KhglQyhmcxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 企业信息管理
 * @Author: jeecg-boot
 * @Date:   2022-11-04
 * @Version: V1.0
 */
public interface KhglQyhmcxxMapper extends BaseMapper<KhglQyhmcxx> {


    public void init(String shid, String yggh, String lrr);
}
