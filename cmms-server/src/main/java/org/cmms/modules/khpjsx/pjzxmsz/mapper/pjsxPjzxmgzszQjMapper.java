package org.cmms.modules.khpjsx.pjzxmsz.mapper;

import java.util.List;

import io.lettuce.core.dynamic.annotation.Param;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszQj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 金融业务信息
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface pjsxPjzxmgzszQjMapper extends BaseMapper<pjsxPjzxmgzszQj> {

	public boolean deleteByMainId(@Param("mainId")String mainId, @Param("khlx")String khlx);

	public List<pjsxPjzxmgzszQj> selectByMainId(@Param("mainId")String mainId,@Param("khlx")String khlx);
}
