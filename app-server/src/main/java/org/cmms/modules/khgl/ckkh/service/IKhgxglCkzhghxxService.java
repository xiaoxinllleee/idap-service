package org.cmms.modules.khgl.ckkh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.ckkh.entity.KhgxglCkzhghxx;
import org.cmms.modules.khgl.ckkh.entity.ZhlbVO;

import java.util.List;

/**
 * @Description: 存款账号管户信息
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
public interface IKhgxglCkzhghxxService extends IService<KhgxglCkzhghxx> {
    public List<ZhlbVO> getCkzhList(String tzr);
}
