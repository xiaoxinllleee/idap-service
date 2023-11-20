package org.cmms.modules.khgl.nh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.nh.entity.KhglKhhmcxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface KhglKhhmcxxMapper extends BaseMapper<KhglKhhmcxx> {

    public  boolean deleteByMainId (String zjhm);

}
