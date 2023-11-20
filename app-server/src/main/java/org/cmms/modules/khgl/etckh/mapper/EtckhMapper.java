package org.cmms.modules.khgl.etckh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.etckh.entity.BdxxbVO;
import org.cmms.modules.khgl.etckh.entity.Etckh;
import org.cmms.modules.khgl.etckh.entity.SbxxVO;

/**
 * @Description: ETC绑定信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
public interface EtckhMapper extends BaseMapper<Etckh> {

    IPage<BdxxbVO> getKhxxList(Page page, @Param("username") String username, @Param("khmc") String khmc);

    IPage<SbxxVO> getSbxxList(Page page, @Param("zjhm") String zjhm);
}
