package org.cmms.modules.qxfk.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.modules.qxfk.entity.QxfkPdfImg;
import org.cmms.modules.qxfk.mapper.QxfkPdfImgMapper;
import org.cmms.modules.qxfk.qx.dto.QxJsonInfo;
import org.cmms.modules.qxfk.qx.dto.QxJsonVO;
import org.cmms.modules.qxfk.qx.dto.QxPdf;
import org.cmms.modules.qxfk.qx.util.ApiQxUtils;
import org.cmms.modules.qxfk.qx.util.JsonUtil;
import org.cmms.modules.qxfk.service.IQxfkPdfImgService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 七星风控
 * @Author: jeecg-boot
 * @Date: 2022-07-28
 * @Version: V1.0
 */
@Service
@Slf4j
public class QxfkPdfImgServiceImpl extends ServiceImpl<QxfkPdfImgMapper, QxfkPdfImg> implements IQxfkPdfImgService {

    @Override
    public Result<?> QxJsonData(String data, String zjhm, String repCode
            , String qxfk, String yggh, String type
    ,String sjhm,String khmc) {
        log.info("===当前QxJsonData查询type{}===", type);
        try {
            //因为报告生成需要时间
            if ("1".equals(type)) {
                Thread.sleep(5000);
            }
            QxfkPdfImg result = new QxfkPdfImg();
            QxJsonVO json = ApiQxUtils.getJSON(data, qxfk);
            if (json != null) {
                String code = json.getCode();
                String msg = null;
                if (StringUtils.isNotBlank(json.getMessage()))
                    msg = json.getMessage();
                if ("2000".equals(code)) {
                    QxJsonInfo info = json.getInfo();
                    String riskLevel = info.getRiskLevel();
                    String queryTime = info.getQueryTime();
                    String riskCode = "4";
                    if (StringUtils.isNotBlank(riskLevel)) {
                        if ("正常".equals(riskLevel)) {
                            riskCode = "1";
                        } else if ("关注".equals(riskLevel)) {
                            riskCode = "2";
                        } else {
                            riskCode = "3";
                        }
                    }
                    if ("1".equals(type)) {
                        result = new QxfkPdfImg();
                        result.setZjhm(zjhm);
                        result.setLrr(yggh);
                        result.setLrsj(new Date());
                        result.setReportCode(repCode);
                        result.setRiskLevel(riskLevel);
                        result.setRiskLevelCode(riskCode);
                        result.setQueryTime(queryTime);
                        result.setImgFwlj(code);
                        result.setImgWllj(msg);
                        result.setKhmc(khmc);
                        result.setSjhm(sjhm);
                        baseMapper.insert(result);
                        return Result.ok(result);
                    } else if ("2".equals(type)) {
                        //更新数据
                        LambdaQueryWrapper<QxfkPdfImg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                        lambdaQueryWrapper.eq(QxfkPdfImg::getReportCode, repCode);
                        QxfkPdfImg qxfkPdfImg = baseMapper.selectOne(lambdaQueryWrapper);
                        qxfkPdfImg.setRiskLevel(riskLevel);
                        qxfkPdfImg.setRiskLevelCode(riskCode);
                        qxfkPdfImg.setQueryTime(queryTime);
                        qxfkPdfImg.setImgFwlj(code);
                        qxfkPdfImg.setImgWllj(msg);
                        qxfkPdfImg.setSjhm(sjhm);
                        qxfkPdfImg.setKhmc(khmc);
                        baseMapper.updateById(qxfkPdfImg);
                        return Result.ok(qxfkPdfImg);
                    }
                } else {
                    if ("1".equals(type)) {
                        result = new QxfkPdfImg();
                        result.setZjhm(zjhm);
                        result.setLrr(yggh);
                        result.setLrsj(new Date());
                        result.setImgFwlj(code);
                        result.setReportCode(repCode);
                        result.setSjhm(sjhm);
                        result.setKhmc(khmc);
                        if (StringUtils.isNotBlank(json.getMessage()))
                            result.setImgWllj(json.getMessage());
                        baseMapper.insert(result);
                        log.info("===查询失败{}===", result);
                        return Result.error("详情查询接口失败");
                    } else if ("2".equals(type)) {
                        LambdaQueryWrapper<QxfkPdfImg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                        lambdaQueryWrapper.eq(QxfkPdfImg::getReportCode, repCode);
                        QxfkPdfImg qxfkPdfImg = baseMapper.selectOne(lambdaQueryWrapper);
                        qxfkPdfImg.setImgFwlj(code);
                        if (StringUtils.isNotBlank(json.getMessage()))
                            qxfkPdfImg.setImgWllj(json.getMessage());
                        baseMapper.updateById(qxfkPdfImg);
                        return Result.error("详情查询接口更新数据失败");
                    }
                }
            } else {
                result = new QxfkPdfImg();
                result.setZjhm(zjhm);
                result.setLrr(yggh);
                result.setLrsj(new Date());
                result.setReportCode(repCode);
                result.setKhmc(khmc);
                result.setSjhm(sjhm);
                result.setImgWllj("网络故障，未查询到风控数据");
                baseMapper.insert(result);
                log.info("===JSON网络故障，未查询到风控数据{}===", result);
                return Result.error("查询接口失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("查询接口失败");
    }

    @Override
    public void queryQxfk(String zjhm, String khmc, String sjhm, String yggh, String qxfk) {
        try {
            if (StringUtils.isNotBlank(zjhm) && StringUtils.isNotBlank(khmc)) {
                if (StringUtils.isBlank(sjhm))
                    sjhm = "13888888888";
                //判断能不能查
                LambdaQueryWrapper<QxfkPdfImg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(QxfkPdfImg::getZjhm, zjhm);
                lambdaQueryWrapper.ge(QxfkPdfImg::getLrsj, DateUtil.beginOfYear(new Date()));
                lambdaQueryWrapper.le(QxfkPdfImg::getLrsj, DateUtil.endOfYear(new Date()));
                lambdaQueryWrapper.orderByDesc(QxfkPdfImg::getLrsj);
                List<QxfkPdfImg> list = baseMapper.selectList(lambdaQueryWrapper);
                if (CollUtil.isEmpty(list)) {
                    String ywdate = JsonUtil.getYwdate(zjhm, sjhm, qxfk, khmc, "1");
                    QxPdf qxPdf = ApiQxUtils.proApply(ywdate, qxfk);

                    String repCode = null;
                    String pdfData = null;
                    QxfkPdfImg result = null;
                    if (StringUtils.isNotBlank(qxPdf.getCode())) {
                        String code = qxPdf.getCode();
                        if ("2000".equals(code)) {
                            if (qxPdf.getInfo() != null && StringUtils.isNotBlank(qxPdf.getInfo().getReportCode())) {
                                repCode = qxPdf.getInfo().getReportCode();
                                pdfData = JsonUtil.getPdfData(repCode, qxfk);
                            }
                        } else {
                            result = new QxfkPdfImg();
                            result.setZjhm(zjhm);
                            result.setKhmc(khmc);
                            result.setSjhm(sjhm);
                            result.setLrr(yggh);
                            result.setLrsj(new Date());
                            result.setImgFwlj(code);
                            if (StringUtils.isNotBlank(qxPdf.getMessage()))
                                result.setImgWllj(qxPdf.getMessage());
                            baseMapper.insert(result);
                            log.info("===查询失败{}===", result);
                        }
                    }

                    if (pdfData != null) {
                        Thread.sleep(10000);
                        QxJsonVO json = ApiQxUtils.getJSON(pdfData, qxfk);
                        if (json != null) {
                            String code = json.getCode();
                            String msg = null;
                            if (StringUtils.isNotBlank(json.getMessage()))
                                msg = json.getMessage();
                            if ("2000".equals(code)) {
                                QxJsonInfo info = json.getInfo();
                                String riskLevel = info.getRiskLevel();
                                String queryTime = info.getQueryTime();
                                String riskCode = "4";
                                if (StringUtils.isNotBlank(riskLevel)) {
                                    if ("正常".equals(riskLevel)) {
                                        riskCode = "1";
                                    } else if ("关注".equals(riskLevel)) {
                                        riskCode = "2";
                                    } else {
                                        riskCode = "3";
                                    }
                                }
                                result = new QxfkPdfImg();

                                result.setZjhm(zjhm);
                                result.setSjhm(sjhm);
                                result.setKhmc(khmc);
                                result.setLrr(yggh);
                                result.setLrsj(new Date());
                                result.setReportCode(repCode);
                                result.setRiskLevel(riskLevel);
                                result.setRiskLevelCode(riskCode);
                                result.setQueryTime(queryTime);
                                result.setImgFwlj(code);
                                result.setImgWllj(msg);
                                baseMapper.insert(result);
                            } else {
                                result = new QxfkPdfImg();
                                result.setZjhm(zjhm);
                                result.setSjhm(sjhm);
                                result.setKhmc(khmc);
                                result.setLrr(yggh);
                                result.setLrsj(new Date());
                                result.setImgFwlj(code);
                                result.setReportCode(repCode);
                                if (StringUtils.isNotBlank(json.getMessage()))
                                    result.setImgWllj(json.getMessage());
                                baseMapper.insert(result);
                                log.info("===查询失败{}===", result);
                            }
                        } else {
                            result = new QxfkPdfImg();
                            result.setZjhm(zjhm);
                            result.setLrr(yggh);
                            result.setSjhm(sjhm);
                            result.setKhmc(khmc);
                            result.setLrsj(new Date());
                            result.setReportCode(repCode);
                            result.setImgWllj("网络故障，未查询到风控数据");
                            baseMapper.insert(result);
                            log.info("===JSON网络故障，未查询到风控数据{}===", result);
                        }
                    }

                }
                log.info("==={}已经有记录===",zjhm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
