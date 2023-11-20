package org.cmms.modules.khgl.csjl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.csjl.entity.AppEtcCsjlVO;
import org.cmms.modules.khgl.csjl.entity.AppEtckhCsjl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.csjl.entity.AppEtckhCsjlVO;

import java.util.List;

/**
 * @Description: etc客户催收记录
 * @Author: jeecg-boot
 * @Date:   2022-03-19
 * @Version: V1.0
 */
@DS("eweb")
public interface IAppEtckhCsjlService extends IService<AppEtckhCsjl> {
    List<AppEtckhCsjlVO> getCsxxList(int start, int end,String namecn);

    IPage<AppEtcCsjlVO> getCsjlList(Page page,String zjhm);
}
