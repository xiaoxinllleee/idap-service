package org.cmms.modules.pad.nhxxgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.pad.nhxxgl.entity.DkxxVo;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.pad.nhxxgl.entity.vKhglNhhzxxgl;
import org.cmms.modules.pad.pyxx.entity.Nhbkbpyfsxx;
import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;

import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-15
 * @Version: V1.0
 */
public interface IKhglNhhzxxglService extends IService<KhglNhhzxxgl> {
    public void updateMain(KhglNhhzxxgl khglNhhzxxgl, List<KhglNhhzzllb> khglNhhzzllbs);

    public List<Nhbkbpyfsxx> selectpyxx(String hhbm);

    public int syncYesNhhzxx();

    public void updateKhlx(String hhbm,String newhhbm);

    public List<DkxxVo> getJtcyDkxxByZjhm(String zjhm);
}
