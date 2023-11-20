package org.cmms.modules.khgl.nh.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.entity.NhbkbpyYsVO;
import org.cmms.modules.khgl.nh.entity.XtBmd;
import org.cmms.modules.khgl.nh.vo.KpyhsVO;
import org.cmms.modules.khgl.nh.vo.NhbkbpyNyVo;

/**
 * @Description: 农户背靠背评议
 * @Author: cmms
 * @Date:   2019-11-30
 * @Version: V1.0
 */
public interface NhbkbpyMapper extends BaseMapper<Nhbkbpy> {

    public List<Nhbkbpy> viewDetail(@Param("ZJHM") String zjhm);

    public Nhbkbpy PymxData(@Param("ZJHM") String zjhm, @Param("PYYZJHM") String pyyzjhm);

    public boolean deleteByMainId(String zjhm);

    public List<Nhbkbpy> selectByMainId(@Param("ZJHM") String zjhm);

    public List<Nhbkbpy> randomList(@Param("dao") NhbkbpyYsVO nhbkbpyYsVO);

    public List<KpyhsVO> getYpyrs();

    public BigDecimal minJcmxcs(String hhbm);

    public String getAllBkbpybz(String hhbm);

    public List<XtBmd> getBmdListOne(@Param("wgbh") String wgbh,@Param("yggh") String yggh);
    public List<XtBmd> getBmdListOneUp(@Param("wgbh") String wgbh,@Param("yggh") String yggh,@Param("pyls")int pyls);
    public int getMaxPyls(@Param("wgbh") String wgbh,@Param("yggh") String yggh);
    public String getpyyxm(@Param("wgbh") String wgbh,@Param("pyls") int pyls);
    public String getYsxdx(@Param("zjhm") String zjhm);
    public BigDecimal getMaxJysxed(String zjhm);

    public List<NhbkbpyNyVo> queryPyxxNy(String wgbh,Integer pyls);
    public Integer getPylsByWgbh(String wgbh);
}
