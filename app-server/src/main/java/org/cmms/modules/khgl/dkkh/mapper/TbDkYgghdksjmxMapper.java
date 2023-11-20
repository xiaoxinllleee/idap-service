package org.cmms.modules.khgl.dkkh.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.dkkh.entity.DkxtjcVO;
import org.cmms.modules.khgl.dkkh.entity.HtlbVO;
import org.cmms.modules.khgl.dkkh.entity.TbDkYgghdksjmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Description: 员工管户贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
public interface TbDkYgghdksjmxMapper extends BaseMapper<TbDkYgghdksjmx> {

    List<String> getZjhms(@Param("rownumStart")int rownumStart, @Param("rownumEnd")int rownumEnd, @Param("yggh") String yggh, @Param("custType") String custType, @Param("wjfl") String wjfl);
    IPage<String> getZjhms2(Page page, @Param("yggh") String yggh, @Param("custType") String custType, @Param("wjfl") String wjfl,@Param("zjhm") String zjhm);


    List<String> getCustTypeByZjhm(@Param("zjhm")String zjhm,@Param("yggh")String yggh);

    IPage<DkxtjcVO> dkxtjc(Page page, @Param("yggh") String yggh, @Param("custType") String custType, @Param("type") String type, @Param("zjhm")String zjhm,
                           @Param("qmTable")String qmTable, @Param("jcTable")String jcTable, @Param("zrTable")String zrTable);

    List<HtlbVO> getHtlbsByZjhm(String zjhm);
}
