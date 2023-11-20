package org.cmms.modules.khgl.sh.mapper;

import java.util.List;

import org.cmms.modules.khgl.sh.entity.ShglYwhywwlxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-10-24
 * @Version: V1.0
 */
public interface ShglYwhywwlxxMapper extends BaseMapper<ShglYwhywwlxx> {
    public List<ShglYwhywwlxx> selectByMainId (String zjhm);
}
