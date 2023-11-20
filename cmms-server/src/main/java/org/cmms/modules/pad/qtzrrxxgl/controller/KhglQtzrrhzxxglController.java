package org.cmms.modules.pad.qtzrrxxgl.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.utils.AreaUtil;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.nhxxgl.entity.KhglYwhywwlxxPad;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxDksjmxPad;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxEtc;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxSjyhPad;
import org.cmms.modules.pad.nhxxgl.service.*;
import org.cmms.modules.pad.qtzrrxxgl.entity.*;
import org.cmms.modules.pad.qtzrrxxgl.service.*;
import org.cmms.modules.pad.qtzrrxxgl.service.IYxglQtzrrhfxxbService;
import org.cmms.modules.pad.shxxgl.entity.Xyk;
import org.cmms.modules.pad.shxxgl.service.IXykService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysUserService;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.yxdygl.pqzrrgl.service.ITjfxcsszService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date: 2020-07-15
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "1")
@RestController
@RequestMapping("/khglQtzrrhzxxgl")
public class KhglQtzrrhzxxglController extends JeecgController<KhglQtzrrhzxxgl, IKhglQtzrrhzxxglService> {
    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private IKhglQtzrrhzxxglService khglQtzrrhzxxglService;

    @Autowired
    private IYwhywwlxxService ywhywwlxxService;

    @Autowired
    private IKhglQtzrrhzzllbService khglQtzrrhzzllbService;

    @Autowired
    private ICamsZcsxQtzrrfcxxPadService camsZcsxQtzrrfcxxPadService;

    @Autowired
    private ICamsZcsxQtzrrpjsxxxPadService camsZcsxQtzrrpjsxxxPadService;

