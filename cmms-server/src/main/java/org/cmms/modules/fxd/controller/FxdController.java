package org.cmms.modules.fxd.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiV2UserGetuserinfoRequest;
import com.dingtalk.api.response.OapiV2UserGetuserinfoResponse;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Rectangle;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.fxd.entity.*;
import org.cmms.modules.fxd.service.ITaskKhjlrzService;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.INhbkbpyService;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.khxxgl.yjzrbg.entity.CamsZcsxYjzrbg;
import org.cmms.modules.khxxgl.yjzrbg.entity.ZxbgPdfImg;
import org.cmms.modules.khxxgl.yjzrbg.service.ICamsZcsxYjzrbgService;
import org.cmms.modules.khxxgl.yjzrbg.service.IZxbgPdfImgService;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.rwzx.rwcj.entity.*;
import org.cmms.modules.rwzx.rwcj.service.ITaskBfrwBaseService;
import org.cmms.modules.rwzx.rwcj.service.ItaskCreateService;
import org.cmms.modules.system.entity.AppHrBasOrganization;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysBasUser;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysBasUserService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.entity.Yxzfgzsz;
import org.cmms.modules.ygjx.entity.CDKLineChartVO;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date 2023/5/3
 * @Created by eran
 * <p>
 * 这部分需要通过钉钉认证之后把用户信息转化过来 目前还么有开发 先做一下临时的
 */
@RestController
@RequestMapping("/fxd")
@Slf4j
public class FxdController {
    @Value(value = "${common.path.upload}")
    protected String uploadpath;

    public String workNo = "8080";
    private final static String KEY = "nhxq";
    public SysBasUser sysBasUser;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    INhxqService nhxqService;
    @Autowired
    ListToDictUtil listToDictUtil;
    @Autowired
    IYwhywwlxxService ywhywwlxxService;
    @Autowired
    INhbkbpyService nhbkbpyService;
    @Autowired
    ITaskBfrwBaseService taskBfrwBaseService;
    @Autowired
    ItaskCreateService taskCreateService;
    @Autowired
    IYxdyglMainService yxdyglMainService;
    @Autowired
    IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    ISysBasUserService sysBasUserService;
    @Autowired
    ITaskKhjlrzService taskKhjlrzService;

    @Autowired
    ICamsZcsxYjzrbgService camsZcsxYjzrbgService;
    @Autowired
    IZxbgPdfImgService zxbgPdfImgService;

