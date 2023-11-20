package org.cmms.modules.khgl.ckkh.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.ckkh.entity.AppCkkhGzList;
import org.cmms.modules.khgl.ckkh.entity.CkkhCardVO;

/**
 * @Description: app存款客户关注列表
 * @Author: jeecg-boot
 * @Date:   2022-03-08
 * @Version: V1.0
 */
public interface AppCkkhGzListMapper extends BaseMapper<AppCkkhGzList> {

    Integer getGhzsByGrp(@Param("yggh")String yggh,@Param("grp")String grp,@Param("custName") String custName);

    List<String> getZjhms(@Param("rownumStart")int rownumStart,@Param("rownumEnd")int rownumEnd,@Param("yggh")String yggh,
                          @Param("grp")String grp);

    IPage<CkkhCardVO> getClckkh(Page page, @Param("tzr")String tzr,
                                @Param("grp")String grp, @Param("custName")String custName);
}
