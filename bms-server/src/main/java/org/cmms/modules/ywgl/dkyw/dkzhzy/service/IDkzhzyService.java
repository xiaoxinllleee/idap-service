package org.cmms.modules.ywgl.dkyw.dkzhzy.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.Dkzhzy;
import org.cmms.modules.ywgl.dkyw.dkzhzy.entity.DzyzhsVO;

/**
 * @Description: 贷款账号转移
 * @Author: jeecg-boot
 * @Date:   2021-09-27
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IDkzhzyService extends IService<Dkzhzy> {
    void pDkzhzy1(String org,String custManagerId);
    void pDkzhzy2(String org,String acctNo);
    void pDkzhzy3(String org,String custManagerId,String acctNo);


    void dkzhzy1(String oldjgdm,String oldcustid,String newjgdm,String newcustid,String newyggh,String newgwbz,String newgyh,String  dkzh,String czy );
    void dkzhzy2(String oldjgdm,String oldcustid,String newjgdm,String newcustid,String newyggh,String newgwbz,String newgyh,String czy );


    public DzyzhsVO getDzysByKhjlOrDkzh(String jgdm,String khjlbz,String dkzh);

    String getGlid();

}
