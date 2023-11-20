package org.cmms.modules.ywgl.dkyw.dkzhzy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.Dkzhzy;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.DzyzhsVO;

/**
 * @Description: 贷款账号转移
 * @Author: jeecg-boot
 * @Date:   2021-09-27
 * @Version: V1.0
 */
public interface DkzhzyMapper extends BaseMapper<Dkzhzy> {
    void pDkzhzy1(@Param("org")String org,@Param("custManagerId")String custManagerId);
    void pDkzhzy2(@Param("org")String org,@Param("acctNo")String acctNo);
    void pDkzhzy3(@Param("org")String org,@Param("custManagerId")String custManagerId,@Param("acctNo")String acctNo);

    void dkzhzy1(@Param("oldjgdm") String oldjgdm,@Param("oldcustid")String oldcustid,
                @Param("newjgdm") String newjgdm,@Param("newcustid") String newcustid,
                @Param("newyggh") String newyggh,@Param("newgwbz") String newgwbz,
                @Param("newgyh") String newgyh, @Param("dkzh") String dkzh,
                @Param("czy") String czy );

    void dkzhzy2(@Param("oldjgdm") String oldjgdm,@Param("oldcustid")String oldcustid,
                 @Param("newjgdm") String newjgdm,@Param("newcustid") String newcustid,
                 @Param("newyggh") String newyggh,@Param("newgwbz") String newgwbz,
                 @Param("newgyh") String newgyh, @Param("czy") String czy );

    public  DzyzhsVO getDzysByKhjlOrDkzh(@Param("jgdm")String jgdm,@Param("khjlbz")String khjlbz, @Param("dkzh")String dkzh);

    String getGlid();
}
