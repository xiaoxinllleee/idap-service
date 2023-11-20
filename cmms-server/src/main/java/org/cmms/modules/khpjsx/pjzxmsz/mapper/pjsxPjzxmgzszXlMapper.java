package org.cmms.modules.khpjsx.pjzxmsz.mapper;

import java.util.List;

import io.lettuce.core.dynamic.annotation.Param;
import org.cmms.modules.khpjsx.pjzxmsz.entity.pjsxPjzxmgzszXl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 与我行往来情况
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface pjsxPjzxmgzszXlMapper extends BaseMapper<pjsxPjzxmgzszXl> {

	public boolean deleteByMainId(@Param("mainId")String mainId, @Param("khlx")String khlx);

	public List<pjsxPjzxmgzszXl> selectByMainId(@Param("mainId")String mainId,@Param("khlx")String khlx);
}