    public static com.aliyun.dingtalkoauth2_1_0.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkoauth2_1_0.Client(config);
    }

    @RequestMapping("/hide")
    public Result<?> hideApp(String userid) {
        System.out.println("=======hidehidehidehidehide");
        if (sysBasUser != null) {
            redisUtil.del("fxd" + sysBasUser.getUsername());
        }
        return Result.ok();
    }

    @RequestMapping("/khgl/rwjd")
    public Result<?> rwjd() {
        List<SysBasUser> list = sysBasUserService.list();
        for (int i = 0; i < list.size(); i++) {
            SysBasUser sysBasUser1 = list.get(i);
            Object o = redisUtil.get("fxd" + sysBasUser1.getUsername());
            Date o2 = (Date) redisUtil.get("fxdsj" + sysBasUser1.getUsername());
            if (o != null) {
                list.get(i).setFxdiszx(true);
                list.get(i).setFxdzjzxsj(new Date());
            } else {
                list.get(i).setFxdiszx(false);
                if (o2 != null) {
                    list.get(i).setFxdzjzxsj(o2);
                }
            }

        }
        return Result.ok(list);
    }

    @RequestMapping("/getUserInfo")
    public Result<?> getUserInfo(String autoCode) {
        //这里会拿到
        if (StringUtils.isBlank(autoCode))
            return Result.ok();
        FxdUserVO fxdUserVO = new FxdUserVO();
        String accessToken = getAccessToken();
        log.info("============autoCode={} === accessToken={} ", autoCode, accessToken);
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getuserinfo");
            OapiV2UserGetuserinfoRequest req = new OapiV2UserGetuserinfoRequest();
            req.setCode(autoCode);
            OapiV2UserGetuserinfoResponse rsp = client.execute(req, accessToken);
            System.out.println(rsp.getBody());
            JSONObject entries = JSONUtil.parseObj(rsp.getBody());
            String o = entries.get("errcode").toString();
            System.out.println(o);
            if (o.equals("0")) {
                JSONObject result = entries.getJSONObject("result");
                DingRspResult bean = JSONUtil.toBean(result, DingRspResult.class);
                System.out.println(bean);
                fxdUserVO.setDingRspResult(bean);
                //拿名称去匹配用户
                if (StringUtils.isNotBlank(bean.getName())) {
                    LambdaQueryWrapper<SysBasUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                    lambdaQueryWrapper.eq(SysBasUser::getRealname, bean.getName());
                    SysBasUser one = sysBasUserService.getOne(lambdaQueryWrapper, false);
                    if (one != null) {
                        fxdUserVO.setSysBasUser(one);
                        redisUtil.set("fxd" + one.getUsername(), new Date());
                        redisUtil.set("fxdsj" + one.getUsername(), new Date());
                    }

                    sysBasUser = one;

                }

            }

        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok(fxdUserVO);
    }

    public String getAccessToken() {
        try {
            Client client = createClient();

            GetAccessTokenRequest getAccessTokenRequest = new GetAccessTokenRequest();
            getAccessTokenRequest.setAppKey("dingrpknb4nurnu76imq");
            getAccessTokenRequest.setAppSecret("SrHHeYjP-znLjjGBLEuoVGztE6dZPVIFjHea7FRi6PJTJwCx9daITWO6Pn49Ng4e");

            try {
                GetAccessTokenResponse accessToken = client.getAccessToken(getAccessTokenRequest);
                return accessToken.getBody().getAccessToken();
            } catch (TeaException err) {
                if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                    // err 中含有 code 和 message 属性，可帮助开发定位问题
                }

            } catch (Exception _err) {
                TeaException err = new TeaException(_err.getMessage(), _err);
                if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                    // err 中含有 code 和 message 属性，可帮助开发定位问题
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/khgl/index")
    public Result<?> khglIndex(String wgbh) {
        //KhglIndexVO fxdIndex = nhxqService.getFxdIndex();
        if (sysBasUser != null) {
            System.out.println(sysBasUser);
            if (StringUtils.isNotBlank(sysBasUser.getTellid())) {
                String tellid = sysBasUser.getTellid();
                workNo = tellid;
            }
        }
        KhglIndexVO fxdIndex = nhxqService.getFxdIndex(wgbh, workNo);
        fxdIndex.jsCDK();
        return Result.ok(fxdIndex);
    }


    @RequestMapping("/khgl/rwtj")
    public Result<?> rwtj(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, String wgbh) {
        Page<WdrwVO> page = new Page<WdrwVO>(pageNo, pageSize);
        Page<WdrwVO> pageWdrw = taskBfrwBaseService.getPageRwtj(page, workNo, wgbh);
        return Result.ok(pageWdrw);
    }


    @RequestMapping("/khgl/list")
    public Result<?> khglList(HttpServletRequest req, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                              String search, Nhxq nhxq) {
        initAllNhInRedis();

        String wgbh = null;
        if (StringUtils.isNotBlank(nhxq.getWgbh()))
            wgbh = nhxq.getWgbh();
        nhxq.setWgbh(null);

        String longitude = null;
        String latitude = null;

        if (StringUtils.isNotBlank(nhxq.getLongitude())) {
            longitude = nhxq.getLongitude();
            log.info("===longitude={}===", longitude);
        }
        nhxq.setLongitude(null);

        if (StringUtils.isNotBlank(nhxq.getLatitude())) {
            latitude = nhxq.getLatitude();
            log.info("===latitude={}===", latitude);

        }
        nhxq.setLatitude(null);

        Page<Nhxq> page = new Page<Nhxq>(pageNo, pageSize);
        QueryWrapper<Nhxq> queryWrapper = QueryGenerator.initQueryWrapper(nhxq, req.getParameterMap());
        //LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(wgbh)) {
            queryWrapper.inSql("wgbh", "select wgbh from yxdygl_main start with wgbh='" + wgbh + "' connect by prior wgbh=parent_id");
        } else {
            queryWrapper.inSql("wgbh", "select menu_id from YXDYGL_PQQXGL where khjl = '" + workNo + "'");
        }
        if (StringUtils.isNotBlank(search)) {
            queryWrapper.like("zjhm", search).or().like("khmc", search);
        }
        Page<Nhxq> page1 = nhxqService.page(page, queryWrapper);
        if (StringUtils.isNotBlank(latitude) && StringUtils.isNotBlank(longitude)) {
            List<Nhxq> records = page1.getRecords();
            for (int i = 0; i < records.size(); i++) {
                Nhxq nhxq1 = records.get(i);
                if (StringUtils.isNotBlank(nhxq1.getLatitude()) && StringUtils.isNotBlank(nhxq1.getLongitude())) {
                    try {
                        double v = Double.parseDouble(nhxq1.getLatitude());
                        double v2 = Double.parseDouble(nhxq1.getLongitude());
                        double v3 = Double.parseDouble(longitude);
                        double v4 = Double.parseDouble(latitude);
                        double distance1 = getDistance(v2, v, v3, v4);
                        nhxq1.setFjdr(distance1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return Result.ok(page1);
    }

    @RequestMapping("/khgl/listByJWD")
    public Result<?> khglList(Double distance, Double longitude, Double latitude) {
        if (distance == null || latitude == null || longitude == null)
            return Result.ok();

        GeoResults<RedisGeoCommands.GeoLocation<Object>> reslut =
                redisTemplate.opsForGeo().radius(KEY,
                        new Circle(new Point(longitude, latitude), new Distance(distance, Metrics.KILOMETERS)),
                        RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                                .includeDistance()
                                .includeCoordinates().sortAscending());
        List<GeoResult<RedisGeoCommands.GeoLocation<Object>>> content = reslut.getContent();
        List<String> zjhms = new ArrayList<>();
        int size = 10;
        if (content.size() < 10)
            size = content.size();
        for (int i = 0; i < size; i++) {
            GeoResult<RedisGeoCommands.GeoLocation<Object>> geoLocationGeoResult = content.get(i);
            Object name = geoLocationGeoResult.getContent().getName();
            zjhms.add(name.toString());
        }

        if (zjhms.size() > 0){
            LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(Nhxq::getZjhm,zjhms);
            List<Nhxq> list = nhxqService.list(lambdaQueryWrapper);
            for (int i = 0; i < list.size(); i++) {
                Nhxq nhxq1 = list.get(i);
                if (StringUtils.isNotBlank(nhxq1.getLatitude()) && StringUtils.isNotBlank(nhxq1.getLongitude())) {
                    try {
                        double v = Double.parseDouble(nhxq1.getLatitude());
                        double v2 = Double.parseDouble(nhxq1.getLongitude());
                        double v3 = longitude;
                        double v4 = latitude;
                        double distance1 = getDistance(v2, v, v3, v4);
                        nhxq1.setFjdr(distance1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            return Result.ok();
        }

        return Result.ok();

    }

    public void initAllNhInRedis() {
        Object o = redisUtil.get("initAllNhInRedis");
        if (o == null) {
            LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.isNotNull(Nhxq::getZjhm);
            lambdaQueryWrapper.isNotNull(Nhxq::getLatitude);
            lambdaQueryWrapper.isNotNull(Nhxq::getLongitude);
            List<Nhxq> list = nhxqService.list(lambdaQueryWrapper);
            for (int i = 0; i < list.size(); i++) {
                Nhxq nhxq = list.get(i);
                Point point = new Point(Double.parseDouble(nhxq.getLongitude()), Double.parseDouble(nhxq.getLatitude()));
                redisTemplate.opsForGeo().add("nhxq", point, nhxq.getZjhm());
            }
            redisUtil.set("initAllNhInRedis", "1");
        }
    }

    @RequestMapping("/khgl/nhByZjhm")
    public Result<?> nhByZjhm(String zjhm) {
        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Nhxq::getZjhm, zjhm);
        Nhxq nhxq = nhxqService.getOne(lambdaQueryWrapper, false);
        Object o = listToDictUtil.parseDictText(nhxq);
        return Result.ok(o);
    }


    @RequestMapping(value = "/khgl/nhxqEdit")
    public Result<?> edit(@RequestBody Nhxq nhxq) {
        System.out.println(nhxq);
        //nhxq.setSfhz(nhxq.getYhzgx().equals("1") ? "1" : "2");
//        QueryWrapper queryWrapperZzbz = new QueryWrapper();
//        queryWrapperZzbz.eq("ywjgdm", nhxq.getJgdm());
//        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
//        nhxq.setSszh(hrBasOrganization.getZzbz());
        nhxq.setLrr(workNo);
        nhxq.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
        nhxq.setUpTm(DateUtil.formatDateTime("HHmmss"));
        nhxqService.updateById(nhxq);

//        QueryWrapper<Khjbzl> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("zjhm", nhxq.getZjhm());
//        Khjbzl khjbzl = khjbzlService.getOne(queryWrapper);
//        if (khjbzl != null) {
//            if (StringUtils.isEmpty(khjbzl.getWgbh())) {
//                khjbzl.setWgbh(nhxq.getWgbh());
//            }
//            if (StringUtils.isEmpty(khjbzl.getJgdm())) {
//                khjbzl.setJgdm(nhxq.getJgdm());
//            }
//
//            if (StringUtils.isEmpty(khjbzl.getKhlx())) {
//                khjbzl.setKhlx(nhxq.getKhlx());
//            }
//
//            if (StringUtils.isEmpty(khjbzl.getLxfs())) {
//                khjbzl.setLxfs(nhxq.getSjhm());
//            }
//            if (StringUtils.isEmpty(khjbzl.getDz())) {
//                khjbzl.setDz(nhxq.getZz());
//            }
//
//            khjbzlService.update(khjbzl, queryWrapper);
//        }
        return Result.ok("编辑成功!");
    }


    @RequestMapping("/khgl/jtxx")
    public Result<?> khglList(String hhbm) {
        LambdaQueryWrapper<Nhxq> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Nhxq::getHhbm, hhbm);
        lambdaQueryWrapper.orderByAsc(Nhxq::getYhzgx);
        List<Nhxq> list = nhxqService.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            List list1 = listToDictUtil.parseDictText(list);
            return Result.ok(list1);
        }
        return Result.ok(list);
    }

    @RequestMapping("/khgl/ywhywgx")
    public Result<?> ywhywgx(String zjhm) {
        LambdaQueryWrapper<Ywhywwlxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Ywhywwlxx::getZjhm, zjhm);
        List<Ywhywwlxx> list = ywhywwlxxService.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            List list1 = listToDictUtil.parseDictText(list);
            return Result.ok(list1.get(0));
        }
        return Result.ok(list);
    }

    @RequestMapping("/khgl/bkbpy")
    public Result<?> bkbpy(String zjhm) {
        LambdaQueryWrapper<Nhbkbpy> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Nhbkbpy::getZjhm, zjhm);
        lambdaQueryWrapper.orderByDesc(Nhbkbpy::getPyls);
        List<Nhbkbpy> list = nhbkbpyService.list(lambdaQueryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            List list1 = listToDictUtil.parseDictText(list);
            return Result.ok(list1);
        }
        return Result.ok(list);
    }

    @RequestMapping("/khgl/bfrw")
    public Result<?> bfrw(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                          WdrwSearchVO wdrwSearchVO) {
        wdrwSearchVO.setYggh(workNo);

        Page<WdrwSearchResultVO> page = new Page<WdrwSearchResultVO>(pageNo, pageSize);
        Page<WdrwSearchResultVO> wdrwSearchResultVOList = taskBfrwBaseService.getWdrwSearchResultVOList(page, wdrwSearchVO);
        List<WdrwSearchResultVO> records = wdrwSearchResultVOList.getRecords();
        if (CollUtil.isNotEmpty(records)) {
            for (int i = 0; i < records.size(); i++) {
                LambdaQueryWrapper<Ywhywwlxx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(Ywhywwlxx::getZjhm, records.get(i).getZjhm());
                Ywhywwlxx one = ywhywwlxxService.getOne(lambdaQueryWrapper, false);
                wdrwSearchResultVOList.getRecords().get(i).setYwhywwlxx(one);
            }
        }
//        List<String> yxidList = new ArrayList<>();
//        if (StringUtils.isNotBlank(type)){
//            LambdaQueryWrapper<TaskCreate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//            lambdaQueryWrapper.eq(TaskCreate::getMarketingType,type);
//            List<TaskCreate> list = taskCreateService.list(lambdaQueryWrapper);
//            for (int i = 0; i < list.size(); i++) {
//                TaskCreate taskCreate = list.get(i);
//                yxidList.add(taskCreate.getId());
//            }
//        }
//        Page<TaskBfrwBase> page = new Page<TaskBfrwBase>(pageNo, pageSize);
//        LambdaQueryWrapper<TaskBfrwBase> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if (CollUtil.isNotEmpty(yxidList)){
//            lambdaQueryWrapper.in(TaskBfrwBase::getYxid,yxidList);
//        }
//
//        lambdaQueryWrapper.inSql(TaskBfrwBase::getWgbh, "select menu_id from YXDYGL_PQQXGL where khjl = '" + workNo + "'");
//        Page<TaskBfrwBase> page1 = taskBfrwBaseService.page(page, lambdaQueryWrapper);
        return Result.ok(wdrwSearchResultVOList);
    }

    @RequestMapping("/khgl/bfrw/getById")
    public Result<?> bfrwGetById(String id) {
        TaskBfrwBase byId = taskBfrwBaseService.getById(id);
        Object o = listToDictUtil.parseDictText(byId);
        return Result.ok(o);
    }

    @RequestMapping("/khgl/bfrw/getByZjhm")
    public Result<?> bfrwGetByZjhm(String zjhm) {
        LambdaQueryWrapper<TaskBfrwBase> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TaskBfrwBase::getZjhm, zjhm);
        lambdaQueryWrapper.isNotNull(TaskBfrwBase::getUpdateTime);
        List<TaskBfrwBase> list = taskBfrwBaseService.list(lambdaQueryWrapper);
        return Result.ok(list);
    }

    @RequestMapping(value = "/khgl/bfrwEdit")
    public Result<?> BfrwEdit(@RequestBody TaskBfrwBase taskBfrwBase) {
        taskBfrwBase.setUpdator(workNo);
        taskBfrwBase.setUpdateTime(new Date());
        taskBfrwBaseService.updateById(taskBfrwBase);
        return Result.ok("编辑成功!");
    }

    @RequestMapping("/khgl/cdk")
    public Result<?> cdk(String wgbh) {
        List<String> list = new ArrayList<>();
        List<Integer> ck = new ArrayList<>();
        List<Integer> dk = new ArrayList<>();
        DateTime dateTime = cn.hutool.core.date.DateUtil.beginOfMonth(new Date());
        dateTime = cn.hutool.core.date.DateUtil.offsetMonth(dateTime, -5);

        for (int i = 0; i < 6; i++) {
            String yyMM = cn.hutool.core.date.DateUtil.format(dateTime, "yyMM");
            //String yyyyMMdd = cn.hutool.core.date.DateUtil.format(dateTime, "yyyyMMdd");
            list.add(yyMM);
            BigDecimal i1 = taskBfrwBaseService.dkYYMM(yyMM, wgbh, workNo);
            dk.add(i1.intValue());
            BigDecimal i2 = taskBfrwBaseService.ckYYMM(yyMM, wgbh, workNo);
            ck.add(i2.intValue());
            dateTime = cn.hutool.core.date.DateUtil.offsetMonth(dateTime, 1);
        }

        CDKLineChartVO cdkLineChartVO = new CDKLineChartVO();
        cdkLineChartVO.setCategories(list);
        cdkLineChartVO.setCk(ck);
        cdkLineChartVO.setDk(dk);
        return Result.ok(cdkLineChartVO);
    }

    @RequestMapping("/khgl/ckAcctDesc")
    public Result<?> ckAcctDesc(String type) {
        List<String> list = new ArrayList<>();
        List<Integer> dq = new ArrayList<>();
        List<Integer> hq = new ArrayList<>();
        DateTime dateTime = cn.hutool.core.date.DateUtil.beginOfMonth(new Date());
        dateTime = cn.hutool.core.date.DateUtil.offsetMonth(dateTime, -5);

        for (int i = 0; i < 6; i++) {
            String yyMM = cn.hutool.core.date.DateUtil.format(dateTime, "yyMM");
            list.add(yyMM);
            int i2 = taskBfrwBaseService.ckYYMM(yyMM, "S");
            dq.add(i2);
            int i3 = taskBfrwBaseService.ckYYMM(yyMM, "T");
            hq.add(i3);
            dateTime = cn.hutool.core.date.DateUtil.offsetMonth(dateTime, 1);
        }

        CDKLineChartVO cdkLineChartVO = new CDKLineChartVO();
        cdkLineChartVO.setCategories(list);
        cdkLineChartVO.setCk(dq);
        cdkLineChartVO.setDk(hq);
        return Result.ok(cdkLineChartVO);
    }


    @RequestMapping("/khgl/yxpciker")
    public Result<?> yxpciker() {
        return Result.ok(taskCreateService.getYxPicker());
    }

    @RequestMapping("/khgl/yxph")
    public Result<?> yxph(String yxid) {
        return Result.ok(taskBfrwBaseService.getZpfxVOList(yxid));
    }

    @RequestMapping("/khgl/khyj")
    public Result<?> test4() {
        List<KhyjVO> list = new ArrayList<>();

        Integer dqckdqs = taskBfrwBaseService.dqckdqs();
        Integer dqdkrs = taskBfrwBaseService.dqdkrs();

        KhyjVO khyjVO = new KhyjVO();
        khyjVO.setTitle("定期存款到期提醒");
        khyjVO.setRs(dqckdqs);
        khyjVO.setTs(30);
        khyjVO.setType("ck");
        khyjVO.setContent(" 条定期存款剩 30 天后到期");
        KhyjVO khyjVO2 = new KhyjVO();
        khyjVO2.setTitle("贷款到期提醒");
        khyjVO2.setRs(dqdkrs);
        khyjVO2.setTs(30);
        khyjVO2.setType("dk");
        khyjVO2.setContent(" 条贷款剩 30 天后到期");


        list.add(khyjVO);
        list.add(khyjVO2);
        return Result.ok(list);
    }

    @RequestMapping("/khgl/khyj/ck")
    public Result<?> test5(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<CbsInvmBase> page = new Page<CbsInvmBase>(pageNo, pageSize);
        Page<CbsInvmBase> ckdq = taskBfrwBaseService.getCkdq(page);
        return Result.ok(ckdq);
    }

    @RequestMapping("/khgl/khyj/dk")
    public Result<?> test6(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<CbsBormBase> page = new Page<CbsBormBase>(pageNo, pageSize);
        Page<CbsBormBase> ckdq = taskBfrwBaseService.getDkdq(page);
        return Result.ok(ckdq);
    }

    @RequestMapping("/khgl/wgone")
    public Result<?> wgone(String wgxz, String pid) {
        LambdaQueryWrapper<YxdyglMain> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(wgxz)) {
            lambdaQueryWrapper.eq(YxdyglMain::getWgxz, wgxz);
        }
        if (StringUtils.isNotBlank(pid)) {
            lambdaQueryWrapper.eq(YxdyglMain::getParentId, pid);
        }

        if (StringUtils.isNotBlank(workNo)) {
            log.info("===网格权限控制 当前员工工号为 {} ===", workNo);
            lambdaQueryWrapper.inSql(YxdyglMain::getWgbh, " select menu_id from yxdygl_pqqxgl where khjl = '" + workNo + "'");
        }

        List<YxdyglMain> list = yxdyglMainService.list(lambdaQueryWrapper);
        return Result.ok(list);
    }

    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private IKhjbzlService khjbzlService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;

    @PostMapping(value = "/khgl/nhxqAdd")
    public Result<?> nhxqAdd(@RequestBody Nhxq nhxq) {
        QueryWrapper queryWrapperNhxx = new QueryWrapper();
        queryWrapperNhxx.eq("zjhm", nhxq.getZjhm());
        Nhxq one = nhxqService.getOne(queryWrapperNhxx, false);
        if (one != null) {
            return Result.error("添加失败，证件号已存在！");
        }
        if (org.cmms.common.util.StringUtils.isEmpty(nhxq.getHhbm())) {
            nhxq.setHhbm(nhxq.getWgbh() + sysDictService.queryhhbm("seq_hhbm.nextval"));
        }
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        QueryWrapper queryWrapperZzbz = new QueryWrapper();
//        queryWrapperZzbz.eq("zzbz", nhxq.getSszh());
//        HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz,false);
        nhxq.setJgdm("02001");
        nhxq.setSszh("2");
        nhxq.setSfhz(nhxq.getYhzgx().equals("1") ? "1" : "2");
        nhxq.setLrbz("1");
        nhxq.setLrsj(new Date());
        nhxq.setLrr(workNo);
        nhxqService.save(nhxq);

        if (nhxq.getYhzgx().equals("1")) {
            QueryWrapper<KhglNhhzxxgl> queryWrapperHzxx = new QueryWrapper();
            queryWrapperHzxx.eq("hhbm", nhxq.getHhbm());
            KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(queryWrapperHzxx);
            if (khglNhhzxxgl == null) {
                KhglNhhzxxgl khglNhhzxxgl1 = new KhglNhhzxxgl();
                BeanUtils.copyProperties(nhxq, khglNhhzxxgl1);
                String id = UUIDGenerator.generate();
                khglNhhzxxgl1.setId(id);
                khglNhhzxxgl1.setSsyxdy(nhxq.getWgbh());
                khglNhhzxxgl1.setHzxm(nhxq.getKhmc());
                khglNhhzxxgl1.setHzzjhm(nhxq.getZjhm());
                khglNhhzxxgl1.setHhbm(nhxq.getHhbm());
                //khglNhhzxxgl1.setSxdx(nhxq.getKhmc());
                //khglNhhzxxgl1.setSxdxzjh(nhxq.getZjhm());
                khglNhhzxxgl1.setZkhjl(workNo);
                khglNhhzxxgl1.setSfzf("2");
                khglNhhzxxgl1.setSfyxzf("2");
                khglNhhzxxgl1.setLrsj(new Date());
                khglNhhzxxgl1.setLrbz("1");
                khglNhhzxxgl1.setLrr(workNo);
                khglNhhzxxglService.save(khglNhhzxxgl1);
            }
        }
        return Result.ok("添加成功！");
    }

    @RequestMapping("/khgl/zhtj/index")
    public Result<?> test7() {
        LambdaQueryWrapper<TaskBfrwBase> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.inSql(TaskBfrwBase::getWgbh, "select menu_id from YXDYGL_PQQXGL where khjl = '" + workNo + "'");
        lambdaQueryWrapper.eq(TaskBfrwBase::getStatus, "1");
        long count = taskBfrwBaseService.count(lambdaQueryWrapper);
        BigDecimal bigDecimal = taskBfrwBaseService.ckZhhj();
        BigDecimal bigDecimal2 = taskBfrwBaseService.dkZhhj();
        KhglIndexVO khglIndexVO = new KhglIndexVO();
        khglIndexVO.setRs(new BigDecimal(count + ""));
        khglIndexVO.setCk(bigDecimal);
        khglIndexVO.setDk(bigDecimal2);
        return Result.ok(khglIndexVO);
    }

    @RequestMapping("/khgl/zhtj/ck")
    public Result<?> test8() {
        List<CbsInvmBase> ckzhList = taskBfrwBaseService.getCKZHList();
        return Result.ok(ckzhList);
    }

    @RequestMapping("/khgl/zhtj/dk")
    public Result<?> test9() {
        List<CbsBormBase> dkzhList = taskBfrwBaseService.getDKZHList();
        return Result.ok(dkzhList);
    }

    @RequestMapping("/khgl/zzbzPicker")
    public Result<?> test10(String type, String sfjgdm) {
        List<AppHrBasOrganization> appHrBasOrganizationList = hrBasOrganizationService.getAppHrBasOrganizationList(type, sfjgdm);
        return Result.ok(appHrBasOrganizationList);
    }

    @PostMapping(value = "/khgl/addKhjlrz")
    public Result<?> addKhjlrz(@RequestBody TaskKhjlrz taskKhjlrz) {
        if (sysBasUser != null) {
            if (StringUtils.isNotBlank(sysBasUser.getTellid())) {
                taskKhjlrz.setCreator(sysBasUser.getTellid());
                taskKhjlrz.setYggh(sysBasUser.getTellid());
            }
            taskKhjlrz.setCreateTime(new Date());
            if (StringUtils.isNotBlank(sysBasUser.getRealname()))
                taskKhjlrz.setFxdxm(sysBasUser.getRealname());
            if (StringUtils.isNotBlank(sysBasUser.getJgdm()))
                taskKhjlrz.setZzbz(sysBasUser.getJgdm());
        }
        taskKhjlrzService.save(taskKhjlrz);
        return Result.ok("添加成功！");
    }

    @GetMapping(value = "/khgl/khjlrz/list")
    public Result<?> queryPageList(TaskKhjlrz taskKhjlrz,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TaskKhjlrz> queryWrapper = QueryGenerator.initQueryWrapper(taskKhjlrz, req.getParameterMap());
        Page<TaskKhjlrz> page = new Page<TaskKhjlrz>(pageNo, pageSize);
        IPage<TaskKhjlrz> pageList = taskKhjlrzService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    @GetMapping(value = "/khgl/map/fjdr")
    public Result<?> fjdr(@RequestParam("distance") double distance,
                          @RequestParam("userLng") double userLng,
                          @RequestParam("userLat") double userLat) {
        //1.获取外接正方形
        Rectangle rectangle = getRectangle(distance, userLng, userLat);
        System.out.println(rectangle.getMaxX());
        System.out.println(rectangle.getMinX());
        System.out.println(rectangle.getMaxY());
        System.out.println(rectangle.getMinY());
        //2.获取位置在正方形内的所有用户
        List<Nhxq> nhxqs = nhxqService.selectUser(rectangle.getMinX(), rectangle.getMaxX(), rectangle.getMinY(), rectangle.getMaxY());
        List<Nhxq> results = new ArrayList<>();
        for (int i = 0; i < nhxqs.size(); i++) {
            Nhxq nhxq = nhxqs.get(i);
            double distance1 = getDistance(Double.parseDouble(nhxq.getLongitude()), Double.parseDouble(nhxq.getLatitude()), userLng, userLat);
            if (distance1 <= distance) {
                nhxq.setFjdr(distance1);
                results.add(nhxq);
            }

        }
        return Result.ok(results);
    }

    SpatialContext spatialContext = SpatialContext.GEO;


    private Rectangle getRectangle(double distance, double userLng, double userLat) {
        return spatialContext.getDistCalc()
                .calcBoxByDistFromPt(spatialContext.makePoint(userLng, userLat),
                        distance * DistanceUtils.KM_TO_DEG, spatialContext, null);
    }

    /***
     * 球面中，两点间的距离
     * @param longitude 经度1
     * @param latitude  纬度1
     * @param userLng   经度2
     * @param userLat   纬度2
     * @return 返回距离，单位km
     */
    private double getDistance(Double longitude, Double latitude, double userLng, double userLat) {
        return spatialContext.calcDistance(spatialContext.makePoint(userLng, userLat),
                spatialContext.makePoint(longitude, latitude)) * DistanceUtils.DEG_TO_KM;
    }

    @RequestMapping("/sdpdf")
    public Result<?> sdpdf(String s, String zjhm) {
        System.out.println(s);
        System.out.println(zjhm);
        System.out.println(camsZcsxYjzrbgService);
        System.out.println(nhxqService);
        boolean file = FileUtil.isFile(s);


        LambdaQueryWrapper<CamsZcsxYjzrbg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CamsZcsxYjzrbg::getZjhm, zjhm);
        CamsZcsxYjzrbg one = camsZcsxYjzrbgService.getOne(lambdaQueryWrapper, false);
        if (one == null) {
            CamsZcsxYjzrbg camsZcsxYjzrbg = new CamsZcsxYjzrbg();
            camsZcsxYjzrbg.setCreator("zxbgpdf");
            camsZcsxYjzrbg.setCreateTime(new Date());
            camsZcsxYjzrbg.setZjhm(zjhm);
            camsZcsxYjzrbg.setHczt("1");
            camsZcsxYjzrbgService.save(camsZcsxYjzrbg);
        }


        if (file) {
            try {
                InputStream is = new FileInputStream(s);
                PDDocument doc = PDDocument.load(is);
                PDFRenderer renderer = new PDFRenderer(doc);
                int pageCount = doc.getNumberOfPages();
                for (int i = 0; i < pageCount; i++) {
                    // dpi，图片像素点，dpi越高图片体积越大，216很清晰，105体积稳定
                    BufferedImage image = renderer.renderImageWithDPI(i, 216);
                    // 格式为JPG
                    String s2 = s.replace(".pdf", "");
                    FileOutputStream fileOutputStream = new FileOutputStream(s + i + ".jpg");
                    ImageIO.write(image, "jpg", fileOutputStream);
                    //list.add(fwpath+"/"+zjhm+i+".jpg");
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }

                LambdaQueryWrapper<ZxbgPdfImg> zxbgPdfImgLambdaQueryWrapper = new LambdaQueryWrapper<>();
                zxbgPdfImgLambdaQueryWrapper.eq(ZxbgPdfImg::getZjhm, zjhm);
                zxbgPdfImgService.remove(zxbgPdfImgLambdaQueryWrapper);

                ZxbgPdfImg zxbgPdfImg = new ZxbgPdfImg();
                zxbgPdfImg.setZjhm(zjhm);
                zxbgPdfImg.setLrr("zxbgpdf");
                zxbgPdfImg.setLrsj(new Date());
                zxbgPdfImg.setWljl(s);
                String replace = uploadpath.replace("//", "/");
                String s3 = s.replace(replace, "");
                zxbgPdfImg.setFwlj(s3);
                zxbgPdfImg.setImgNumber(pageCount + "");
                zxbgPdfImgService.save(zxbgPdfImg);
            } catch (Exception e) {
                System.out.println("=== pdf转图片出错===");
                e.printStackTrace();
            }
        }

        return Result.ok();
    }
}
