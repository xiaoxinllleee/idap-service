package org.cmms.modules.pad.nhxxgl.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.nhxxgl.entity.DkxxVo;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.pad.nhxxgl.entity.vKhglNhhzxxgl;
import org.cmms.modules.pad.pyxx.entity.Nhbkbpyfsxx;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-15
 * @Version: V1.0
 */
public interface KhglNhhzxxglMapper extends BaseMapper<KhglNhhzxxgl> {

 public List<Nhbkbpyfsxx> selectpyxx(String hhbm);

 int syncYesNhhzxx();

 public void updateKhlx(String hhbm,String newhhbm);

 public List<DkxxVo> getJtcyDkxxByZjhm(String zjhm);
}
