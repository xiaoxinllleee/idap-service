package org.cmms.modules.tjfx.ipadsysj.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.tjfx.ipadsysj.entity.SysjQhzhCjpm;
import org.cmms.modules.tjfx.ipadsysj.entity.Zhyxzfpm;
import org.cmms.modules.tjfx.ipadsysj.entity.vTjfxSysj;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.zfsjmx.khjlsjmx.entity.ZfsjmxKhjl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 平板端首页数据
 * @Author: jeecg-boot
 * @Date:   2020-07-23
 * @Version: V1.0
 */
public interface IvTjfxSysjService extends IService<vTjfxSysj> {


    //public List<Map<String,Object>> querysysj ( );

    public Map<String,Object> querysysj ( );

    public List<ZfsjmxKhjl> queryZhCjpm(String sszh);
    public List<ZfsjmxKhjl> queryQhCjpm();
    public Long grbyzfhs(String khjl);
    public Long zhbyzfhs(String zzbz);
    public Long qhbyzfhs();
    public Long grljzfhs(String khjl);
    public Long zhljzfhs(String zzbz);
    public Long qhljzfhs();
    public Long grdyysxed(String khjl);
    public Long zhdyysxed(String zzbz);
    public Long qhdyysxed();
    public Long grljysxed(String khjl);
    public Long zhljysxed(String zzbz);
    public Long qhljysxed();
    public String queryGyh(String username);

    public void initKhxx();
    public void initKhywxx();
    public void initShxx();
    public void initShywxx();

    public Long shbyzfhs(String khjl);
    public Long shzhbyzfhs(String zzbz);
    public Long shqhbyzfhs();

    public Long shljzfhs(String khjl);
    public Long shzhljzfhs(String zzbz);
    public Long shqhljzfhs();

    public Long shbyysxed(String khjl);
    public Long shzhbyysxed(String zzbz);
    public Long shqhbyysxed();

    public Long shljysxed(String khjl);
    public Long shzhljysxed(String zzbz);
    public Long shqhljysxed();

    public List<SysjQhzhCjpm> queryQhzhCjpm();

    public HrBasOrganization queryZzxxByYggh(String yggh);

    public List<SysjQhzhCjpm> getYxzfpm(String khlx,String tjwd);

    public List<SysjQhzhCjpm> getYxzfpmByYggh(String khlx,String tjwd,String yggh);

    public List<Zhyxzfpm> getZhYxzfpm(String tjwd);

    public Map<String,Object> getYgYxzfsj(String yggh, String khlx);
}
