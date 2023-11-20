package org.cmms.modules.dklldj.lldjgl.lldjsq.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.constant.RateConstant;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.mapper.Rate_khjbxxbMapper;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.service.IRateZxllcxService;
import org.cmms.modules.dklldj.lldjgl.lldjsq.entity.Lldjsq;
import org.cmms.modules.dklldj.lldjgl.lldjsq.entity.RateKhjbxxbVO;
import org.cmms.modules.dklldj.lldjgl.lldjsq.mapper.LldjsqMapper;
import org.cmms.modules.dklldj.lldjgl.lldjsq.service.ILldjsqService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.common.util.DateUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.xdgl.grdkgl.entity.RateLldjZhckrp;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbgzxxb;
import org.cmms.modules.xdgl.grdkgl.entity.RateZxlldjb;
import org.cmms.modules.xdgl.grdkgl.mapper.RateLldjZhckrpMapper;
import org.cmms.modules.xdgl.grdkgl.mapper.RateZbgzxxbMapper;
import org.cmms.modules.xdgl.grdkgl.service.IFwxxService;
import org.cmms.modules.xdgl.grdkgl.service.IRateZxlldjbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
@Slf4j
@Service
public class LldjsqServiceImpl extends ServiceImpl<LldjsqMapper, Lldjsq> implements ILldjsqService {

}
