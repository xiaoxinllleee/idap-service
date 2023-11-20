package org.cmms.modules.qxfk.qx.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.khgl.ckkh.entity.QhckphVO;
import org.cmms.modules.qxfk.qx.constant.AppConstant;
import org.cmms.modules.qxfk.qx.dto.QxJsonVO;
import org.cmms.modules.qxfk.qx.dto.QxPdf;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Date 2022/7/28
 * @Created by eran
 */
@Slf4j
public class ApiQxUtils {

    public static QxPdf proApply(String body, String dev) {
        try {
            String qz = AppConstant.YM_TEST;
            if (AppConstant.PROD.equals(dev)) {
                qz = AppConstant.YM_PROD;
            }
            String url = qz + AppConstant.API_BGSQ;
            HttpRequest post = HttpUtil.createPost(url);
            post.body(body);
            HttpResponse execute = post.execute();
            log.info("===报告申请结果{}===", execute);
            String res = execute.body();
            if (StringUtils.isNotBlank(res)) {
                return JsonUtil.toObject(res, QxPdf.class);
            }
        } catch (Exception e) {
            log.info("===报告申请接口未开通===");
            e.printStackTrace();
        }
        return null;
    }

    public static QxPdf getPDF(String body, String dev) {
        try {
            log.info("===等待报告生成时间10秒===");
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("===等待时间完毕===");
        QxPdf qxPdf = new QxPdf();
        String qz = AppConstant.YM_TEST;
        if (AppConstant.PROD.equals(dev)) {
            qz = AppConstant.YM_PROD;
        }
        String url = qz + AppConstant.API_PDF;
        HttpRequest post = HttpUtil.createPost(url);
        post.body(body);
        HttpResponse execute = post.execute();
        String res = execute.body();
        if (StringUtils.isNotBlank(res)) {
            qxPdf = JsonUtil.toObject(res, QxPdf.class);
        }
        return qxPdf;
    }


    public static QxJsonVO getJSON(String body, String dev) {
        try {
            QxJsonVO qxJsonVO = new QxJsonVO();
            String qz = AppConstant.YM_TEST;
            if (AppConstant.PROD.equals(dev)) {
                qz = AppConstant.YM_PROD;
            }
            String url = qz + AppConstant.API_JSON;
            HttpRequest post = HttpUtil.createPost(url);
            post.body(body);
            HttpResponse execute = post.execute();
            String res = execute.body();
            log.info("===JSON申请结果{}===", res);
            if (StringUtils.isNotBlank(res)) {
                qxJsonVO = JsonUtil.toObject(res, QxJsonVO.class);
            }
            return qxJsonVO;
        } catch (Exception e) {
            log.info("===七星风控查询json数据错误===");
            e.printStackTrace();
        }
        return null;
    }

    public static Object getAllJSON(String body, String dev) {
        try {
            String qz = AppConstant.YM_TEST;
            if (AppConstant.PROD.equals(dev)) {
                qz = AppConstant.YM_PROD;
            }
            String url = qz + AppConstant.API_JSON;
            HttpRequest post = HttpUtil.createPost(url);
            post.body(body);
            HttpResponse execute = post.execute();
            String res = execute.body();
            log.info("===JSON申请结果{}===", res);
            if (StringUtils.isNotBlank(res)) {
                return res;
            }
            return null;
        } catch (Exception e) {
            log.info("===七星风控查询json数据错误===");
            e.printStackTrace();
        }
        return null;
    }
    public static boolean makePdf(String base64, String path) {

        try {
            File file = new File(path);
            FileOutputStream fop = new FileOutputStream(file);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes = decoder.decodeBuffer(base64);
            fop.write(decodedBytes);
            fop.flush();
            fop.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
