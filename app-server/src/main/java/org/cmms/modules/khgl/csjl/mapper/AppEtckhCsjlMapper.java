package org.cmms.modules.khgl.csjl.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.csjl.entity.AppEtcCsjlVO;
import org.cmms.modules.khgl.csjl.entity.AppEtckhCsjl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.csjl.entity.AppEtckhCsjlVO;

/**
 * @Description: etc客户催收记录
 * @Author: jeecg-boot
 * @Date:   2022-03-19
 * @Version: V1.0
 */
public interface AppEtckhCsjlMapper extends BaseMapper<AppEtckhCsjl> {
    List<AppEtckhCsjlVO> getCsxxList(@Param("start")int start,@Param("end")int end,@Param("namecn")String namecn);

    IPage<AppEtcCsjlVO> getCsjlList(Page page,@Param("zjhm")String zjhm);
}
