package org.cmms.modules.khgl.ckkh.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.ckkh.entity.*;
import org.cmms.modules.khgl.dkkh.entity.DkxtjcVO;

/**
 * @Description: 员工管户数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-04
 * @Version: V1.0
 */
public interface TbCkYgghcksjmxMapper extends BaseMapper<TbCkYgghcksjmx> {

    CkkhjbVO getByYggh(@Param("yggh")String yggh);
    CkkhGyVO getByZjhm(@Param("zjhm")String zjhm);

    List<String> getZjhms(@Param("rownumStart")int rownumStart,@Param("rownumEnd")int rownumEnd,@Param("yggh")String yggh,
                          @Param("grp")String grp,@Param("custName")String custName);

    Integer getGhzs(@Param("yggh")String yggh);
    Integer getGhzsByGrp(@Param("yggh")String yggh,@Param("grp")String grp,@Param("custName")String custName);

    TbCkYgghcksjmx minEndDate(@Param("zjhm")String zjhm);

    List<String> getAcctGrpByZjhm(@Param("zjhm")String zjhm,@Param("yggh")String yggh);

    List<CkkhRankVO> getRankList(@Param("rownumStart")int rownumStart,@Param("rownumEnd")int rownumEnd,@Param("yggh")String yggh,
                                 @Param("pxType")String pxType);


    IPage<TbCkYgghcksjmxVo> getJsr(Page page, @Param("ghr")String ghr, @Param("qmyeS") String qmyeS, @Param("qmyeE") String qmyeE,
                                    @Param("qmTable")String qmTable,@Param("zrTable")String zrTable);

    IPage<TbCkYgghcksjmxVo> getJyc(Page page, @Param("ghr")String ghr, @Param("qmyeS") String qmyeS, @Param("qmyeE") String qmyeE,
                                    @Param("qmTable")String qmTable);

    IPage<TbCkYgghcksjmxVo> getJjc(Page page, @Param("ghr")String ghr, @Param("qmyeS") String qmyeS, @Param("qmyeE") String qmyeE,
                                    @Param("qmTable")String qmTable,@Param("jcTable")String jcTable);

    IPage<TbCkYgghcksjmxVo> getJnc(Page page, @Param("ghr")String ghr, @Param("qmyeS") String qmyeS, @Param("qmyeE") String qmyeE,
                                    @Param("qmTable")String qmTable);
    String getCkCpxx(String zjhm);

    List<QhckphVO> getCkRank();

    IPage<CkkhCardVO> getClckkh(Page page,@Param("tzr")String tzr,
                                @Param("grp")String grp,@Param("custName")String custName);
}