    @Autowired
    private ICamsZcsxQtzrrcjxxPadService camsZcsxQtzrrcjxxPadService;
    @Autowired
    private IVKhglQtzrrhzxxglService vKhglQtzrrhzxxglService;
    @Autowired
    private IVKhglQtzrrhzxxglQueryService vKhglQtzrrhzxxglQueryService;
    @Autowired
    private IKhywxxWsyhService khywxxWsyhMapper;
    @Autowired
    private IKhywxxSjyhPadService khywxxSjyhPadMapper;
    @Autowired
    private IKhywxxEtcService khywxxEtcMapper;
    @Autowired
    private IKhywxxDksjmxPadService khywxxDksjmxPadMapper;
    @Autowired
    private IKhglYwhywwlxxPadService khglYwhywwlxxPadMapper;
    @Autowired
    private AreaUtil areaUtil;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IYxglQtzrrhfxxbService yxglQtzrrhfxxbService;
    @Autowired
    private IVhrbasstaffpostService vhrbasstaffpostService;
    @Autowired
    private ITjfxcsszService tjfxcsszService;
    @Autowired
    private IKhglQtzrrhmcxxPadService khglQtzrrhmcxxPadService;
    @Autowired
    private ITjfxCsszService iTjfxCsszService;
    @Autowired
    private IXykService xykService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param khglQtzrrhzxxglQuery
     * @param currentPage
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询")
    @ApiOperation(value = "1-分页列表查询", notes = "1-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(VKhglQtzrrhzxxglQuery khglQtzrrhzxxglQuery,
                                   @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "locationQuery", required = false, defaultValue = "false") Boolean locationQuery,
                                   HttpServletRequest req) {
        System.out.println("=============进入请求查询其他自然人户主信息=================" + DateUtil.currentDateTime());
        log.info("进入请求查询其他自然人户主信息");
        String hzxm = khglQtzrrhzxxglQuery.getHzxm();
        String hzzjhm = khglQtzrrhzxxglQuery.getHzzjhm();
        khglQtzrrhzxxglQuery.setHzxm(null);
        khglQtzrrhzxxglQuery.setHzzjhm(null);
        QueryWrapper<VKhglQtzrrhzxxglQuery> queryWrapper = QueryGenerator.initQueryWrapper(khglQtzrrhzxxglQuery, req.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //查询是否开通三级营销单元
        String sfkt = tjfxcsszService.selectcsz(); //1是 2否
//        Map<Object, Object> map = areaUtil.getEjyxdyqx(req);
        //TODO
        //由于有的用户分配的权限过多，使用in会报错，暂改成直接通过语句查询
        if (!StringUtils.isEmpty(sfkt) && "1".equalsIgnoreCase(sfkt)) {
            queryWrapper.exists("select 1 from (" +
                    "    select t3.dybh,t3.dymc" +
                    "    from sys_bas_user t1,yxdygl_pqzrrgl t2,yxdygl_sjyxdygl t3" +
                    "    where t1.tellid=t2.khjl and t1.username = '" + sysUser.getUsername() + "' and t2.sjyxdybh=t3.dybh" +
                    "    group by t3.dybh,t3.dymc) n1 where ssyxdy=n1.dybh");
        } else {
            queryWrapper.exists("select 1 from (" +
                    "    select t3.dybh,t3.dymc" +
                    "    from sys_bas_user t1,yxdygl_pqzrrgl t2,yxdygl_ejyxdygl t3" +
                    "    where t1.tellid=t2.khjl and t1.username = '" + sysUser.getUsername() + "' and t2.ejyxdybh=t3.dybh" +
                    "    group by t3.dybh,t3.dymc) n1 where ssyxdy=n1.dybh");
        }
        System.out.println("=============查询户主信息111=================" + DateUtil.currentDateTime());
        log.info("查询户主信息");
        Page<VKhglQtzrrhzxxglQuery> page = new Page<VKhglQtzrrhzxxglQuery>(currentPage, pageSize);


        //根据姓名或者证件号码查询户主信息
        khglQtzrrhzxxglQuery.setHzxm(hzxm);
        khglQtzrrhzxxglQuery.setHzzjhm(hzzjhm);
        List<String> hhbmList = vKhglQtzrrhzxxglService.selectHzByCy(khglQtzrrhzxxglQuery, req);
        System.out.println("=============查询户主信息222=================" + DateUtil.currentDateTime());
        if (hhbmList != null && hhbmList.size() > 0) {
            if(!StringUtils.isEmpty(khglQtzrrhzxxglQuery.getHzxm())) {
                queryWrapper.and(wrapper -> wrapper.like("hzxm", khglQtzrrhzxxglQuery.getHzxm()).or().in("hhbm", hhbmList));
            } else {
                queryWrapper.and(wrapper -> wrapper.eq("hzzjhm", khglQtzrrhzxxglQuery.getHzzjhm()).or().in("hhbm", hhbmList));
            }
        } else {
            if(!StringUtils.isEmpty(khglQtzrrhzxxglQuery.getHzxm())) {
                queryWrapper.like("hzxm", khglQtzrrhzxxglQuery.getHzxm());
            }
            if (!StringUtils.isEmpty(khglQtzrrhzxxglQuery.getHzzjhm())) {
                queryWrapper.eq("hzzjhm", khglQtzrrhzxxglQuery.getHzzjhm());
            }
        }


        IPage<VKhglQtzrrhzxxglQuery> pageList = vKhglQtzrrhzxxglQueryService.page(page, queryWrapper);
        System.out.println("=============查询户主信息333=================" + DateUtil.currentDateTime());
        if (locationQuery) {

            queryWrapper.isNotNull("longitude");
            queryWrapper.isNotNull("latitude");
            long total = pageList.getTotal();
            pageList = vKhglQtzrrhzxxglQueryService.page(page, queryWrapper);
            pageList.setSize(total);
        }

        Page<VKhglQtzrrhzxxgl> khglQtzrrhzxxglPageList = new Page<>(pageList.getCurrent(), pageList.getSize(), pageList.getTotal());
        List<VKhglQtzrrhzxxglQuery> vKhglQtzrrhzxxglQueryList = pageList.getRecords();
        List<String> ids = new ArrayList<>();
        if (vKhglQtzrrhzxxglQueryList != null && !vKhglQtzrrhzxxglQueryList.isEmpty()) {
            for (VKhglQtzrrhzxxglQuery qtzrrhzxxglQuery : vKhglQtzrrhzxxglQueryList) {
                ids.add(qtzrrhzxxglQuery.getId());
            }
        }
        if (ids.size() > 0) {
            List<VKhglQtzrrhzxxgl> list = (List)vKhglQtzrrhzxxglService.listByIds(ids);
            khglQtzrrhzxxglPageList.setRecords(list);
        }
        System.out.println("=============查询户主信息完成=================" + DateUtil.currentDateTime());
        log.info("查询户主信息完成");
        return Result.ok(khglQtzrrhzxxglPageList);
    }

    /**
     * 分页列表查询
     *
     * @param vKhglQtzrrhzxxgl
     * @param pageSize
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询")
    @ApiOperation(value = "1-分页列表查询", notes = "1-分页列表查询")
    @GetMapping(value = "/queryWdcjList")
    public Result<?> queryWdcjList(VKhglQtzrrhzxxgl vKhglQtzrrhzxxgl,
                                   @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<VKhglQtzrrhzxxgl> queryWrapper = QueryGenerator.initQueryWrapper(vKhglQtzrrhzxxgl, req.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        queryWrapper.eq("lrr", sysUser.getUsername());
        queryWrapper.orderByDesc("up_dt");
        Page<VKhglQtzrrhzxxgl> page = new Page<VKhglQtzrrhzxxgl>(currentPage, pageSize);
        IPage<VKhglQtzrrhzxxgl> pageList = vKhglQtzrrhzxxglService.page(page, queryWrapper);
        if (pageList.getRecords().size()<=0){
            if (!StringUtils.isEmpty(vKhglQtzrrhzxxgl.getHzxm()) || !StringUtils.isEmpty(vKhglQtzrrhzxxgl.getHzzjhm())) {
                //根据家庭成员查询户主信息
                VKhglQtzrrhzxxglQuery vKhglQtzrrhzxxglQuery = new VKhglQtzrrhzxxglQuery();
                BeanUtils.copyProperties(vKhglQtzrrhzxxgl, vKhglQtzrrhzxxglQuery);
                List<String> hhbmList = vKhglQtzrrhzxxglService.selectHzByCy(vKhglQtzrrhzxxglQuery, req);
                QueryWrapper<VKhglQtzrrhzxxgl> queryWrapper2 = new QueryWrapper<>();
                if (hhbmList.size() > 0) {
                    queryWrapper2.in("hhbm", hhbmList);
                } else {
                    queryWrapper2.isNull("hhbm");
                }
                queryWrapper2.eq("lrr", sysUser.getUsername());
                queryWrapper2.orderByDesc("up_dt");
                //queryWrapper2.isNotNull("lrsj");
                IPage<VKhglQtzrrhzxxgl> vKhglNhhzxxglIPage = vKhglQtzrrhzxxglService.page(page, queryWrapper2);

                return  Result.ok(vKhglNhhzxxglIPage);
            }
        }
        return Result.ok(pageList);
    }


    @GetMapping(value = "/queryCzxxByAdd")
    public Result<?> queryCzxxByAdd(HttpServletRequest req) {
        try {
            LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
            String realname = loginUser.getRealname();
            List<Map<String,String>> list=new ArrayList<>();
            Map<Object, Object> map = areaUtil.getEjyxdyqx(req);
            Set<Map.Entry<Object, Object>> entries = map.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                Map<String,String> map1= new HashMap<>();
                map1.put("qybm",entry.getKey().toString());
                map1.put("mc",entry.getValue().toString());
                map1.put("realname",realname);
                list.add(map1);
            }
            return Result.ok(list);
        } catch (Exception e) {
            return Result.error(e.toString());
        }
    }

    /*
    通过户号编码去花名册查询家庭信息
     */
    @GetMapping(value = "/queryHzxxByHhbm")
    public Result<?> queryHzxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if (hhbm != null) {
                QueryWrapper<VKhglQtzrrhzxxgl> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("hhbm", hhbm);
                List<VKhglQtzrrhzxxgl> list = vKhglQtzrrhzxxglService.list(queryWrapper);
                if (list != null && list.size() > 0) {
                    return Result.ok(list.get(0));
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有找到数据");
    }

    /*
   通过户号编码去花名册查询授信对象证件号码
    */
    @GetMapping(value = "/querySxdxZjhm")
    public Result<?> querySxdxZjhm(@Param("hhbm") String hhbm) {
        try {
            JSONObject jsonObject = new JSONObject();
            QueryWrapper<KhglQtzrrhzxxgl> hzxxglQueryWrapper = new QueryWrapper<>();
            hzxxglQueryWrapper.eq("hhbm", hhbm);
            KhglQtzrrhzxxgl  khglQtzrrhzxxgl = khglQtzrrhzxxglService.getOne(hzxxglQueryWrapper);
           if (khglQtzrrhzxxgl.getSxdxzjh() != null ){
               QueryWrapper<KhglQtzrrhmcxx> hmcxxQueryWrapper = new QueryWrapper<>();
               hmcxxQueryWrapper.eq("zjhm", khglQtzrrhzxxgl.getSxdxzjh());
               KhglQtzrrhmcxx  khglQtzrrhmcxx = khglQtzrrhmcxxPadService.getOne(hmcxxQueryWrapper);
               if (khglQtzrrhmcxx != null){
                   jsonObject.put("sxdxzjhmflage",khglQtzrrhmcxx.getZjhm());
                   jsonObject.put("sxdxid",khglQtzrrhmcxx.getId());
                   return Result.ok(jsonObject);
               }
           }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有找到任何数据");
    }

    @GetMapping(value = "/queryGrxxByZjhm")
    public Result<?> queryGrxxByZjhm(@Param("id") String id) {
        try {
            if (id != null) {
                QueryWrapper<KhglQtzrrhmcxx> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("id", id);
                List<KhglQtzrrhmcxx> list = khglQtzrrhmcxxPadService.list(khhmcQueryWrapper);
                if (list != null && list.size() > 0) {
                    KhglQtzrrhmcxx qtzrrhmcxx = list.get(0);
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(qtzrrhmcxx).toString());
                    jsonObject.put("sszh_dictText",qtzrrhmcxx.getSszh() == null ? "" :  sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", qtzrrhmcxx.getSszh()));
                    if (!StringUtils.isEmpty(qtzrrhmcxx.getSsyxdy())) {
                        String ssxz_dictText = sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl", "yjyxdymc", "dybh", qtzrrhmcxx.getSsyxdy());
                        String xzc_dictText = sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl", "dymc", "dybh", qtzrrhmcxx.getSsyxdy());
                        if (StringUtils.isEmpty(ssxz_dictText)) {
                            ssxz_dictText = sysDictService.queryTableDictTextByKey("v_yxdygl_sjyxdygl", "yjyxdymc", "dybh", qtzrrhmcxx.getSsyxdy());
                        }
                        if (StringUtils.isEmpty(xzc_dictText)) {
                            xzc_dictText = sysDictService.queryTableDictTextByKey("v_yxdygl_sjyxdygl", "ejyxdymc", "dybh", qtzrrhmcxx.getSsyxdy());
                        }
                        jsonObject.put("ssxz_dictText", ssxz_dictText);
                        jsonObject.put("xzc_dictText", xzc_dictText);
                    } else {
                        jsonObject.put("ssxz_dictText", "");
                        jsonObject.put("xzc_dictText", "");
                    }
                    return Result.ok(jsonObject);
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有找到数据");
    }


    @GetMapping(value = "/queryGrcjxxByZjhm")
    public Result<?> queryGrcjxxByZjhm(@Param("id") String id) {
        try {
            if (id != null) {
                QueryWrapper<KhglQtzrrhmcxx> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("id", id);
                KhglQtzrrhmcxx khglQtzrrhmcxx = khglQtzrrhmcxxPadService.getOne(khhmcQueryWrapper);
                QueryWrapper<CamsZcsxQtzrrcjxxPad> nhcjxxWrapper = new QueryWrapper<>();
                nhcjxxWrapper.eq("zjhm", khglQtzrrhmcxx.getZjhm());
                List<CamsZcsxQtzrrcjxxPad> list = camsZcsxQtzrrcjxxPadService.list(nhcjxxWrapper);
                if (list != null && list.size() > 0) {
                    CamsZcsxQtzrrcjxxPad camsZcsxQtzrrcjxxPad = list.get(0);
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(camsZcsxQtzrrcjxxPad).toString());
                    return Result.ok(jsonObject);
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有找到数据");
    }

    @GetMapping(value = "/queryFcxxByHhbm")
    public Result<?> queryFcxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if (hhbm != null) {
                QueryWrapper<CamsZcsxQtzrrfcxxPad> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("hhbm", hhbm);
                List<CamsZcsxQtzrrfcxxPad> list = camsZcsxQtzrrfcxxPadService.list(queryWrapper);
                if (list.size() > 0) {
                    return Result.ok(list);
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有找到数据");
    }

    @RequestMapping(value = "/queryPjsxZcTable", method = RequestMethod.GET)
    public Result<?> queryPjsxZcTable(@Param("hhbm") String hhbm) {
        try {
            LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
            JSONArray jsonArray = new JSONArray();
            QueryWrapper<CamsZcsxQtzrrpjsxxxPad> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("hhbm", hhbm);
            List<CamsZcsxQtzrrpjsxxxPad> nhPjsxxxList = camsZcsxQtzrrpjsxxxPadService.list(queryWrapper);
            if (nhPjsxxxList == null || nhPjsxxxList.size()<=0) {
                return Result.ok("没有数据");
            } else {
                for (CamsZcsxQtzrrpjsxxxPad pjsxxx : nhPjsxxxList) {
                    //资产情况
                    JSONObject jo = new JSONObject();
                    jo.put("zclx", "地产");
                    jo.put("zcsl", pjsxxx.getDcsl());
                    jo.put("zcjz", pjsxxx.getDcjz());
                    jo.put("zcsm", pjsxxx.getDcxqsm());
                    JSONObject jo1 = new JSONObject();
                    jo1.put("zclx", "交通工具");
                    jo1.put("zcsl", pjsxxx.getJtgjsl());
                    jo1.put("zcjz", pjsxxx.getJtgjjz());
                    jo1.put("zcsm", pjsxxx.getJtgjxqsm());
                    JSONObject jo2 = new JSONObject();
                    jo2.put("zclx", "存款");
                    jo2.put("zcsl", pjsxxx.getCksl());
                    jo2.put("zcjz", pjsxxx.getCkjz());
                    jo2.put("zcsm", pjsxxx.getCkxqsm());
                    JSONObject jo3 = new JSONObject();
                    jo3.put("zclx", "有价单证");
                    jo3.put("zcsl", pjsxxx.getYjdzsl());
                    jo3.put("zcjz", pjsxxx.getYjdzjz());
                    jo3.put("zcsm", pjsxxx.getYjdzxqsm());
                    JSONObject jo4 = new JSONObject();
                    jo4.put("zclx", "股权");
                    jo4.put("zcsl", pjsxxx.getGqsl());
                    jo4.put("zcjz", pjsxxx.getGqjz());
                    jo4.put("zcsm", pjsxxx.getGqxqsm());
                    JSONObject jo5 = new JSONObject();
                    jo5.put("zclx", "其他资产");
                    jo5.put("zcsl", pjsxxx.getQtzcsl());
                    jo5.put("zcjz", pjsxxx.getQtzcjz());
                    jo5.put("zcsm", pjsxxx.getQtzcxqsm());
                    jsonArray.add(jo);
                    jsonArray.add(jo1);
                    jsonArray.add(jo2);
                    jsonArray.add(jo3);
                    jsonArray.add(jo4);
                    jsonArray.add(jo5);
                }
                return Result.ok(jsonArray);
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
    }

    @RequestMapping(value = "/queryPjsxFzTable", method = RequestMethod.GET)
    public Result<?> queryPjsxFzTable(@Param("hhbm") String hhbm) {
        try {
            JSONArray jsonArray = new JSONArray();
            QueryWrapper<CamsZcsxQtzrrpjsxxxPad> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("hhbm", hhbm);
            List<CamsZcsxQtzrrpjsxxxPad> nhPjsxxxList = camsZcsxQtzrrpjsxxxPadService.list(queryWrapper);
            if (nhPjsxxxList == null || nhPjsxxxList.size()<=0) {
                return Result.ok("没有数据");
            } else {
                for (CamsZcsxQtzrrpjsxxxPad pjsxxx : nhPjsxxxList) {
                    JSONObject jo1 = new JSONObject();
                    jo1.put("jkfs", "本系统");
                    jo1.put("zqr", pjsxxx.getBxtjkzqr());
                    jo1.put("jkje", pjsxxx.getBxtjksl());
                    jo1.put("jksm", pjsxxx.getBxtjkxqsm());
                    JSONObject jo2 = new JSONObject();
                    jo2.put("jkfs", "他行");
                    jo2.put("zqr", pjsxxx.getThjkzqr());
                    jo2.put("jkje", pjsxxx.getThjksl());
                    jo2.put("jksm", pjsxxx.getThjkxqsm());
                    JSONObject jo3 = new JSONObject();
                    jo3.put("jkfs", "信用卡");
                    jo3.put("zqr", pjsxxx.getXykzqr());
                    jo3.put("jkje", pjsxxx.getXyksl());
                    jo3.put("jksm", pjsxxx.getXykxqsm());
                    JSONObject jo4 = new JSONObject();
                    jo4.put("jkfs", "其他");
                    jo4.put("zqr", pjsxxx.getQtfzzqr());
                    jo4.put("jkje", pjsxxx.getQtfzsl());
                    jo4.put("jksm", pjsxxx.getQtfzxqsm());
                    JSONObject jo5 = new JSONObject();
                    jo5.put("jkfs", "家庭年开支");
                    jo5.put("zqr", pjsxxx.getJtnkzzqr());
                    jo5.put("jkje", pjsxxx.getJtnkzsl());
                    jo5.put("jksm", pjsxxx.getJtnkzxqsm());
                    jsonArray.add(jo1);
                    jsonArray.add(jo2);
                    jsonArray.add(jo3);
                    jsonArray.add(jo4);
                    jsonArray.add(jo5);
                }
                return Result.ok(jsonArray);
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
    }

    /*
   通过证件号码去花名册查询家庭信息
    */
    @GetMapping(value = "/queryJtcyxxByZjhm")
    public Result<?> queryJtcyxxByZjhm(@Param("zjhm") String zjhm) {
        try {
            if (zjhm!=null){
                QueryWrapper<KhglQtzrrhmcxx> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("zjhm", zjhm);
                List<KhglQtzrrhmcxx> list = khglQtzrrhmcxxPadService.list(khhmcQueryWrapper);
                if (list != null && list.size() > 0) {
                    KhglQtzrrhmcxx khhmcxx = list.get(0);
                    String hhbm = khhmcxx.getHhbm();
                    //判断是否授信
                    QueryWrapper<CamsZcsxQtzrrpjsxxxPad> pjsxxxQueryWrapper = new QueryWrapper<>();
                    pjsxxxQueryWrapper.eq("hhbm", hhbm);
                    pjsxxxQueryWrapper.isNotNull("zzsxed");
                    CamsZcsxQtzrrpjsxxxPad pjsxxx = camsZcsxQtzrrpjsxxxPadService.getOne(pjsxxxQueryWrapper);
                    if (pjsxxx != null) {
                        return Result.error("待添加客户所在户已评级授信，不能进行此操作");
                    }
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(khhmcxx).toString());
                    //判断是否户主
                    String sfhz = khhmcxx.getSfhz();
                    if ("1".equalsIgnoreCase(sfhz)) {
                        //如果是户主，查询有没有家庭成员
                        QueryWrapper<KhglQtzrrhmcxx> jtcyQueryWrapper = new QueryWrapper<>();
                        jtcyQueryWrapper.eq("hhbm", hhbm);
                        jtcyQueryWrapper.eq("sfhz", "2");
                        List<KhglQtzrrhmcxx> jtcyList = khglQtzrrhmcxxPadService.list(jtcyQueryWrapper);
                        if (jtcyList != null && jtcyList.size() > 0) {
                            //有家庭成员，将成员列表返回到界面，用户需要选择新的户主与调整其他成员的与户主关系
                            JSONArray jtcyArray = JSONArray.parseArray(JSONArray.toJSONString(jtcyList));
                            for (int i = 0; i < jtcyArray.size(); i++) {
                                JSONObject jtcy = jtcyArray.getJSONObject(i);
                                jtcy.put("xb_dictText", sysDictService.queryDictTextByKey("sex", jtcy.getString("xb")));
                                jtcy.put("yhzgx_dictText", sysDictService.queryDictTextByKey("yhzgx", jtcy.getString("yhzgx")));
                            }
                            jsonObject.put("jtcyList", jtcyArray);
                        }
                    }

                    if(!StringUtils.isEmpty(khhmcxx.getSsyxdy())) {
                        String s = sysDictService.queryTableDictTextByKey("v_yxdygl_ejyxdygl", "yjyxdymc||dymc", "dybh", khhmcxx.getSsyxdy());
                        jsonObject.put("ssyxdy_dictText", s);
                    }
                    //查询户主名称
                    QueryWrapper<KhglQtzrrhzxxgl> hzxx = new QueryWrapper<>();
                    hzxx.eq("hhbm", khhmcxx.getHhbm());
                    List<KhglQtzrrhzxxgl> hzxxList = khglQtzrrhzxxglService.list(hzxx);
                    if (hzxxList != null && hzxxList.size() > 0) {
                        KhglQtzrrhzxxgl hzxxgl = hzxxList.get(0);
                        jsonObject.put("hzzjhm", hzxxgl.getHzzjhm());
                        jsonObject.put("hzxm", hzxxgl.getHzxm());
                    }
                    return Result.ok(jsonObject);
                }
            }else{
                return Result.ok(false);
            }
        }catch (Exception e){
            return Result.error(e.toString());
        }
        return Result.ok(false);
    }

    @PostMapping(value = "/editHhbmByZjhmAndHhbm")
    public Result<?> editHhbmByZjhmAndHhbm(@RequestBody JSONObject jsonObject) {
        String hhbm = jsonObject.getString("hhbm");
        String yhzgx = jsonObject.getString("yhzgx");
        String id = jsonObject.getString("id");
        String hhbm_old = jsonObject.getString("hhbm_old");
        //待添加成员是否为户主
        String sfhz = jsonObject.getString("sfhz");
        if ("1".equalsIgnoreCase(sfhz)) {
            //待添加成员原先家庭中是否有家庭成员
            JSONArray jtcyList = jsonObject.getJSONArray("jtcyList");
            if (jtcyList != null && jtcyList.size() > 0) {
                //有家庭成员
                //更新家庭成员的与户主关系、是否户主
                String jtcyHzid = "";
                for (int i = 0; i < jtcyList.size(); i++) {
                    JSONObject jtcy = jtcyList.getJSONObject(i);
                    String jtcyYhzgx = jtcy.getString("xyhzgx");
                    String jtcySfhz = "2";

                    if ("1".equalsIgnoreCase(jtcyYhzgx)) {
                        jtcySfhz = "1";
                        jtcyHzid = jtcy.getString("id");
                    }
                    KhglQtzrrhmcxx khglQtzrrhmcxx = new KhglQtzrrhmcxx();
                    khglQtzrrhmcxx.setYhzgx(jtcyYhzgx);
                    khglQtzrrhmcxx.setSfhz(jtcySfhz);

                    UpdateWrapper<KhglQtzrrhmcxx> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("id", jtcy.getString("id"));
                    khglQtzrrhmcxxPadService.update(khglQtzrrhmcxx, updateWrapper);
                }
                //更新户主表中的户主信息
                QueryWrapper<KhglQtzrrhmcxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", jtcyHzid);
                KhglQtzrrhmcxx khhmcxx = khglQtzrrhmcxxPadService.getOne(queryWrapper);

                KhglQtzrrhzxxgl hzxxgl = new KhglQtzrrhzxxgl();
                QueryWrapper<KhglQtzrrhzxxgl> hzxxglQueryWrapper = new QueryWrapper<>();
                hzxxglQueryWrapper.eq("hhbm", hhbm_old);
                KhglQtzrrhzxxgl khglNhhzxxgl = khglQtzrrhzxxglService.getOne(hzxxglQueryWrapper);
                if (!StringUtils.isEmpty(khglNhhzxxgl.getSxdxzjh()) && khglNhhzxxgl.getSxdxzjh().equalsIgnoreCase(khglNhhzxxgl.getHzzjhm())) {
                    //如果授信对象是户主
                    hzxxgl.setSxdxzjh(khhmcxx.getZjhm());
                    hzxxgl.setSxdx(khhmcxx.getKhmc());
                }


                hzxxgl.setHzxm(khhmcxx.getKhmc());
                hzxxgl.setHzzjhm(khhmcxx.getZjhm());
                UpdateWrapper<KhglQtzrrhzxxgl> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("hhbm", hhbm_old);
                khglQtzrrhzxxglService.update(hzxxgl, updateWrapper);
            } else {
                //没有家庭成员，
                //更新待添加成员花名册中的户号编码、与户主关系、是否户主
                //删除户主信息表中的记录
                QueryWrapper<KhglQtzrrhzxxgl> deleteWrapper = new QueryWrapper<>();
                deleteWrapper.eq("hhbm", hhbm_old);
                khglQtzrrhzxxglService.remove(deleteWrapper);
            }
        }
        KhglQtzrrhmcxx khglQtzrrhmcxx = new KhglQtzrrhmcxx();
        khglQtzrrhmcxx.setHhbm(hhbm);
        khglQtzrrhmcxx.setYhzgx(yhzgx);
        khglQtzrrhmcxx.setSfhz("2");
        UpdateWrapper<KhglQtzrrhmcxx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        try {
            khglQtzrrhmcxx.setXgsj(new Date());
            khglQtzrrhmcxx.setXgr(sysUser.getUsername());
            khglQtzrrhmcxxPadService.update(khglQtzrrhmcxx, updateWrapper);

        } catch (Exception e) {
            log.error("添加成员失败", e);
            return Result.error("系统错误，添加成员失败！");
        }
        return Result.ok("修改成功");
    }

    @GetMapping(value = "/queryPjsxxxByHhbm")
    public Result<?> queryPjsxxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if (hhbm != null) {
                CamsZcsxQtzrrpjsxxxPad cms = new CamsZcsxQtzrrpjsxxxPad();
                QueryWrapper<CamsZcsxQtzrrpjsxxxPad> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("hhbm", hhbm);
                List<CamsZcsxQtzrrpjsxxxPad> list = camsZcsxQtzrrpjsxxxPadService.list(queryWrapper);
                if (list.size() > 0) {
                    return Result.ok(list.get(0));
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有找到数据");
    }

    @GetMapping(value = "/DeleterJtxxByHhbmAndZjhm")
    public Result<?> DeleterJtxxByHhbmAndZjhm(@Param("id") String id) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        QueryWrapper<KhglQtzrrhmcxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        KhglQtzrrhmcxx  khmcxxQuery = khglQtzrrhmcxxPadService.getOne(queryWrapper);

        try {
            //判断是否授信
            String hhbm = khmcxxQuery.getHhbm();
            String sfhz = khmcxxQuery.getSfhz();
            QueryWrapper<CamsZcsxQtzrrpjsxxxPad> pjsxxxQueryWrapper = new QueryWrapper<>();
            pjsxxxQueryWrapper.eq("hhbm", hhbm);
            pjsxxxQueryWrapper.isNotNull("zzsxed");
            CamsZcsxQtzrrpjsxxxPad nhpjsxxx = camsZcsxQtzrrpjsxxxPadService.getOne(pjsxxxQueryWrapper);
            if (nhpjsxxx != null) {
                return Result.error("当前户已评级授信，不能进行此操作");
            }
            //判断是否有家庭成员
            QueryWrapper<KhglQtzrrhmcxx> jtcyQueryWrapper = new QueryWrapper<>();
            jtcyQueryWrapper.eq("hhbm", hhbm);
            List<KhglQtzrrhmcxx> jtcyList = khglQtzrrhmcxxPadService.list(jtcyQueryWrapper);
            if (jtcyList == null && jtcyList.size() == 0) {
                return Result.error("当前户不存在其他成员，无需进行此操作");
            } else {
                //更新户号编码、与户主关系、户主表中增加记录
                //查询原户主信息
                QueryWrapper<KhglQtzrrhzxxgl> hzxxglQueryWrapper = new QueryWrapper<>();
                hzxxglQueryWrapper.eq("hhbm", hhbm);
                KhglQtzrrhzxxgl khglQtzrrhzxxgl = khglQtzrrhzxxglService.getOne(hzxxglQueryWrapper);

                String newHhbm = khmcxxQuery.getSsyxdy()+sysDictService.queryhhbm("seq_hhbm.nextval");
                UpdateWrapper<KhglQtzrrhmcxx> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", id);
                KhglQtzrrhmcxx khhmcxx = new KhglQtzrrhmcxx();
                khhmcxx.setHhbm(newHhbm);
                khhmcxx.setYhzgx("1");
                khhmcxx.setSfhz("1");
                khhmcxx.setXgsj(new Date());
                khhmcxx.setXgr(sysUser.getUsername());
                khglQtzrrhmcxxPadService.update(khhmcxx, updateWrapper);
                KhglQtzrrhzxxgl hzxxgl = new KhglQtzrrhzxxgl();
                hzxxgl.setSszh(khglQtzrrhzxxgl.getSszh());
                hzxxgl.setSsxz(khglQtzrrhzxxgl.getSsxz());
                hzxxgl.setXzc(khglQtzrrhzxxgl.getXzc());
                hzxxgl.setSsyxdy(khglQtzrrhzxxgl.getSsyxdy());
                hzxxgl.setZkhjl(khglQtzrrhzxxgl.getZkhjl());
                hzxxgl.setHhbm(newHhbm);
                hzxxgl.setHzxm(khmcxxQuery.getKhmc());
                hzxxgl.setHzzjhm(khmcxxQuery.getZjhm());
                hzxxgl.setKhlx(khglQtzrrhzxxgl.getKhlx());
                hzxxgl.setLrsj(new Date());
                hzxxgl.setLrr(sysUser.getUsername());
                khglQtzrrhzxxglService.save(hzxxgl);
            }
        } catch (Exception e) {
            log.error("移除家庭成员失败", e);
            return Result.error("系统错误，移除家庭成员失败！");
        }
        return Result.ok("删除成功");
    }

    @RequestMapping(value = "/EditJtxx", method = RequestMethod.POST)
    public Result<?> EditJtxx(@RequestBody KhglQtzrrhzxxglPage page) {
        try {
            KhglQtzrrhzxxgl khglQtzrrhzxxgl = new KhglQtzrrhzxxgl();
            QueryWrapper<KhglQtzrrhmcxx> hzgl = new QueryWrapper<>();
            if (!StringUtils.isEmpty(page.getSxdxzjh())) {
                hzgl.eq("id", page.getSxdxzjh());
                KhglQtzrrhmcxx list1 = khglQtzrrhmcxxPadService.getOne(hzgl);
                if (list1!=null){
                    page.setSxdx(list1.getKhmc());
                    page.setSxdxzjh(list1.getZjhm());
                }
            }
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            BeanUtils.copyProperties(page,khglQtzrrhzxxgl);
            khglQtzrrhzxxgl.setLrr(sysUser.getUsername());
            khglQtzrrhzxxgl.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
            khglQtzrrhzxxgl.setUpTm(DateUtil.formatDateTime("HHmmss"));
            UpdateWrapper<KhglQtzrrhzxxgl> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("hhbm", page.getHhbm());
            khglQtzrrhzxxglService.update(khglQtzrrhzxxgl,updateWrapper);
            saveYxglKhhfxxb(page.getSsyxdy(), page.getHzxm(), page.getHzzjhm());
            vKhglQtzrrhzxxglService.init(page.getHhbm());
        } catch (Exception e) {
            log.error("修改家庭信息失败", e);
            return Result.error("系统错误，修改家庭信息失败！");
        }
        return Result.ok("修改成功");
    }

	@RequestMapping(value = "/AddGrhmcxx", method = RequestMethod.POST)
	public Result<?> AddGrhmcxx(@RequestBody KhglQtzrrhmcxx jsonObject) {
		try {
		    if (jsonObject!=null) {
                QueryWrapper<KhglQtzrrhmcxx> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("id",jsonObject.getId());
                List<KhglQtzrrhmcxx> list = khglQtzrrhmcxxPadService.list(queryWrapper);
                if (list.size()==0){
                    khglQtzrrhmcxxPadService.save(jsonObject);
                }else{
                    //2020/12/14 zhouquan 由于蓝山版本点击修改个人信息 里面证件号码是脱敏状态所以做修改
                    jsonObject.setZjhm(list.get(0).getZjhm());
                    UpdateWrapper<KhglQtzrrhmcxx> khglKhhmcxxUpdateWrapper = new UpdateWrapper<>();
                    khglKhhmcxxUpdateWrapper.eq("id", jsonObject.getId());
                    khglQtzrrhmcxxPadService.update(jsonObject, khglKhhmcxxUpdateWrapper);
                }
                //同步更新户主信息表
                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                UpdateWrapper<KhglQtzrrhzxxgl> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("hhbm", jsonObject.getHhbm());
                KhglQtzrrhzxxgl khglQtzrrhzxxgl = new KhglQtzrrhzxxgl();
                khglQtzrrhzxxgl.setLrr(sysUser.getUsername());
                khglQtzrrhzxxgl.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
                khglQtzrrhzxxgl.setUpTm(DateUtil.formatDateTime("HHmmss"));
                if ("1".equalsIgnoreCase(jsonObject.getSfhz())) {
                    khglQtzrrhzxxgl.setHzxm(jsonObject.getKhmc());
                    khglQtzrrhzxxgl.setHzzjhm(jsonObject.getZjhm());
                }
                khglQtzrrhzxxglService.update(khglQtzrrhzxxgl, updateWrapper);

                saveYxglKhhfxxb(jsonObject.getSsyxdy(), jsonObject.getKhmc(), jsonObject.getZjhm());
            }else
            {
                return Result.error("修改失败");
            }
		} catch (Exception e) {
		    log.error("个人信息修改失败", e);
			return Result.error("系统错误，修改失败！");
		}
		return Result.ok("修改成功");
	}

    @RequestMapping(value = "/AddGrcjxx", method = RequestMethod.POST)
    public Result<?> AddGrcjxx(@RequestBody CamsZcsxQtzrrcjxxPad jsonObject) {
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date);
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            jsonObject.setLrr(loginUser.getUsername());

            //2020/12/14 zhouquan
            QueryWrapper<KhglQtzrrhmcxx> khhmcxxQueryWrapper = new QueryWrapper<>();
            khhmcxxQueryWrapper.eq("id",jsonObject.getId());
            KhglQtzrrhmcxx khhmcxx = khglQtzrrhmcxxPadService.getOne(khhmcxxQueryWrapper);
            jsonObject.setZjhm(khhmcxx.getZjhm());

            if (jsonObject!=null) {
                QueryWrapper<CamsZcsxQtzrrcjxxPad> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("zjhm",jsonObject.getZjhm());
                List<CamsZcsxQtzrrcjxxPad> list = camsZcsxQtzrrcjxxPadService.list(queryWrapper);
                if (list.size()==0){
                    jsonObject.setUpDt(dateStr);
                    camsZcsxQtzrrcjxxPadService.save(jsonObject);
                }else{
                    UpdateWrapper<CamsZcsxQtzrrcjxxPad> khglKhhmcxxUpdateWrapper = new UpdateWrapper<>();
                    khglKhhmcxxUpdateWrapper.eq("zjhm", jsonObject.getZjhm());
                    jsonObject.setUpDt(dateStr);
                    camsZcsxQtzrrcjxxPadService.update(jsonObject, khglKhhmcxxUpdateWrapper);
                }
                vKhglQtzrrhzxxglService.init(khhmcxx.getHhbm());
            }else
            {
                return Result.error("修改失败");
            }
        } catch (Exception e) {
            log.error("个人采集信息修改失败", e);
            return Result.error("系统错误，修改失败！");
        }
        return Result.ok("修改成功");
    }

    @Transactional
    @RequestMapping(value = "/AddFcxx", method = RequestMethod.POST)
    public Result<?> AddFcxx(@RequestBody CamsZcsxQtzrrfcxxPadReceive jsonObject) {
        try {
            List<CamsZcsxQtzrrfcxxPad> purchaseOrders = jsonObject.getCams();
            UpdateWrapper<CamsZcsxQtzrrfcxxPad> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("hhbm", jsonObject.getHhbm());
            List<CamsZcsxQtzrrfcxxPad> list = camsZcsxQtzrrfcxxPadService.list(updateWrapper);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (list!=null && list.size()>0){
                camsZcsxQtzrrfcxxPadService.remove(updateWrapper);
                for (int i = 0; i < purchaseOrders.size(); i++) {
                    if (i > 0) {
                        purchaseOrders.get(i).setHhbm(jsonObject.getHhbm());
                        purchaseOrders.get(i).setKhmc(jsonObject.getKhmc());
                        purchaseOrders.get(i).setLrsj(new Date());
                        purchaseOrders.get(i).setLrr(sysUser.getUsername());
                        purchaseOrders.get(i).setFcbm(UUID.randomUUID().toString().substring(0, 16));
                        camsZcsxQtzrrfcxxPadService.save(purchaseOrders.get(i));
                    }}
            }else{
                for (int i = 0; i < purchaseOrders.size(); i++) {
                    if (i > 0) {
                        purchaseOrders.get(i).setHhbm(jsonObject.getHhbm());
                        purchaseOrders.get(i).setKhmc(jsonObject.getKhmc());
                        purchaseOrders.get(i).setLrsj(new Date());
                        purchaseOrders.get(i).setLrr(sysUser.getUsername());
                        purchaseOrders.get(i).setFcbm(UUID.randomUUID().toString().substring(0, 16));
                        camsZcsxQtzrrfcxxPadService.save(purchaseOrders.get(i));
                    }}}
        } catch (Exception e) {
            log.error("编辑房产信息失败", e);
            return Result.error("系统错误，编辑房产信息失败！");
        }
        return Result.ok("添加成功");
    }

    @Transactional
    @RequestMapping(value = "/AddPjsxxx", method = RequestMethod.POST)
    public Result<?> AddPjsxxx(@RequestBody CamsZcsxQtzrrpjsxxxPad jsonObject) {
        try {
            //通过授信对象id 查询证件号码 update 2020/11/20 zhouquan
            QueryWrapper<KhglQtzrrhmcxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",jsonObject.getZjhm());
            KhglQtzrrhmcxx khhmcxx = khglQtzrrhmcxxPadService.getOne(queryWrapper);
            if (khhmcxx != null){
                jsonObject.setZjhm(khhmcxx.getZjhm());
                jsonObject.setKhmc(khhmcxx.getKhmc());
            }

            QueryWrapper<CamsZcsxQtzrrpjsxxxPad> pjsxxx=new QueryWrapper<>();
            pjsxxx.eq("hhbm",jsonObject.getHhbm());
            List<CamsZcsxQtzrrpjsxxxPad> list = camsZcsxQtzrrpjsxxxPadService.list(pjsxxx);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (list!=null && list.size()>0){
                UpdateWrapper<CamsZcsxQtzrrpjsxxxPad> cms = new UpdateWrapper<>();
                cms.eq("hhbm", jsonObject.getHhbm());
                jsonObject.setLrr(sysUser.getUsername());
                jsonObject.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
                jsonObject.setUpTm(DateUtil.formatDateTime("HHmmss"));
                camsZcsxQtzrrpjsxxxPadService.update(jsonObject, cms);
            }else{
                jsonObject.setLrbz("1");
                jsonObject.setLrsj(new Date());
                jsonObject.setLrr(sysUser.getUsername());
                camsZcsxQtzrrpjsxxxPadService.save(jsonObject);
            }
        } catch (Exception e) {
            log.error("编辑评级授信信息失败", e);
            return Result.error("系统错误，编辑评级授信信息失败！");
        }
        return Result.ok("添加成功");
    }

    @Transactional
    @RequestMapping(value = "/AddHzxx", method = RequestMethod.POST)
    public Result<?> AddHzxx(@RequestBody Map<String, Object> models) {
        try {
            KhglQtzrrhzxxgl khglQtzrrhzxxgl = JSON.parseObject(JSON.toJSONString(models), KhglQtzrrhzxxgl.class);
            KhglQtzrrhmcxx khhmcxx = JSON.parseObject(JSON.toJSONString(models), KhglQtzrrhmcxx.class);
          /*  QueryWrapper<KhglNhhzxxgl> hzxx = new QueryWrapper<>();
            hzxx.eq("hhbm",nhhzxxgl.getHhbm());
            List<KhglNhhzxxgl> hzxxList = khglNhhzxxglService.list(hzxx);
            if (hzxxList != null && hzxxList.size() > 0){
                return Result.error("户号编码已存在");
            }*/
            //对证件号码进行解密
            khglQtzrrhzxxgl.setHzzjhm(Base64.decodeStr(khglQtzrrhzxxgl.getHzzjhm()));
            //判断证件号码是否存在
            QueryWrapper<KhglQtzrrhmcxx> khxx = new QueryWrapper<>();
            khxx.eq("zjhm", khglQtzrrhzxxgl.getHzzjhm());
            List<KhglQtzrrhmcxx> khxxList = khglQtzrrhmcxxPadService.list(khxx);
            if (khxxList != null && khxxList.size() > 0){
                return Result.error("证件号码已存在");
            }
            String id = UUIDGenerator.generate();
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            khglQtzrrhzxxgl.setId(id);
            khglQtzrrhzxxgl.setHhbm(khglQtzrrhzxxgl.getSsyxdy()+sysDictService.queryhhbm("seq_hhbm.nextval"));
            khglQtzrrhzxxgl.setSxdx(khglQtzrrhzxxgl.getHzxm());
            khglQtzrrhzxxgl.setSxdxzjh(khglQtzrrhzxxgl.getHzzjhm());
            khglQtzrrhzxxgl.setZkhjl(sysUser.getWorkNo());
            khglQtzrrhzxxgl.setLrsj(new Date());
            khglQtzrrhzxxgl.setLrbz("1");
            khglQtzrrhzxxgl.setLrr(sysUser.getUsername());
            khglQtzrrhzxxglService.save(khglQtzrrhzxxgl);
            //同步保留花名册信息
            KhglQtzrrhmcxx khglQtzrrhmcxx = new KhglQtzrrhmcxx();
            khglQtzrrhmcxx.setId(id);
            khglQtzrrhmcxx.setSszh(khglQtzrrhzxxgl.getSszh());
            khglQtzrrhmcxx.setHhbm(khglQtzrrhzxxgl.getHhbm());
            khglQtzrrhmcxx.setKhmc(khglQtzrrhzxxgl.getHzxm());
            khglQtzrrhmcxx.setSsyxdy(khglQtzrrhzxxgl.getSsyxdy());
            khglQtzrrhmcxx.setKhlx("1");
            khglQtzrrhmcxx.setYhzgx("1");
            khglQtzrrhmcxx.setXb(khhmcxx.getXb());
            khglQtzrrhmcxx.setLxfs(khhmcxx.getLxfs());
            khglQtzrrhmcxx.setZjhm(khglQtzrrhzxxgl.getHzzjhm());
            khglQtzrrhmcxx.setNl(khhmcxx.getNl());
            khglQtzrrhmcxx.setDz(khhmcxx.getDz());
            khglQtzrrhmcxx.setSfhz("1");
            khglQtzrrhmcxx.setLrbz("1");
            khglQtzrrhmcxx.setLrsj(new Date());
            khglQtzrrhmcxx.setLrr(sysUser.getUsername());
            khglQtzrrhmcxxPadService.save(khglQtzrrhmcxx);

        } catch (Exception e) {
            log.error("添加户主信息失败", e);
            return Result.error("系统错误，添加失败！");
        }
        return Result.ok("添加成功");
    }

    @RequestMapping(value = "/AddJtxxByHhbm", method = RequestMethod.POST)
    public Result<?> AddJtxxByHhbm(@RequestBody JSONObject jsonObject) {
        KhglQtzrrhmcxx khglQtzrrhmcxx = new KhglQtzrrhmcxx();

        try {
            //查询户主信息
            QueryWrapper<KhglQtzrrhzxxgl> hzxx = new QueryWrapper<>();
            hzxx.eq("hhbm", jsonObject.getString("hhbm"));
            List<KhglQtzrrhzxxgl> hzxxList = khglQtzrrhzxxglService.list(hzxx);
            if (hzxxList != null && hzxxList.size() > 0){
                KhglQtzrrhzxxgl nhhzxxgl = hzxxList.get(0);
                khglQtzrrhmcxx.setSszh(nhhzxxgl.getSszh());
                khglQtzrrhmcxx.setSsyxdy(nhhzxxgl.getSsyxdy());
            }
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            khglQtzrrhmcxx.setHhbm(jsonObject.getString("hhbm"));
            khglQtzrrhmcxx.setKhmc(jsonObject.getString("khmc"));
            khglQtzrrhmcxx.setYhzgx(jsonObject.getString("yhzgx"));
            khglQtzrrhmcxx.setXb(jsonObject.getString("xb"));
            khglQtzrrhmcxx.setLxfs(jsonObject.getString("lxfs"));
            khglQtzrrhmcxx.setZjhm(jsonObject.getString("zjhm"));
            khglQtzrrhmcxx.setNl(jsonObject.getString("nl"));
            khglQtzrrhmcxx.setSfhz("2");
            khglQtzrrhmcxx.setKhlx("1");
            khglQtzrrhmcxx.setLrsj(new Date());
            khglQtzrrhmcxx.setLrr(sysUser.getUsername());
            khglQtzrrhmcxxPadService.save(khglQtzrrhmcxx);
        } catch (Exception e) {
            log.error("添加家庭成员信息失败", e);
            return Result.error("系统错误，添加失败！");
        }
        return Result.ok("添加成功");
    }





    @GetMapping(value = "/queryFjxxByHhbm")
    public Result<?> queryFjxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            if (hhbm != null) {
                QueryWrapper<KhglQtzrrhzzllb> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("hhbm", hhbm);
                List<KhglQtzrrhzzllb> list = khglQtzrrhzzllbService.list(queryWrapper);
                List<FjxxRecive> list1 = new ArrayList<>();
                if (list != null && list.size() > 0) {
                    return Result.ok(list);
                }
            }
        } catch (Exception e) {
            return Result.error(e.toString());
        }
        return Result.ok("没有附件数据");
    }

    /**
     * 查询业务往来信息
     */
    @GetMapping(value = "/queryYwwlxxById")
    public Result<?> queryYwwlxxById(@Param("id") String id) {
        try {
            QueryWrapper<KhglQtzrrhzxxgl> qtzrrhzxxglQueryWrapper = new QueryWrapper<>();
            qtzrrhzxxglQueryWrapper.eq("id", id);
            KhglQtzrrhzxxgl khglQtzrrhzxxgl = khglQtzrrhzxxglService.getOne(qtzrrhzxxglQueryWrapper);
            QueryWrapper<KhglYwhywwlxxPad> ywhywwlxxPadQueryWrapper = new QueryWrapper<>();
            ywhywwlxxPadQueryWrapper.eq("zjhm", khglQtzrrhzxxgl.getHzzjhm());
            List<KhglYwhywwlxxPad> ywhywwlxxPadList = khglYwhywwlxxPadMapper.list(ywhywwlxxPadQueryWrapper);
            if (ywhywwlxxPadList != null && ywhywwlxxPadList.size() > 0) {
                return Result.ok(ywhywwlxxPadList.get(0));
            }
        } catch (Exception e) {
            log.error("查询业务信息失败", e);
            return Result.error("查询业务信息失败");
        }
        return Result.ok("未查询到业务信息");
    }

    @GetMapping(value = "/queryYwxxByZjhm")
    public Result<?> queryYwxxByZjhm(@Param("id") String id) {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            if (!StringUtils.isEmpty(id)) {
                QueryWrapper<KhglQtzrrhmcxx> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("id", id);
                KhglQtzrrhmcxx khhmcxx = khglQtzrrhmcxxPadService.getOne(khhmcQueryWrapper);
                QueryWrapper<KhglYwhywwlxxPad> ywhywwlxxPadQueryWrapper = new QueryWrapper<>();
                ywhywwlxxPadQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
                List<KhglYwhywwlxxPad> cdkywxxList = khglYwhywwlxxPadMapper.list(ywhywwlxxPadQueryWrapper);
                jsonObject.put("cdkywxx", cdkywxxList);
                /*if (cdkywxxList != null && cdkywxxList.size()>0){
                    Map<String,KhglYwhywwlxxPad> map = new HashMap<>();
                    map.put("cdkywxx", cdkywxxList.get(0));
                    jsonArray.add(map);
                }*/
                QueryWrapper<KhywxxDksjmxPad> dksjmx = new QueryWrapper<>();
                dksjmx.eq("zjhm", khhmcxx.getZjhm());
                List<KhywxxDksjmxPad> dksjmxList = khywxxDksjmxPadMapper.list(dksjmx);
                for (KhywxxDksjmxPad khywxxDksjmxPad : dksjmxList) {
                    khywxxDksjmxPad.setDkxt(khywxxDksjmxPad.getDkxt() == null ? "" :  sysDictService.queryDictTextByKey("dkxt", khywxxDksjmxPad.getDkxt()));
                    khywxxDksjmxPad.setDbfs(khywxxDksjmxPad.getDbfs() == null ? "" : sysDictService.queryDictTextByKey("dbfs", khywxxDksjmxPad.getDbfs()));
                    khywxxDksjmxPad.setDkpz(khywxxDksjmxPad.getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", khywxxDksjmxPad.getDkpz()));
                    khywxxDksjmxPad.setDyzrr(khywxxDksjmxPad.getDyzrr() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", khywxxDksjmxPad.getDyzrr()));
                    if (!StringUtils.isEmpty(khywxxDksjmxPad.getKhjlbz())) {
                        khywxxDksjmxPad.setKhjlbz(sysDictService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh", khywxxDksjmxPad.getKhjlbz()));
                    }
                }
                jsonObject.put("dksjmx", dksjmxList);

                /*if (dksjmxList != null && dksjmxList.size()>0){
                    Map<String,List<KhywxxDksjmxPad>> map = new HashMap<>();
                    for (int i = 0; i < dksjmxList.size(); i++) {
                        dksjmxList.get(i).setDkxt(sysDictService.queryDictTextByKey("dkxt", dksjmxList.get(i).getDkxt()));
                        dksjmxList.get(i).setDbfs(sysDictService.queryDictTextByKey("dbfs", dksjmxList.get(i).getDbfs()));
                        dksjmxList.get(i).setDkpz(dksjmxList.get(i).getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", dksjmxList.get(i).getDkpz()));
                        dksjmxList.get(i).setDyzrr(dksjmxList.get(i).getDyzrr() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmxList.get(i).getDyzrr()));
                        if (!StringUtils.isEmpty(dksjmxList.get(i).getKhjlbz())) {
                            dksjmxList.get(i).setKhjlbz(sysDictService.queryTableDictTextByKey("HR_BAS_STAFF","ygxm","yggh", dksjmxList.get(i).getKhjlbz()));
                        }
                    }
                    map.put("dksjmx", dksjmxList);
                    jsonArray.add(map);
                }*/

                QueryWrapper<KhywxxSjyhPad> sjyh = new QueryWrapper<>();
                sjyh.eq("zjhm", khhmcxx.getZjhm());
                List<KhywxxSjyhPad> sjyhList = khywxxSjyhPadMapper.list(sjyh);
                for (KhywxxSjyhPad khywxxSjyhPad : sjyhList) {
                    khywxxSjyhPad.setOpenJgdm(khywxxSjyhPad.getOpenJgdm() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz", khywxxSjyhPad.getOpenJgdm()));
                    khywxxSjyhPad.setOpenType(khywxxSjyhPad.getOpenType() == null ? "" : sysDictService.queryDictTextByKey("sjyh_khlx", khywxxSjyhPad.getOpenType()));
                    khywxxSjyhPad.setStatus(khywxxSjyhPad.getStatus() == null ? "" : sysDictService.queryDictTextByKey("khywxx_kxhzt", khywxxSjyhPad.getStatus()));
                }
                jsonObject.put("sjyh", sjyhList);

                /*if (sjyhList != null && sjyhList.size()>0){
                    Map<String,KhywxxSjyhPad> map = new HashMap<>();
                    sjyhList.get(0).setOpenJgdm(sysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz", sjyhList.get(0).getOpenJgdm()));
                    sjyhList.get(0).setOpenType(sysDictService.queryDictTextByKey("sjyh_khlx", sjyhList.get(0).getOpenType()));
                    sjyhList.get(0).setStatus(sysDictService.queryDictTextByKey("khywxx_kxhzt", sjyhList.get(0).getStatus()));
                    jsonObject.put("sjyh", sjyhList);
                    map.put("sjyh", sjyhList.get(0));
                    jsonArray.add(map);
                }*/
                QueryWrapper<KhywxxEtc> etc = new QueryWrapper<>();
                etc.eq("zjhm", khhmcxx.getZjhm());
                List<KhywxxEtc> etcList = khywxxEtcMapper.list(etc);
                for (KhywxxEtc khywxxEtc : etcList) {
                    khywxxEtc.setOpenJgdm(khywxxEtc.getOperJgdm() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","ywjgdm",khywxxEtc.getOpenJgdm()));
                    khywxxEtc.setOperYggh(khywxxEtc.getOperYggh() == null ? "" : sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", khywxxEtc.getOperYggh()));
                    khywxxEtc.setZhlx(khywxxEtc.getZhlx() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", khywxxEtc.getZhlx()));
                    khywxxEtc.setStatus(khywxxEtc.getStatus() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", khywxxEtc.getStatus()));
                }
                jsonObject.put("etc", etcList);
               /* if (etcList != null && etcList.size()>0){
                    Map<String,KhywxxEtc> map = new HashMap<>();
                    etcList.get(0).setOpenJgdm(etcList.get(0).getOperJgdm() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",etcList.get(0).getOpenJgdm()));
                    etcList.get(0).setOperYggh(etcList.get(0).getOperYggh() == null ? "" : sysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",etcList.get(0).getOperYggh()));
                    etcList.get(0).setZhlx(etcList.get(0).getZhlx() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", etcList.get(0).getZhlx()));
                    etcList.get(0).setStatus(etcList.get(0).getStatus() == null ? "" : sysDictService.queryDictTextByKey("etc_zhlx", etcList.get(0).getStatus()));
                    map.put("etc",etcList.get(0));
                    jsonArray.add(map);
                }*/

                //信用卡
                QueryWrapper<Xyk> xykQueryWrapper= new QueryWrapper<>();
                xykQueryWrapper.eq("zjhm",khhmcxx.getZjhm());
                List<Xyk> xykList = xykService.list(xykQueryWrapper);
                jsonObject.put("xykList", xykList);
                return Result.ok(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }

    @GetMapping(value = "/queryJtxxByHhbm")
    public Result<?> queryJtxxByHhbm(@Param("hhbm") String hhbm) {
        try {
            QueryWrapper<KhglQtzrrhmcxx> khhmcQueryWrapper = new QueryWrapper<>();
            khhmcQueryWrapper.eq("hhbm", hhbm);
            khhmcQueryWrapper.orderByAsc("yhzgx");
            List<KhglQtzrrhmcxx> list = khglQtzrrhmcxxPadService.list(khhmcQueryWrapper);
            List<QtzrrJtcyxx> nhJtcyxx = new ArrayList<QtzrrJtcyxx>();
            for (int i = 0; i < list.size(); i++) {
                QtzrrJtcyxx nhJtcyxx1 = new QtzrrJtcyxx();
                BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
                String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
                nhJtcyxx1.setYhzgx(yhzgx);
                String xb =nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
                nhJtcyxx1.setXb(xb);
                nhJtcyxx.add(nhJtcyxx1);
                List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
                if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
                    if (nhJtcyxx.size() > 0) {
                        ywhywwlxxes.get(0).setId(list.get(i).getId());
                        BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
                    }
                }
            }
            if (list != null && list.size() > 0) {
                return Result.ok(nhJtcyxx);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }

    /**
     * 添加
     *
     * @param khglQtzrrhzxxgl
     * @return
     */
    @AutoLog(value = "1-添加")
    @ApiOperation(value = "1-添加", notes = "1-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody KhglQtzrrhzxxgl khglQtzrrhzxxgl) {
        khglQtzrrhzxxglService.save(khglQtzrrhzxxgl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khglQtzrrhzxxgl
     * @return
     */
    @AutoLog(value = "1-编辑")
    @ApiOperation(value = "1-编辑", notes = "1-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody KhglQtzrrhzxxgl khglQtzrrhzxxgl) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        khglQtzrrhzxxgl.setUpDt(DateUtil.formatDateTime("yyyyMMdd"));
        khglQtzrrhzxxgl.setUpTm(DateUtil.formatDateTime("HHmmss"));
        khglQtzrrhzxxglService.updateById(khglQtzrrhzxxgl);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1-通过id删除")
    @ApiOperation(value = "1-通过id删除", notes = "1-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khglQtzrrhzxxglService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "1-批量删除")
    @ApiOperation(value = "1-批量删除", notes = "1-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        khglQtzrrhzxxglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "1-通过id查询")
    @ApiOperation(value = "1-通过id查询", notes = "1-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        KhglQtzrrhzxxgl khglQtzrrhzxxgl = khglQtzrrhzxxglService.getById(id);
        return Result.ok(khglQtzrrhzxxgl);
    }

    /**
     * 查询我的授信信息
     * @param nhpjsxxx
     * @return
     */
    @GetMapping(value = "/queryNhsxxxList")
    public Result<?> queryNhsxxxList(CamsZcsxQtzrrpjsxxxPad nhpjsxxx,
                                     @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                     @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                     HttpServletRequest req) {
        QueryWrapper<CamsZcsxQtzrrpjsxxxPad> queryWrapper = QueryGenerator.initQueryWrapper(nhpjsxxx, req.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        queryWrapper.eq("lrr", sysUser.getUsername());
        queryWrapper.orderByDesc("up_dt");
        Page<CamsZcsxQtzrrpjsxxxPad> page = new Page<CamsZcsxQtzrrpjsxxxPad>(pageNo, pageSize);
        IPage<CamsZcsxQtzrrpjsxxxPad> pageList = camsZcsxQtzrrpjsxxxPadService.page(page, queryWrapper);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(pageList).toString());
        JSONArray records = jsonObject.getJSONArray("records");
        if (records != null && records.size() > 0) {
            for (int i = 0; i < records.size(); i++) {
                JSONObject sxxx = records.getJSONObject(i);
                //查询对应的联系方式
                QueryWrapper<KhglQtzrrhmcxx> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("zjhm", sxxx.getString("zjhm"));
                List<KhglQtzrrhmcxx> khhmcxxList = khglQtzrrhmcxxPadService.list(khhmcQueryWrapper);
                if (khhmcxxList != null && khhmcxxList.size() > 0) {
                    String lxfs = khhmcxxList.get(0).getLxfs();
                    sxxx.put("lxfs", lxfs);
                }
            }
        }
        return Result.ok(jsonObject);
    }

    /**
     * 保存回访信息
     */
    protected void saveYxglKhhfxxb(String yxdy, String khmc, String zjhm) {
        YxglQtzrrhfxxb khhfxxb = new YxglQtzrrhfxxb();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(sysUser.getWorkNo());
        if (vhrbasstaffpost != null) {
            khhfxxb.setZzbz(vhrbasstaffpost.getZzbz());
            khhfxxb.setYggh(vhrbasstaffpost.getYggh());
            khhfxxb.setKhjlbh(vhrbasstaffpost.getKhjlbz());
        }
        khhfxxb.setYxdy(yxdy);
        khhfxxb.setKhmc(khmc);
        khhfxxb.setZjhm(zjhm);
        khhfxxb.setHfrq(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd"), "yyyy-MM-dd"));
        khhfxxb.setSjly("2");
        khhfxxb.setLrr(sysUser.getUsername());
        yxglQtzrrhfxxbService.save(khhfxxb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param khglQtzrrhzxxgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, KhglQtzrrhzxxgl khglQtzrrhzxxgl) {
        return super.exportXls(request, khglQtzrrhzxxgl, KhglQtzrrhzxxgl.class, "1");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, KhglQtzrrhzxxgl.class);
    }

    /**
     * 获取户号编码
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryHhbm",method = RequestMethod.GET)
    public Result<?> hhbm (@RequestParam(name="id",required = true) String id){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("hhbm",sysDictService.queryhhbm("seq_hhbm.nextval"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return	Result.ok(jsonObject);
    }

    /**
     * 修改戶主
     *
     * @param jsonArray
     * @return
     */
    @AutoLog(value = "1-修改戶主")
    @ApiOperation(value = "1-修改戶主", notes = "1-修改戶主")
    @RequestMapping(value = "/editHhxx",method = RequestMethod.POST)
    public Result<?> editHhxx(@RequestBody JSONArray jsonArray) {
        List<KhglQtzrrhmcxx> khglQtzrrhmcxxList = JSONArray.parseArray(JSON.toJSONString(jsonArray),KhglQtzrrhmcxx.class);
        for (KhglQtzrrhmcxx khglQtzrrhmcxx : khglQtzrrhmcxxList) {
            KhglQtzrrhmcxx khhmcxx1 = new KhglQtzrrhmcxx();
            UpdateWrapper<KhglQtzrrhmcxx> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("hhbm",khglQtzrrhmcxx.getHhbm()).eq("id",khglQtzrrhmcxx.getId());
            //如果前台设置了与户主关系为户主则设置户主
            if (khglQtzrrhmcxx.getYhzgx().equalsIgnoreCase("1")){
                khhmcxx1.setYhzgx(khglQtzrrhmcxx.getYhzgx());
                khhmcxx1.setSfhz("1");
                khglQtzrrhmcxxPadService.update(khhmcxx1,updateWrapper);

                //由于前台传过来的证件号码属于脱敏状态特需根据id查询证件号
                QueryWrapper<KhglQtzrrhmcxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",khglQtzrrhmcxx.getId());
                KhglQtzrrhmcxx qtzrrhmcxx = khglQtzrrhmcxxPadService.getOne(queryWrapper);

                //修改户主表信息
                KhglQtzrrhzxxgl khglQtzrrhzxxgl = new KhglQtzrrhzxxgl();
                khglQtzrrhzxxgl.setHzzjhm(qtzrrhmcxx.getZjhm());
                khglQtzrrhzxxgl.setHzxm(qtzrrhmcxx.getKhmc());
                UpdateWrapper<KhglQtzrrhzxxgl> qtzrrhzxxglUpdateWrapper = new UpdateWrapper<KhglQtzrrhzxxgl>();
                qtzrrhzxxglUpdateWrapper.eq("hhbm", khglQtzrrhmcxx.getHhbm());
                khglQtzrrhzxxglService.update(khglQtzrrhzxxgl,qtzrrhzxxglUpdateWrapper);
            }else {
                khhmcxx1.setYhzgx(khglQtzrrhmcxx.getYhzgx());
                khhmcxx1.setSfhz("2");
                khglQtzrrhmcxxPadService.update(khhmcxx1,updateWrapper);
            }

        }
        return Result.ok("修改成功！");
    }


    @GetMapping(value = "/queryJtcyxx")
    public Result<?> queryHhbm(@Param("hhbm") String hhbm) {
        try {
            QueryWrapper<KhglQtzrrhmcxx> khhmcQueryWrapper = new QueryWrapper<>();
            khhmcQueryWrapper.eq("hhbm", hhbm);
            khhmcQueryWrapper.orderByAsc("yhzgx");
            List<KhglQtzrrhmcxx> list = khglQtzrrhmcxxPadService.list(khhmcQueryWrapper);
            if (list != null && list.size() > 0) {
                return Result.ok(list);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }


}
