package org.cmms.modules.pad.qyxxgl.mapper;

import org.cmms.modules.pad.qyxxgl.entity.VKhglQyxxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 企业信息管理_pad
 * @Author: jeecg-boot
 * @Date:   2022-11-04
 * @Version: V1.0
 */
public interface KhglQyxxglMapper extends BaseMapper<VKhglQyxxgl> {

    public void init(String qyid, String yggh, String lrr);
}
