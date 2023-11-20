package org.cmms.modules.pad.shxxgl.mapper;

import org.cmms.modules.pad.shxxgl.entity.KhglShhmcxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.HashMap;

/**
 * @Description: 商户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface KhglShhmcxxMapper extends BaseMapper<KhglShhmcxx> {
    public HashMap<String, Object> getYwxxByZjhm(String zjhm);

    public void init(String shid, String yggh, String lrr);
}
