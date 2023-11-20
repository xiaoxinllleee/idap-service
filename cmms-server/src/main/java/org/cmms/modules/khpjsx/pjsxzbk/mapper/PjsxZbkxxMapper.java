package org.cmms.modules.khpjsx.pjsxzbk.mapper;

import java.util.Map;

import org.cmms.modules.khpjsx.pjsxdjsz.entity.PjsxKhdjsz;
import org.cmms.modules.khpjsx.pjsxzbk.entity.PjsxZbkxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评级授信指标库
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface PjsxZbkxxMapper extends BaseMapper<PjsxZbkxx> {

    public PjsxZbkxx queryZbid(Map<String,String> sql);

}
