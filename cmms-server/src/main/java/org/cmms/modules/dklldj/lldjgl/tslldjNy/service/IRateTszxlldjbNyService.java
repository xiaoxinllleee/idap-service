package org.cmms.modules.dklldj.lldjgl.tslldjNy.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.dto.RateKhAndTsZxLlNyDto;
import org.cmms.modules.dklldj.lldjgl.tslldjNy.entity.RateTszxlldjb;

/**
 * @Description: dwdw
 * @Author: jeecg-boot
 * @Date:   2022-09-14
 * @Version: V1.0
 */
@DS("eweb")
public interface IRateTszxlldjbNyService extends IService<RateTszxlldjb> {

    boolean selectSpStatusById(String id);

    RateTszxlldjb selectRateRszxlldjbNyByDjId(Long djid);

    void AddRateTsNyLl(RateTszxlldjb rateTszxlldjb);

    JSONObject getComputeResultById(RateTszxlldjb rateTszxlldjb);

    void updateRateTsNyLl(RateTszxlldjb rateTszxlldjb);
}
