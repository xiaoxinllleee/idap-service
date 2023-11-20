package org.cmms.modules.khgl.ckkh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.ckkh.entity.KhgxglCkzhghxx;
import org.cmms.modules.khgl.ckkh.entity.ZhlbVO;

/**
 * @Description: 存款账号管户信息
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
public interface KhgxglCkzhghxxMapper extends BaseMapper<KhgxglCkzhghxx> {

    public List<ZhlbVO> getCkzhList(String zjhm);
}
