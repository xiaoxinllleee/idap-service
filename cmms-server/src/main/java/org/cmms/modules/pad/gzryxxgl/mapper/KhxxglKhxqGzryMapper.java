package org.cmms.modules.pad.gzryxxgl.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.gzryxxgl.entity.KhxxglKhxqGzry;

/**
 * @Description: 公职人员信息管理
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
public interface KhxxglKhxqGzryMapper extends BaseMapper<KhxxglKhxqGzry> {

    public void init(String gzryid, String yggh, String lrr);
}
