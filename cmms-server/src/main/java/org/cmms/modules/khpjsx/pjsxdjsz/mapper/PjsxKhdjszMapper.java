package org.cmms.modules.khpjsx.pjsxdjsz.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khpjsx.pjsxdjsz.entity.PjsxKhdjsz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评级授信等级设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface PjsxKhdjszMapper extends BaseMapper<PjsxKhdjsz> {

    public PjsxKhdjsz queryDjbh(Map<String,String> sql);

}
