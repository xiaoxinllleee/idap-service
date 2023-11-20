package org.cmms.modules.ywgl.yydj.sjyh.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.yydj.sjyh.entity.Yydjsjyh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.yydj.sjyh.entity.YyjlVO;

import java.util.List;

/**
 * @Description: 手机银行
 * @Author: jeecg-boot
 * @Date:   2022-03-05
 * @Version: V1.0
 */
public interface YydjsjyhMapper extends BaseMapper<Yydjsjyh> {

//    List<YyjlVO> getList(@Param("start") int start,@Param("end") int end,@Param("sbzt") int sbzt);
    List<YyjlVO> getList(Page page , @Param("sbzt") int sbzt, @Param("yggh") String username);
}
