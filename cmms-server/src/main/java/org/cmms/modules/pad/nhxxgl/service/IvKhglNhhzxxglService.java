package org.cmms.modules.pad.nhxxgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.pad.nhxxgl.entity.vKhglNhhzxxgl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.nhxxgl.entity.vKhglNhhzxxglQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-29
 * @Version: V1.0
 */
public interface IvKhglNhhzxxglService extends IService<vKhglNhhzxxgl> {

    List<String> selectHzByCy(String lxfs,String yssxz,String yxzc,String zz, vKhglNhhzxxglQuery hzxx, HttpServletRequest req);

    /**
     * 每次编辑数据时，提取走访数据
     * @param hhbm
     * @param zjhm
     * @param yggh
     * @param username
     * @param zfrq
     */
    public void init(String hhbm, String zjhm, String yggh, String username, String zfrq);

    /**
     * 大数据版本 提取khxxgl_grsxlxmx_nh表的数据
     * @param hhbm
     */
    public void init1(String hhbm);

    /**
     * 大数据版本 提取khxxgl_sxlxmx_nh表的数据
     * @param hhbm
     */
    public void init2(String hhbm);
    public Integer init3(String hhbm);

    List<String> getLrryList();

    List<String> getPfrList();
}
