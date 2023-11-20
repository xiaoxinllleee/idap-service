package org.cmms.modules.khdj.khdjsz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khdj.khdjsz.entity.Khdjsz;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
public interface khdjszMapper extends BaseMapper<Khdjsz> {

    Khdjsz queryByDjbh(Map<String, String> sql);

}
