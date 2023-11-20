package org.cmms.modules.khgl.nh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.entity.NhbkbpyYsVO;
import org.cmms.modules.khgl.nh.entity.XtBmd;
import org.cmms.modules.khgl.nh.mapper.NhbkbpyMapper;
import org.cmms.modules.khgl.nh.vo.NhbkbpyNyVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 农户背靠背评议
 * @Author: cmms
 * @Date:   2019-11-30
 * @Version: V1.0
 */
public interface INhbkbpyService extends IService<Nhbkbpy> {

    public List<Nhbkbpy> viewDetail(String zjhm);

    public Nhbkbpy PymxData(String zjhm, String pyyzjhm);

    public List<Nhbkbpy> selectByMainId(String zjhm);

    /**
     * 批量评议验收
     * */
    public List<Nhbkbpy> randomList(NhbkbpyYsVO nhbkbpyYsVO);

    /**
     * 获取所有轮数评议客户
     * */
    public Map<String,Integer> getYpyrs();

    public BigDecimal minJcmxcs(String hhbm);
    public String getAllBkbpybz(String hhbm);

    List<XtBmd> getBmdList(String wgbh,String yggh,int pyls,String code);

    public int getMaxPyls(String wgbh,String yggh);
    public String getpyyxm(String wgbh,int pyls);
    public String getYsxdx(String zjhm);

    public BigDecimal getMaxJysxed(String zjhm);

    public List<NhbkbpyNyVo> queryPyxxNy(String wgbh,Integer pyls);
    public Integer getPylsByWgbh(String wgbh);
}
