package org.cmms.modules.pad.shxxgl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.utils.AreaUtil;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khgl.sh.entity.Dksjmx;
import org.cmms.modules.khgl.sh.entity.Etc;
import org.cmms.modules.khgl.sh.entity.ShglYwhywwlxx;
import org.cmms.modules.khgl.sh.entity.Sjyh;
import org.cmms.modules.khgl.sh.service.*;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;
import org.cmms.modules.khxxgl.khflgl.shxq.service.IShxqService;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.pad.nhxxgl.entity.*;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.cmms.modules.pad.nhxxgl.service.*;
import org.cmms.modules.pad.shxxgl.entity.*;
import org.cmms.modules.pad.shxxgl.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.qtsjdrjk.posjsh.entity.ShywxxPosjsh;
import org.cmms.modules.qtsjdrjk.posjsh.service.IShywxxPosjshService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysRole;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysUserRoleService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.yxdygl.pqzrrgl.service.ITjfxcsszService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 商户信息管理
 * @Author: jeecg-boot
 * @Date: 2020-08-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "商户信息管理")
@RestController
@RequestMapping("/khgl/shxxgl")
public class KhglShxxglController extends JeecgController<KhglShhmcxx, IKhglShhmcxxService> {
    @Autowired
    private IKhglShhmcxxService khglShhmcxxService;
    @Autowired
    private IVKhglShxxglService vKhglShxxglService;
    @Autowired
    private ICamsZcsxShcjxxService camsZcsxShcjxxService;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private ICamsJbxxShzllbService camsJbxxShzllbService;
    @Autowired
    private ICamsZcsxShfcxxService camsZcsxShfcxxService;
    @Autowired
    private ICamsZcsxShpjsxxxService camsZcsxShpjsxxxService;
    @Autowired
    private IKhglShglrxxService khglShglrxxService;

    @Autowired
    private IYwhywwlxxService ywhywwlxxService;
    @Autowired
    private AreaUtil areaUtil;
    @Autowired
    private ITjfxcsszService tjfxcsszService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IKhywxxDksjmxPadService khywxxDksjmxPadService;
    @Autowired
    private IKhywxxSjyhPadService khywxxSjyhPadService;
    @Autowired
    private IKhglYwhywwlxxPadService khglYwhywwlxxPadService;
    @Autowired
    private IDksjmxService dksjmxService;
    @Autowired
    private IEtcService etcService;
    @Autowired
    private ISjyhService sjyhService;
    @Autowired
    private IFxezhService fxezhService;
    @Autowired
    private IXykService xykService;
    @Autowired
    private IShglYwhywwlxxService iShglYwhywwlxxService;
    @Autowired
    private ITjfxZhbyService tjfxZhbyService;
    @Autowired
    private IXjlghjcService xjlghjcService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;

    @Autowired
    private IVhrbasstaffpostService vhrbasstaffpostService;
    @Autowired
    private IShhfxxbService shhfxxbService;

    @Autowired
    private IShxqService shcjxxService;

    @Autowired
    private IKhjbzlService khjbzlService;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IShywxxPosjshService shywxxPosjshService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ICamsZcsxShpjsxxxZcqkService camsZcsxShpjsxxxZcqkService;
    @Autowired
    private ICamsZcsxShpjsxxxFzqkService camsZcsxShpjsxxxFzqkService;

    /**
     * 分页列表查询
     *
     * @param vKhglShxxgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "商户信息管理-分页列表查询")
    @ApiOperation(value = "商户信息管理-分页列表查询", notes = "商户信息管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(VKhglShxxgl vKhglShxxgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   String startDate, String endDate,
                                   HttpServletRequest req) {
        QueryWrapper<VKhglShxxgl> queryWrapper = QueryGenerator.initQueryWrapper(vKhglShxxgl, req.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            try {
                queryWrapper.between("clrq", sdf.parse(startDate), sdf.parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //由于有的用户分配的权限过多，使用in会报错，暂改成直接通过语句查询
        if (!sysUser.getUsername().equals("admin")) {
            //in  用 list有个数限制问题， 此处改为inSql
            String sqlSswg = "select  menu_id from YXDYGL_PQQXGL t where khjl =" + "'" + sysUser.getWorkNo() + "'";
//            queryWrapper.and(i -> i.inSql("ssyxdy", sqlSswg).or().eq("zkhjl", sysUser.getWorkNo()));
            queryWrapper.and(i -> i.inSql("ssyxdy", sqlSswg));
        }

        Page<VKhglShxxgl> page = new Page<VKhglShxxgl>(pageNo, pageSize);
        IPage<VKhglShxxgl> pageList = vKhglShxxglService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 通过id查询
     *
     * @param shid
     * @return
     */
    @AutoLog(value = "商户信息管理-通过id查询")
    @ApiOperation(value = "商户信息管理-通过id查询", notes = "商户信息管理-通过id查询")
    @GetMapping(value = "/queryShxxById")
    public Result<?> queryShxxById(@RequestParam(name = "shid", required = true) String shid) {
        try {
            //查询商户基本信息
            Shxq khglShhmcxx = shcjxxService.getById(shid);
            JSONObject shhmcJsonObject = JSONObject.parseObject(JSONObject.toJSON(khglShhmcxx).toString());
            shhmcJsonObject.put("shid", shid);
            shhmcJsonObject.put("frzjhmflag", khglShhmcxx.getFrzjhm());
            //查询商户采集信息
            if (StringUtils.isNotEmpty(khglShhmcxx.getSszh())) {
                HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(khglShhmcxx.getSszh());
                if (hrBasOrganization != null) {
                    shhmcJsonObject.put("sszh_dictText", hrBasOrganization.getZzjc());
                }
            }
            //如果授信对象为空页面授信对象下拉则默认展示商户名称
            if (khglShhmcxx != null) {
                if (khglShhmcxx.getSxdxzjh() == null) {
                    shhmcJsonObject.put("sxdxzjhflag", khglShhmcxx.getTyshxydm());
                } else {
                    shhmcJsonObject.put("sxdxzjhflag", khglShhmcxx.getSxdxzjh());
                }
            }
            return Result.ok(shhmcJsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询商户信息错误！", e);
            return Result.error("查询商户信息错误！");
        }

    }

    /**
     * 获取房产信息
     *
     * @param shid
     * @return
     */
    @GetMapping(value = "/queryFcxxByShid")
    public Result<?> queryFcxxByShid(@Param("shid") String shid) {
        try {
            if (!StringUtils.isEmpty(shid)) {
                List<CamsZcsxShfcxx> list = camsZcsxShfcxxService.getByShid(shid);
                if (list != null && list.size() > 0) {
                    return Result.ok(list);
                }
            } else {
                return Result.error("请求参数错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询房产信息错误！", e);
            return Result.error("查询房产信息错误！");
        }
        return Result.ok("没有房产数据");
    }

    /**
     * 获取评级授信信息
     *
     * @param shid
     * @return
     */
    @GetMapping(value = "/queryPjsxxxByShid")
    public Result<?> queryPjsxxxByShid(@Param("shid") String shid) {
        try {
            if (!StringUtils.isEmpty(shid)) {
                CamsZcsxShpjsxxx shpjsxxx = camsZcsxShpjsxxxService.getByShid(shid);
                if (shpjsxxx != null) {
                    JSONObject shpjsxxxJo = JSONObject.parseObject(JSONObject.toJSON(shpjsxxx).toString());
                    //获取资产信息
                    JSONArray shzcxxJa = new JSONArray();
                    //资产情况
                    JSONObject dcJo = new JSONObject();
                    dcJo.put("zclx", "地产");
                    dcJo.put("zcsl", shpjsxxx.getDcsl());
                    dcJo.put("zcjz", shpjsxxx.getDcjz());
                    dcJo.put("zcsm", shpjsxxx.getDcxqsm());
                    JSONObject jtgjJo = new JSONObject();
                    jtgjJo.put("zclx", "交通工具");
                    jtgjJo.put("zcsl", shpjsxxx.getJtgjsl());
                    jtgjJo.put("zcjz", shpjsxxx.getJtgjjz());
                    jtgjJo.put("zcsm", shpjsxxx.getJtgjxqsm());
                    JSONObject ckJo = new JSONObject();
                    ckJo.put("zclx", "存款");
                    ckJo.put("zcsl", shpjsxxx.getCksl());
                    ckJo.put("zcjz", shpjsxxx.getCkjz());
                    ckJo.put("zcsm", shpjsxxx.getCkxqsm());
                    JSONObject yjdzJo = new JSONObject();
                    yjdzJo.put("zclx", "有价单证");
                    yjdzJo.put("zcsl", shpjsxxx.getYjdzsl());
                    yjdzJo.put("zcjz", shpjsxxx.getYjdzjz());
                    yjdzJo.put("zcsm", shpjsxxx.getYjdzxqsm());
                    JSONObject gqJo = new JSONObject();
                    gqJo.put("zclx", "股权");
                    gqJo.put("zcsl", shpjsxxx.getGqsl());
                    gqJo.put("zcjz", shpjsxxx.getGqjz());
                    gqJo.put("zcsm", shpjsxxx.getGqxqsm());
                    JSONObject qtzcJo = new JSONObject();
                    qtzcJo.put("zclx", "其他资产");
                    qtzcJo.put("zcsl", shpjsxxx.getQtzcsl());
                    qtzcJo.put("zcjz", shpjsxxx.getQtzcjz());
                    qtzcJo.put("zcsm", shpjsxxx.getQtzcxqsm());
                    JSONObject nzsrJo = new JSONObject();
                    nzsrJo.put("zclx", "年总收入");
                    nzsrJo.put("zcsl", shpjsxxx.getNzsrsl());
                    nzsrJo.put("zcjz", shpjsxxx.getNzsrjz());
                    nzsrJo.put("zcsm", shpjsxxx.getNzsrxqsm());
                    shzcxxJa.add(dcJo);
                    shzcxxJa.add(jtgjJo);
                    shzcxxJa.add(ckJo);
                    shzcxxJa.add(yjdzJo);
                    shzcxxJa.add(gqJo);
                    shzcxxJa.add(qtzcJo);
                    shzcxxJa.add(nzsrJo);
                    //负债情况
                    JSONArray shfzxxJa = new JSONArray();
                    JSONObject bxtJo = new JSONObject();
                    bxtJo.put("jkfs", "本系统");
                    bxtJo.put("zqr", shpjsxxx.getBxtjkzqr());
                    bxtJo.put("jkje", shpjsxxx.getBxtjksl());
                    bxtJo.put("jksm", shpjsxxx.getBxtjkxqsm());
                    JSONObject thJo = new JSONObject();
                    thJo.put("jkfs", "他行");
                    thJo.put("zqr", shpjsxxx.getThjkzqr());
                    thJo.put("jkje", shpjsxxx.getThjksl());
                    thJo.put("jksm", shpjsxxx.getThjkxqsm());
                    JSONObject xykJo = new JSONObject();
                    xykJo.put("jkfs", "信用卡");
                    xykJo.put("zqr", shpjsxxx.getXykzqr());
                    xykJo.put("jkje", shpjsxxx.getXyksl());
                    xykJo.put("jksm", shpjsxxx.getXykxqsm());
                    JSONObject qtfzJo = new JSONObject();
                    qtfzJo.put("jkfs", "其他负债");
                    qtfzJo.put("zqr", shpjsxxx.getQtfzzqr());
                    qtfzJo.put("jkje", shpjsxxx.getQtfzsl());
                    qtfzJo.put("jksm", shpjsxxx.getQtfzxqsm());
                    JSONObject jtnkzJo = new JSONObject();
                    jtnkzJo.put("jkfs", "家庭年开支");
                    jtnkzJo.put("zqr", shpjsxxx.getJtnkzzqr());
                    jtnkzJo.put("jkje", shpjsxxx.getJtnkzsl());
                    jtnkzJo.put("jksm", shpjsxxx.getJtnkzxqsm());

                    shfzxxJa.add(bxtJo);
                    shfzxxJa.add(thJo);
                    shfzxxJa.add(xykJo);
                    shfzxxJa.add(qtfzJo);
                    shfzxxJa.add(jtnkzJo);

                    shpjsxxxJo.put("shzcxx", shzcxxJa);
                    shpjsxxxJo.put("shfzxx", shfzxxJa);
                    return Result.ok(shpjsxxxJo);
                }
            } else {
                return Result.error("请求参数错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询评级授信信息错误！", e);
            return Result.error("查询评级授信信息错误！");
        }
        return Result.ok("没有评级授信数据");
    }

    /**
     * 获取评级授信信息-永兴
     *
     * @param shid
     * @return
     */
    @GetMapping(value = "/queryPjsxxxByShidYx")
    public Result<?> queryPjsxxxByShidYx(@Param("shid") String shid) {
        try {
            if (!StringUtils.isEmpty(shid)) {
                //获取商户资产情况
                QueryWrapper<CamsZcsxShpjsxxxZcqk> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("shid",shid);
                queryWrapper.orderByAsc("px");
                List<CamsZcsxShpjsxxxZcqk> zcqkList=camsZcsxShpjsxxxZcqkService.list(queryWrapper);

                //获取商户负债情况
                QueryWrapper<CamsZcsxShpjsxxxFzqk> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("shid",shid);
                queryWrapper.orderByAsc("px");
                List<CamsZcsxShpjsxxxFzqk> fzqkList=camsZcsxShpjsxxxFzqkService.list(queryWrapper1);

                //获取商户评级授信信息
                QueryWrapper<CamsZcsxShpjsxxx> queryWrapper2=new QueryWrapper<>();
                queryWrapper2.eq("shid",shid);
                CamsZcsxShpjsxxx camsZcsxShpjsxxx=camsZcsxShpjsxxxService.getOne(queryWrapper2,false);

                Map resultMap=new HashMap();
                resultMap.put("zcqk",zcqkList);
                resultMap.put("fzqk",fzqkList);
                resultMap.put("shpjxx",camsZcsxShpjsxxx);
                return Result.ok(resultMap);
            } else {
                return Result.error("请求参数错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询评级授信资产信息错误！", e);
            return Result.error("查询评级授信资产信息错误！");
        }
    }

    /**
     * 永兴-查询当前登录的客户经理能否编辑商户信息
     *
     * @return
     */
    @GetMapping("/enableEditShxx")
    public Result<?> enableEditShxx(@RequestParam("shid") String shid) throws ParseException {
        if (StringUtils.isNotBlank(shid)) {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            Shxq shxq = shcjxxService.getById(shid);

            if (shxq != null && StringUtils.isNotBlank(shxq.getCjr()) && shxq.getCjsj() != null) {
                Date date1=DateUtil.string2Date(DateUtil.addDayOther(shxq.getCjsj(), 30,"yyyy-MM-dd"), "yyyy-MM-dd");
                Date date2=DateUtil.string2Date(DateUtil.format(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd");
                if (date1.compareTo(date2) >0){
                    if (!sysUser.getWorkNo().equals(shxq.getCjr())) {
                        return Result.ok(true);
                    }
                }
            }
            return Result.ok(false);
        }
        return Result.ok(false);
    }


    /**
     * 修改商户信息
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/EditShxx", method = RequestMethod.POST)
    public Result<?> EditJtxx(@RequestBody JSONObject jsonObject) {
        try {
            Shxq khglShhmcxx = new Shxq();
            khglShhmcxx = JSONObject.toJavaObject(jsonObject, Shxq.class);
            Shxq check = shcjxxService.getById(khglShhmcxx.getId());
            if (check == null) {
                return Result.error("请求参数错误！");
            }
            Integer tlsj = check.getTlsj();
            if (tlsj == null) {
                tlsj = 0;
            }
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

            //更新商户信息
            if (khglShhmcxx.getTlsj() != null) {
                khglShhmcxx.setTlsj(khglShhmcxx.getTlsj() + tlsj);
            } else {
                khglShhmcxx.setTlsj(tlsj);
            }
            //永兴更新采集人和采集时间(一个月内只可以一个采集人编辑商户数据)
            if (getRedisQydm().equals(QybmEnum.YONGXING.getQybm())) {
                if (StringUtils.isNotBlank(khglShhmcxx.getCjr()) && khglShhmcxx.getCjsj() != null) {
                    Date date1=DateUtil.string2Date(DateUtil.addDayOther(khglShhmcxx.getCjsj(), 30,"yyyy-MM-dd"), "yyyy-MM-dd");
                    Date date2=DateUtil.string2Date(DateUtil.format(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd");
                    if (date1.compareTo(date2)<0){
                        khglShhmcxx.setCjr(sysUser.getWorkNo());
                        khglShhmcxx.setCjsj(new Date());
                    }
                } else {
                    khglShhmcxx.setCjr(sysUser.getWorkNo());
                    khglShhmcxx.setCjsj(new Date());
                }
            }
            shcjxxService.updateById(khglShhmcxx);

            saveYxglShhfxxb(khglShhmcxx);

            List<KhglShglrxx> gxrxxList = JSONArray.parseArray(JSON.toJSONString(jsonObject.getJSONArray("gxrxxList")), KhglShglrxx.class);
            if (CollUtil.isNotEmpty(gxrxxList)) {
                for (int i = 0; i < gxrxxList.size(); i++) {
                    KhglShglrxx khglShglrxx = gxrxxList.get(i);
                    UpdateWrapper<KhglShglrxx> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("id", khglShglrxx.getId());
                    khglShglrxxService.update(khglShglrxx, updateWrapper);
                }
            }
            //提取是否有效走访
            khglShhmcxxService.init(khglShhmcxx.getId(), sysUser.getWorkNo(), sysUser.getUsername());

//	 		saveYxglKhhfxxb(page.getSsyxdy(), page.getHzxm(), page.getHzzjhm());
        } catch (Exception e) {
            log.error("修改商户信息失败", e);
            return Result.error("系统错误，修改商户信息失败！");
        }
        return Result.ok("修改成功");
    }

    /**
     * 保存回访信息
     */
    protected void saveYxglShhfxxb(Shxq khglShhmcxx) {
        Shhfxxb shhfxxb = new Shhfxxb();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(sysUser.getWorkNo());
        if (vhrbasstaffpost != null) {
            shhfxxb.setZzbz(vhrbasstaffpost.getZzbz());
            shhfxxb.setYggh(vhrbasstaffpost.getYggh());
            shhfxxb.setKhjlbh(vhrbasstaffpost.getKhjlbz());
        }
        shhfxxb.setId(UUID.randomUUID().toString().substring(0, 32));
        shhfxxb.setShid(khglShhmcxx.getId());
        shhfxxb.setYxdy(khglShhmcxx.getWgbh());
        shhfxxb.setKhmc(khglShhmcxx.getShmc());
        shhfxxb.setZjhm(khglShhmcxx.getTyshxydm());
        shhfxxb.setZhpfr(khglShhmcxx.getZhpfr());
        shhfxxb.setHfrq(DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd"), "yyyy-MM-dd"));
        shhfxxb.setSjly("2");
        shhfxxb.setLrr(sysUser.getUsername());
        shhfxxbService.save(shhfxxb);
    }

    @Transactional
    @RequestMapping(value = "/AddFcxx", method = RequestMethod.POST)
    public Result<?> AddFcxx(@RequestBody List<CamsZcsxShfcxx> shfcxxList) {
        try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (shfcxxList != null && shfcxxList.size() > 0) {
                UpdateWrapper<CamsZcsxShfcxx> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("shid", shfcxxList.get(0).getShid());
                //先删除数据
                camsZcsxShfcxxService.remove(updateWrapper);
                for (int i = 0; i < shfcxxList.size(); i++) {
                    shfcxxList.get(i).setLrsj(new Date());
                    shfcxxList.get(i).setLrr(sysUser.getUsername());
                    shfcxxList.get(i).setFcbm(UUID.randomUUID().toString().substring(0, 32));
                }
                camsZcsxShfcxxService.saveBatch(shfcxxList);
            }
        } catch (Exception e) {
            log.error("编辑房产信息失败", e);
            return Result.error("系统错误，编辑房产信息失败！");
        }
        return Result.ok("添加成功");
    }

    @Transactional
    @RequestMapping(value = "/AddPjsxxx", method = RequestMethod.POST)
    public Result<?> AddPjsxxx(@RequestBody CamsZcsxShpjsxxx jsonObject) {
        try {
            QueryWrapper<CamsZcsxShpjsxxx> pjsxxx = new QueryWrapper<>();
            pjsxxx.eq("shid", jsonObject.getShid());
            List<CamsZcsxShpjsxxx> list = camsZcsxShpjsxxxService.list(pjsxxx);
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (list != null && list.size() > 0) {
                UpdateWrapper<CamsZcsxShpjsxxx> update = new UpdateWrapper<>();
                update.eq("shid", jsonObject.getShid());
                jsonObject.setXgr(sysUser.getUsername());
                jsonObject.setXgsj(new Date());
                camsZcsxShpjsxxxService.update(jsonObject, update);
            } else {
                Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(sysUser.getWorkNo());
                if (vhrbasstaffpost != null) {
                    jsonObject.setSxdxsszh(vhrbasstaffpost.getZzbz());
                }
                jsonObject.setLrbz("1");
                jsonObject.setLrr(sysUser.getUsername());
                jsonObject.setLrsj(new Date());
                jsonObject.setXgr(null);
                jsonObject.setXgsj(null);
                camsZcsxShpjsxxxService.save(jsonObject);
            }
        } catch (Exception e) {
            log.error("编辑评级授信信息失败", e);
            return Result.error("系统错误，编辑评级授信信息失败！");
        }
        return Result.ok("添加成功");
    }

    @Transactional
    @RequestMapping(value = "/AddPjsxxxYx", method = RequestMethod.POST)
    public Result<?> AddPjsxxxYx(@RequestBody ShpjxxSaveVo jsonObject) {
        try {
            String shid=jsonObject.getShid();
            if (StringUtils.isNotBlank(shid)){
                //添加资产负债信息
                List<CamsZcsxShpjsxxxZcqk> zcqkList=jsonObject.getZcqkList();
                List<CamsZcsxShpjsxxxFzqk> fzqkList=jsonObject.getFzqkList();
                //先删
                QueryWrapper<CamsZcsxShpjsxxxZcqk> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("shid",shid);
                camsZcsxShpjsxxxZcqkService.remove(queryWrapper1);
                QueryWrapper<CamsZcsxShpjsxxxFzqk> queryWrapper2=new QueryWrapper<>();
                queryWrapper2.eq("shid",shid);
                camsZcsxShpjsxxxFzqkService.remove(queryWrapper2);
                //后加
                zcqkList.forEach(e->e.setShid(shid));
                fzqkList.forEach(e->e.setShid(shid));
                camsZcsxShpjsxxxZcqkService.saveBatch(zcqkList);
                camsZcsxShpjsxxxFzqkService.saveBatch(fzqkList);

                //添加评级授信信息
                QueryWrapper<CamsZcsxShpjsxxx> pjsxxx = new QueryWrapper<>();
                pjsxxx.eq("shid", jsonObject.getShid());
                List<CamsZcsxShpjsxxx> list = camsZcsxShpjsxxxService.list(pjsxxx);
                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                if (list != null && list.size() > 0) {
                    UpdateWrapper<CamsZcsxShpjsxxx> update = new UpdateWrapper<>();
                    update.eq("shid", jsonObject.getShid());
                    jsonObject.getCamsZcsxShpjsxxx().setXgr(sysUser.getUsername());
                    jsonObject.getCamsZcsxShpjsxxx().setXgsj(new Date());
                    camsZcsxShpjsxxxService.update(jsonObject.getCamsZcsxShpjsxxx(), update);
                } else {
                    Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(sysUser.getWorkNo());
                    if (vhrbasstaffpost != null) {
                        jsonObject.getCamsZcsxShpjsxxx().setSxdxsszh(vhrbasstaffpost.getZzbz());
                    }
                    jsonObject.getCamsZcsxShpjsxxx().setLrbz("1");
                    jsonObject.getCamsZcsxShpjsxxx().setLrr(sysUser.getUsername());
                    jsonObject.getCamsZcsxShpjsxxx().setLrsj(new Date());
                    jsonObject.getCamsZcsxShpjsxxx().setXgr(null);
                    jsonObject.getCamsZcsxShpjsxxx().setXgsj(null);
                    camsZcsxShpjsxxxService.save(jsonObject.getCamsZcsxShpjsxxx());
                }
            }else {
                return Result.error("参数错误！");
            }
        } catch (Exception e) {
            log.error("编辑评级授信信息失败", e);
            return Result.error("系统错误，编辑评级授信信息失败！");
        }
        return Result.ok("添加成功");
    }

    /**
     * 添加商户信息
     *
     * @param khglShhmcxx
     * @return
     */
    @RequestMapping(value = "/AddShxx", method = RequestMethod.POST)
    public Result<?> AddShxx(@RequestBody Shxq khglShhmcxx) {
        try {
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String ygjgdm = hrBasOrganizationService.getYwjgdmByZzbz(khglShhmcxx.getSszh());
            khglShhmcxx.setJgdm(ygjgdm == null ? "" : ygjgdm);
            QueryWrapper<Shxq> shhmcxxQueryWrapper = new QueryWrapper<>();
            shhmcxxQueryWrapper.eq("tyshxydm", khglShhmcxx.getTyshxydm());
            List<Shxq> shhmcxxList = shcjxxService.list(shhmcxxQueryWrapper);
            if (shhmcxxList != null && shhmcxxList.size() > 0) {
                return Result.error("商户信息已存在");
            }
            shcjxxService.save(khglShhmcxx);
            QueryWrapper<Khjbzl> queryWrapper = new QueryWrapper();
            queryWrapper.eq("zjhm", khglShhmcxx.getTyshxydm());
            Khjbzl khjbzl = khjbzlService.getOne(queryWrapper);
            if (khjbzl != null) {
                if (khjbzl.getKhxz() == null || khjbzl.getKhxz().isEmpty()) {
                    khjbzl.setKhxz("2");
                } else {
                    Boolean sfysh = false;
                    String[] split = khjbzl.getKhxz().split(",");
                    for (String khxz : split) {
                        if (khxz.equals("2")) {
                            sfysh = true;
                        }
                        ;
                    }
                    if (!sfysh) {
                        khjbzl.setKhxz(khjbzl.getKhxz() + ",2");
                    }
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getWgbh())) {
                    khjbzl.setWgbh(khglShhmcxx.getWgbh());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getJgdm())) {
                    khjbzl.setJgdm(khglShhmcxx.getJgdm());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getKhlx())) {
                    khjbzl.setKhlx("2");
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getLxfs())) {
                    khjbzl.setLxfs(khglShhmcxx.getLxfs());
                }
                if (com.alibaba.druid.util.StringUtils.isEmpty(khjbzl.getDz())) {
                    khjbzl.setDz(khglShhmcxx.getDz());
                }
                khjbzlService.update(khjbzl, queryWrapper);

            } else {
                Khjbzl khjbzlSave = new Khjbzl();
                khjbzlSave.setWgbh(khglShhmcxx.getWgbh());
                khjbzlSave.setJgdm(khglShhmcxx.getJgdm());
                khjbzlSave.setKhmc(khglShhmcxx.getShmc());
                khjbzlSave.setZjlx("45");
                khjbzlSave.setZjhm(khglShhmcxx.getTyshxydm());
                khjbzlSave.setLxfs(khglShhmcxx.getLxfs());
                khjbzlSave.setDz(khglShhmcxx.getDz());
                khjbzlSave.setKhxz("2");
                khjbzlSave.setKhlx("2");
                khjbzlSave.setKhlb("2");
                khjbzlSave.setDabh(UUIDGenerator.generate());
                khjbzlSave.setCreateTime(new Date());
                khjbzlSave.setCreateBy(sysUser.getUsername());
                khjbzlService.save(khjbzlSave);
            }

            saveYxglShhfxxb(khglShhmcxx);
            //提取是否有效走访
            khglShhmcxxService.init(khglShhmcxx.getId(), sysUser.getWorkNo(), sysUser.getUsername());
        } catch (Exception e) {
            log.error("添加商户信息失败", e);
            return Result.error("系统错误，添加失败！");
        }
        return Result.ok("添加成功");
    }

    /**
     * 添加
     *
     * @return
     */
    @AutoLog(value = "商户信息管理-添加")
    @ApiOperation(value = "商户信息管理-添加", notes = "商户信息管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Shxq khglShhmcxx) {
        shcjxxService.save(khglShhmcxx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khglShhmcxx
     * @return
     */
    @AutoLog(value = "商户信息管理-编辑")
    @ApiOperation(value = "商户信息管理-编辑", notes = "商户信息管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Shxq khglShhmcxx) {
        shcjxxService.updateById(khglShhmcxx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "商户信息管理-通过id删除")
    @ApiOperation(value = "商户信息管理-通过id删除", notes = "商户信息管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        shcjxxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "商户信息管理-批量删除")
    @ApiOperation(value = "商户信息管理-批量删除", notes = "商户信息管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.shcjxxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "商户信息管理-通过id查询")
    @ApiOperation(value = "商户信息管理-通过id查询", notes = "商户信息管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Shxq khglShhmcxx = shcjxxService.getById(id);
        return Result.ok(khglShhmcxx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param khglShhmcxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, KhglShhmcxx khglShhmcxx) {
        return super.exportXls(request, khglShhmcxx, KhglShhmcxx.class, "商户信息管理");
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
        return super.importExcel(request, response, KhglShhmcxx.class);
    }


    @RequestMapping(value = "/querygxrxx", method = RequestMethod.GET)
    public Result<?> querygxrxx(@RequestParam(name = "id", required = true) String id) {
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            //通过id查询法人证件号码
            QueryWrapper<Shxq> shhmcxxQueryWrapper = new QueryWrapper<>();
            shhmcxxQueryWrapper.eq("id", id);
            Shxq khglShhmcxx = shcjxxService.getOne(shhmcxxQueryWrapper);

            JSONObject json = new JSONObject();
            json.put("id", khglShhmcxx.getId());
            json.put("zjhm", khglShhmcxx.getTyshxydm());
            json.put("khmc", khglShhmcxx.getShmc());
            jsonArray.add(json);
            QueryWrapper<KhglShglrxx> khglShglrxxQueryWrapper = new QueryWrapper<>();
            khglShglrxxQueryWrapper.eq("sh_id", id);
            List<KhglShglrxx> khglShglrxxList = khglShglrxxService.list(khglShglrxxQueryWrapper);
            List<Glrxx> glrxxList = new ArrayList<>();
            for (KhglShglrxx khglShglrxx : khglShglrxxList) {
                Glrxx shglrxx = new Glrxx();
                BeanUtils.copyProperties(khglShglrxx, shglrxx);
                JSONObject jo = new JSONObject();
                shglrxx.setXb_dictText(khglShglrxx.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", khglShglrxx.getXb()));
                shglrxx.setHyzk_dictText(khglShglrxx.getHyzk() == null ? " " : sysDictService.queryDictTextByKey("hyzk", khglShglrxx.getHyzk()));
                if (!khglShglrxx.getZjhm().equals(khglShhmcxx.getFrzjhm())) {
                    jo.put("id", khglShglrxx.getId());
                    jo.put("zjhm", khglShglrxx.getZjhm());
                    jo.put("khmc", khglShglrxx.getKhmc());
                    jsonArray.add(jo);
                }
                glrxxList.add(shglrxx);
            }
            jsonObject.put("glrxxList", glrxxList);

            //通过法人证件号码查询户号编码
            if (khglShhmcxx.getFrzjhm() != null) {
                QueryWrapper<Nhxq> hmcQueryWrapper = new QueryWrapper<>();
                hmcQueryWrapper.eq("zjhm", khglShhmcxx.getFrzjhm());
                Nhxq khhmcxx = nhxqService.getOne(hmcQueryWrapper);
                //通过户号编码查询家庭成员信息
                if (khhmcxx != null) {
                    QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                    khhmcQueryWrapper.eq("hhbm", khhmcxx.getHhbm());
                    khhmcQueryWrapper.orderByAsc("yhzgx");
                    List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);

                    //查询授信对象证件号码，如果法人家庭成员中在农户中存在授信对象，则商户授信对象下拉列表中只展示授信对象的证件号码，其余家庭成员不展示,反之则全部展示
                    QueryWrapper<KhglNhhzxxgl> khglNhhzxxglQueryWrapper = new QueryWrapper<>();
                    khglNhhzxxglQueryWrapper.eq("hhbm", khhmcxx.getHhbm());
                    KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(khglNhhzxxglQueryWrapper);
                    if (khglNhhzxxgl != null) {
                        if (khglNhhzxxgl.getSxdxzjh() != null) {
                            JSONObject jo = new JSONObject();
                            jo.put("id", khglNhhzxxgl.getId());
                            jo.put("zjhm", khglNhhzxxgl.getSxdxzjh());
                            jo.put("khmc", khglNhhzxxgl.getSxdx());
                            jsonArray.add(jo);
                        }
                    }

                    List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
                    for (int i = 0; i < list.size(); i++) {
                        JSONObject jo = new JSONObject();
                        NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
                        BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
                        String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
                        nhJtcyxx1.setYhzgx(yhzgx);
                        String xb = nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
                        nhJtcyxx1.setXb(xb);
                        nhJtcyxx.add(nhJtcyxx1);
						/*if (!list.get(i).getZjhm().equals(khglShhmcxx.getFrzjhm())) {
							jo.put("id", nhJtcyxx1.getId());
							jo.put("zjhm", nhJtcyxx1.getZjhm());
							jo.put("khmc", nhJtcyxx1.getKhmc());
							jsonArray.add(jo);
						}*/
                        if (khglNhhzxxgl != null) {
                            if (khglNhhzxxgl.getSxdxzjh() == null) {
                                jo.put("id", nhJtcyxx1.getId());
                                jo.put("zjhm", nhJtcyxx1.getZjhm());
                                jo.put("khmc", nhJtcyxx1.getKhmc());
                                jsonArray.add(jo);
                            }
                        } else {
                            jo.put("id", nhJtcyxx1.getId());
                            jo.put("zjhm", nhJtcyxx1.getZjhm());
                            jo.put("khmc", nhJtcyxx1.getKhmc());
                            jsonArray.add(jo);
                        }

                        List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
                        if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
                            if (nhJtcyxx.size() > 0) {
                                ywhywwlxxes.get(0).setId(list.get(i).getId());
                                BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
                            }
                        }
                    }
                    jsonObject.put("nhJtcyxx", nhJtcyxx);
                }
            } else {
                JSONArray jsonArray1 = new JSONArray();
                jsonObject.put("nhJtcyxx", jsonArray1);
            }
            jsonObject.put("sxdx", jsonArray);
            return Result.ok(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询关系人信息错误！", e);
            return Result.error("查询关系人信息错误！");
        }

    }

    /**
     * 永兴-根据实际经营者证件号码获取e支付收款码信息
     */
    @GetMapping(value = "/getEzfskmBySjjyzZjhm")
    public Result<?> getEzfskmBySjjyzZjhm(@RequestParam("zjhm")String zjhm){
        if (StringUtils.isNotBlank(zjhm)){
            return Result.ok(shcjxxService.getEzfskmBySjjyzZjhm(zjhm));
        }
        return Result.ok();
    }

    /**
     * 分页列表查询
     *
     * @param khglShxxgl
     * @param pageSize
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询我的采集信息")
    @ApiOperation(value = "1-分页列表查询我的采集信息", notes = "1-分页列表查询我的采集信息")
    @GetMapping(value = "/queryWdcjList")
    public Result<?> queryWdcjList(VKhglShxxgl khglShxxgl,
                                   @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<VKhglShxxgl> queryWrapper = QueryGenerator.initQueryWrapper(khglShxxgl, req.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<SysRole> userRoleCode = sysUserRoleService.getUserRoleCode(sysUser.getId());
        if (!"zhjhy".equals(userRoleCode.get(0).getRoleCode())) {
            queryWrapper.eq("lrr", sysUser.getUsername());
        } else {
            //祁阳 稽核员查询所有采集过的数据
            queryWrapper.eq("sfycj", "1");
        }
        queryWrapper.orderByDesc("xgsj");
        Page<VKhglShxxgl> page = new Page<VKhglShxxgl>(currentPage, pageSize);
        IPage<VKhglShxxgl> pageList = vKhglShxxglService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /*
       通过证件号码去花名册查询关联人信息
    */
    @GetMapping(value = "/queryGlrxxByZjhm")
    public Result<?> queryGlrxxByZjhm(@Param("zjhm") String zjhm) {
        try {
            if (!StringUtils.isEmpty(zjhm)) {
                zjhm = Base64.decodeStr(zjhm);
                QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("zjhm", zjhm);
                List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
                if (list != null && list.size() > 0) {
                    Nhxq khhmcxx = list.get(0);
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(khhmcxx).toString());
                    List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(zjhm);
                    if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
                        BeanUtils.copyProperties(ywhywwlxxes.get(0), jsonObject);
                    }
                    return Result.ok(jsonObject);
                } else {
                    //查询存贷款信息
                    HashMap<String, Object> ywxx = khglShhmcxxService.getYwxxByZjhm(zjhm);
                    if (ywxx == null) {
                        return Result.ok(false);
                    } else {
                        return Result.ok(ywxx);
                    }
                }
            } else {
                return Result.ok(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询关联人信息错误！", e);
            return Result.error("查询关联人信息错误！");
        }
    }

    /*
     通过证件号码去花名册查询关联人信息
    */
    @GetMapping(value = "/queryglrxxZjhm")
    public Result<?> queryglrxxZjhm(@Param("zjhm") String zjhm) {
        try {
            if (zjhm != null) {
                zjhm = Base64.decodeStr(zjhm);
                QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("zjhm", zjhm);
                List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
                if (list != null && list.size() > 0) {
                    Nhxq khhmcxx = list.get(0);
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(khhmcxx).toString());
                    return Result.ok(jsonObject);
                }
            } else {
                return Result.ok(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询关联人信息错误！", e);
            return Result.error("查询关联人信息错误！");
        }
        return Result.ok(false);
    }


    /**
     * 通过id查询查询法人家庭成员
     */
    @RequestMapping(value = "/queryJtcyxx", method = RequestMethod.GET)
    public Result<?> queryJtcyxx(@RequestParam(name = "id", required = true) String id) {

        //通过id查询法人证件号码
        QueryWrapper<Shxq> shhmcxxQueryWrapper = new QueryWrapper<>();
        shhmcxxQueryWrapper.eq("id", id);
        Shxq khglShhmcxx = shcjxxService.getOne(shhmcxxQueryWrapper);
        //通过法人证件号码查询户号编码
        QueryWrapper<Nhxq> hmcQueryWrapper = new QueryWrapper<>();
        hmcQueryWrapper.eq("zjhm", khglShhmcxx.getFrzjhm());
        Nhxq khhmcxx = nhxqService.getOne(hmcQueryWrapper);
        try {
            QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
            khhmcQueryWrapper.eq("hhbm", khhmcxx.getHhbm());
            khhmcQueryWrapper.orderByAsc("yhzgx");
            List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
            List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
            for (int i = 0; i < list.size(); i++) {
                NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
                BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
                String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
                nhJtcyxx1.setYhzgx(yhzgx);
                String xb = nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
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
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
        return Result.ok("没有数据");
    }

    /**
     * 通过法人证件号码查询查询法人家庭成员
     */
    @RequestMapping(value = "/queryJtcyxxByzjhm", method = RequestMethod.GET)
    public Result<?> queryJtcyxxByzjhm(@RequestParam(name = "zjhm", required = true) String zjhm, @RequestParam(name = "id", required = true) String id) {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        //通过id查询法人证件号码
        QueryWrapper<Shxq> shhmcxxQueryWrapper = new QueryWrapper<>();
        shhmcxxQueryWrapper.eq("id", id);
        Shxq khglShhmcxx = shcjxxService.getOne(shhmcxxQueryWrapper);

        JSONObject json = new JSONObject();
        json.put("id", khglShhmcxx.getId());
        json.put("zjhm", khglShhmcxx.getTyshxydm());
        json.put("khmc", khglShhmcxx.getShmc());
        jsonArray.add(json);

        if (!StringUtils.isEmpty(zjhm)) {
            zjhm = Base64.decodeStr(zjhm);
        }
        QueryWrapper<KhglShglrxx> khglShglrxxQueryWrapper = new QueryWrapper<>();
        khglShglrxxQueryWrapper.eq("sh_id", id);
        List<KhglShglrxx> khglShglrxxList = khglShglrxxService.list(khglShglrxxQueryWrapper);
        for (KhglShglrxx khglShglrxx : khglShglrxxList) {
            JSONObject jo = new JSONObject();
            khglShglrxx.setXb(khglShglrxx.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", khglShglrxx.getXb()));
            khglShglrxx.setHyzk(khglShglrxx.getHyzk() == null ? " " : sysDictService.queryDictTextByKey("hyzk", khglShglrxx.getHyzk()));
            if (khglShglrxx.getZjhm() != zjhm) {
                jo.put("id", khglShglrxx.getId());
                jo.put("zjhm", khglShglrxx.getZjhm());
                jo.put("khmc", khglShglrxx.getKhmc());
                jsonArray.add(jo);
            }
        }

        //通过法人证件号码查询户号编码
        QueryWrapper<Nhxq> hmcQueryWrapper = new QueryWrapper<>();
        hmcQueryWrapper.eq("zjhm", zjhm);
        Nhxq khhmcxx = nhxqService.getOne(hmcQueryWrapper);
        try {
            if (khhmcxx != null) {
                //查询授信对象证件号码，如果法人家庭成员中在农户中存在授信对象，则商户授信对象下拉列表中只展示授信对象的证件号码，其余家庭成员不展示,反之则全部展示
                QueryWrapper<KhglNhhzxxgl> khglNhhzxxglQueryWrapper = new QueryWrapper<>();
                khglNhhzxxglQueryWrapper.eq("hhbm", khhmcxx.getHhbm());
                KhglNhhzxxgl khglNhhzxxgl = khglNhhzxxglService.getOne(khglNhhzxxglQueryWrapper);
                if (khglNhhzxxgl != null) {
                    if (khglNhhzxxgl.getSxdxzjh() != null) {
                        JSONObject jo = new JSONObject();
                        jo.put("id", khglNhhzxxgl.getId());
                        jo.put("zjhm", khglNhhzxxgl.getSxdxzjh());
                        jo.put("khmc", khglNhhzxxgl.getSxdx());
                        jsonArray.add(jo);
                    }
                }
                QueryWrapper<Nhxq> khhmcQueryWrapper = new QueryWrapper<>();
                khhmcQueryWrapper.eq("hhbm", khhmcxx.getHhbm());
                khhmcQueryWrapper.orderByAsc("yhzgx");
                List<Nhxq> list = nhxqService.list(khhmcQueryWrapper);
                List<NhJtcyxx> nhJtcyxx = new ArrayList<NhJtcyxx>();
                for (int i = 0; i < list.size(); i++) {
                    JSONObject jo = new JSONObject();
                    NhJtcyxx nhJtcyxx1 = new NhJtcyxx();
                    BeanUtils.copyProperties(list.get(i), nhJtcyxx1);
                    String yhzgx = nhJtcyxx1.getYhzgx() == null ? " " : sysDictService.queryDictTextByKey("yhzgx", nhJtcyxx1.getYhzgx());
                    nhJtcyxx1.setYhzgx(yhzgx);
                    String xb = nhJtcyxx1.getXb() == null ? " " : sysDictService.queryDictTextByKey("sex", nhJtcyxx1.getXb());
                    nhJtcyxx1.setXb(xb);
                    if (khglNhhzxxgl != null) {
                        if (khglNhhzxxgl.getSxdxzjh() == null) {
                            jo.put("id", nhJtcyxx1.getId());
                            jo.put("zjhm", nhJtcyxx1.getZjhm());
                            jo.put("khmc", nhJtcyxx1.getKhmc());
                            jsonArray.add(jo);
                        }
                    } else {
                        jo.put("id", nhJtcyxx1.getId());
                        jo.put("zjhm", nhJtcyxx1.getZjhm());
                        jo.put("khmc", nhJtcyxx1.getKhmc());
                        jsonArray.add(jo);
                    }
						/*if (!list.get(i).getZjhm().equals(zjhm)) {
							jo.put("id", nhJtcyxx1.getId());
							jo.put("zjhm", nhJtcyxx1.getZjhm());
							jo.put("khmc", nhJtcyxx1.getKhmc());
							jsonArray.add(jo);
						}*/
                    nhJtcyxx.add(nhJtcyxx1);
                    List<Ywhywwlxx> ywhywwlxxes = ywhywwlxxService.selectByMainId(list.get(i).getZjhm());
                    if (ywhywwlxxes != null && ywhywwlxxes.size() > 0) {
                        if (nhJtcyxx.size() > 0) {
                            ywhywwlxxes.get(0).setId(list.get(i).getId());
                            BeanUtils.copyProperties(ywhywwlxxes.get(0), nhJtcyxx.get(i));
                        }
                    }
                }
                jsonObject.put("nhJtcyxx", nhJtcyxx);
            } else {
                jsonObject.put("nhJtcyxx", "");
            }
            jsonObject.put("sxdx", jsonArray);
            return Result.ok(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.toString());
        }
    }

    //查询对公业务信息
    @RequestMapping(value = "/queryDgywxx", method = RequestMethod.GET)
    public Result<?> queryDgywxx(@RequestParam(name = "id", required = true) String id) {
        try {
            JSONObject jsonObject = new JSONObject();
            QueryWrapper<Shxq> shhmcxxQueryWrapper = new QueryWrapper<>();
            shhmcxxQueryWrapper.eq("id", id);
            Shxq shhmcxx = shcjxxService.getOne(shhmcxxQueryWrapper);

            //业务往来信息
            QueryWrapper<ShglYwhywwlxx> shglYwhywwlxxQueryWrapper = new QueryWrapper<>();
            shglYwhywwlxxQueryWrapper.eq("zjhm", shhmcxx.getTyshxydm());
            List<ShglYwhywwlxx> shglYwhywwlxxList = iShglYwhywwlxxService.list(shglYwhywwlxxQueryWrapper);

            //贷款业务信息
            QueryWrapper<Dksjmx> dksjmxQueryWrapper = new QueryWrapper<>();
            dksjmxQueryWrapper.eq("zjhm", shhmcxx.getTyshxydm());
            List<Dksjmx> dksjmxList = dksjmxService.list(dksjmxQueryWrapper);
            for (Dksjmx dksjmx : dksjmxList) {
                dksjmx.setDyzrr(dksjmx.getDyzrr() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getDyzrr()));
                dksjmx.setKhjlbz(dksjmx.getKhjlbz() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmx.getKhjlbz()));
                dksjmx.setDkpz(dksjmx.getDkpz() == null ? "" : sysDictService.queryDictTextByKey("dkzl", dksjmx.getDkpz()));
                dksjmx.setDkxt(dksjmx.getDkxt() == null ? "" : sysDictService.queryDictTextByKey("dkxt", dksjmx.getDkxt()));
                dksjmx.setDbfs(dksjmx.getDbfs() == null ? "" : sysDictService.queryDictTextByKey("dbfs", dksjmx.getDbfs()));
            }
            //手机银行
            QueryWrapper<Sjyh> sjyhQueryWrapper = new QueryWrapper<>();
            sjyhQueryWrapper.eq("zjhm", shhmcxx.getTyshxydm());
            List<Sjyh> sjyhList = sjyhService.list(sjyhQueryWrapper);
            for (Sjyh sjyh : sjyhList) {
                sjyh.setCancelGyh(sjyh.getCancelGyh() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", sjyh.getCancelGyh()));
                sjyh.setStatus(sjyh.getStatus() == null ? "" : sysDictService.queryDictTextByKey("khywxx_kxhzt", sjyh.getStatus()));
                sjyh.setOpenType(sjyh.getOpenType() == null ? "" : sysDictService.queryDictTextByKey("sjyh_khlx", sjyh.getOpenType()));
            }

            //ETC
            QueryWrapper<Etc> etcQueryWrapper = new QueryWrapper<>();
            etcQueryWrapper.eq("zjhm", shhmcxx.getTyshxydm());
            List<Etc> etcList = etcService.list(etcQueryWrapper);

            if (shhmcxx.getFrzjhm() != null) {
                //福祥E支付
                QueryWrapper<Fxezh> fxezhQueryWrapper = new QueryWrapper<>();
                fxezhQueryWrapper.eq("drzjhm", shhmcxx.getFrzjhm());
                List<Fxezh> fxezhList = fxezhService.list(fxezhQueryWrapper);

                //POS机商户
                QueryWrapper<ShywxxPosjsh> posjshQueryWrapper = new QueryWrapper<>();
                posjshQueryWrapper.eq("drzjhm", shhmcxx.getFrzjhm());
                List<ShywxxPosjsh> posjshList = shywxxPosjshService.list(posjshQueryWrapper);


                //信用卡
                QueryWrapper<Xyk> xykQueryWrapper = new QueryWrapper<>();
                xykQueryWrapper.eq("zjhm", shhmcxx.getFrzjhm());
                List<Xyk> xykList = xykService.list(xykQueryWrapper);

                //现金流归行检测
                QueryWrapper<Nhxq> khhmcxxQueryWrapper = new QueryWrapper<>();
                khhmcxxQueryWrapper.eq("zjhm", shhmcxx.getFrzjhm());
                Nhxq khhmcxx = nhxqService.getOne(khhmcxxQueryWrapper);

                if (khhmcxx != null) {
                    List<Xjlghjc> xjlghjcList = xjlghjcService.queryXjlGhjc(khhmcxx.getHhbm());
                    jsonObject.put("xjlghjcList", xjlghjcList);
                }

                jsonObject.put("fxezhList", fxezhList);
                jsonObject.put("xykList", xykList);
                jsonObject.put("posjshList", posjshList);
            } else {
                List<Xyk> xykList = new ArrayList<>();
                List<Fxezh> fxezhList = new ArrayList<>();
                List<Xjlghjc> xjlghjcList = new ArrayList<>();
                List<ShywxxPosjsh> posjshList = new ArrayList<>();
                jsonObject.put("fxezhList", fxezhList);
                jsonObject.put("xykList", xykList);
                jsonObject.put("xjlghjcList", xjlghjcList);
                jsonObject.put("posjshList", posjshList);
            }
            jsonObject.put("ywhywwlxxList", shglYwhywwlxxList);
            jsonObject.put("dksjmxList", dksjmxList);
            jsonObject.put("sjyhList", sjyhList);
            jsonObject.put("etcList", etcList);
            return Result.ok(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询关系人信息错误！", e);
            return Result.error("查询关系人信息错误！");
        }

    }


    @RequestMapping(value = "/AddGlrxx", method = RequestMethod.POST)
    public Result<?> AddGlrxx(@RequestBody KhglShglrxx khglShglrxx) {
        QueryWrapper<KhglShglrxx> khglShglrxxQueryWrapper = new QueryWrapper<>();
        khglShglrxxQueryWrapper.eq("sh_id", khglShglrxx.getShId()).eq("zjhm", khglShglrxx.getZjhm());
        KhglShglrxx khglShglrxx1 = khglShglrxxService.getOne(khglShglrxxQueryWrapper);
        try {
            if (khglShglrxx1 != null) {
                return Result.error("该关联人在该公司存在关联关系");
            } else {
                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                khglShglrxx.setLrbz("1");
                khglShglrxx.setLrr(sysUser.getUsername());
                khglShglrxxService.save(khglShglrxx);
                return Result.ok("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加成员失败", e);
            return Result.error("系统错误，添加失败！");
        }

    }

    /**
     * 修改关联人信息
     *
     * @param khglShglrxxUpdate
     * @return
     */
    @AutoLog(value = "1-修改关联人信息")
    @ApiOperation(value = "1-修改关联人信息", notes = "1-修改关联人信息")
    @RequestMapping(value = "/EditGlrxx", method = RequestMethod.POST)
    public Result<?> EditGlrxx(@RequestBody KhglShglrxx khglShglrxxUpdate) {
        try {
            QueryWrapper<KhglShglrxx> khglShglrxxQueryWrapper = new QueryWrapper<>();
            khglShglrxxQueryWrapper.eq("sh_id", khglShglrxxUpdate.getShId());
            List<KhglShglrxx> khglShglrxxList = khglShglrxxService.list(khglShglrxxQueryWrapper);
            if (khglShglrxxList.size() == 0) {
                return Result.error("未找到对应的关联人信息！");
            } else {
                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                khglShglrxxUpdate.setLrbz("1");
                khglShglrxxUpdate.setXgr(sysUser.getUsername());
                khglShglrxxUpdate.setXgsj(new Date());
                khglShglrxxService.updateById(khglShglrxxUpdate);
                return Result.ok("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改关联人信息", e);
            return Result.error("系统错误，修改关联人信息！");
        }
    }

    @RequestMapping(value = "/AddJtxxByHhbm", method = RequestMethod.POST)
    public Result<?> AddJtxxByHhbm(@RequestBody JSONObject jsonObject) {
        Nhxq khglKhhmcxx = new Nhxq();
        KhglShglrxx khglShglrxx = new KhglShglrxx();
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        try {
            khglShglrxx.setShId(jsonObject.getString("shId"));
            khglShglrxx.setKhmc(jsonObject.getString("khmc"));
            khglShglrxx.setLxfs(jsonObject.getString("lxfs"));
            khglShglrxx.setZjhm(jsonObject.getString("zjhm"));
            khglShglrxx.setNl(jsonObject.getString("nl"));
            khglShglrxx.setXb(jsonObject.getString("xb"));
            khglShglrxx.setCszy(jsonObject.getString("cszy"));
            khglShglrxx.setGzdw(jsonObject.getString("gzdw"));
            khglShglrxx.setCzhjydz(jsonObject.getString("czhjydz"));
            khglShglrxx.setHyzk(jsonObject.getString("hyzk"));
            khglShglrxx.setLrbz("1");
            khglShglrxx.setLrr(sysUser.getUsername());
            khglShglrxxService.save(khglShglrxx);


            //查询户主信息
            //khglKhhmcxx.setHhbm(jsonObject.getString("hhbm"));
            khglKhhmcxx.setKhmc(jsonObject.getString("khmc"));
            khglKhhmcxx.setXb(jsonObject.getString("xb"));
            khglKhhmcxx.setSjhm(jsonObject.getString("lxfs"));
            khglKhhmcxx.setZjhm(jsonObject.getString("zjhm"));
            khglKhhmcxx.setNl(jsonObject.getString("nl"));
            khglKhhmcxx.setHyzk(jsonObject.getString("hyzk"));
            khglKhhmcxx.setCszy(jsonObject.getString("cszy"));

            khglKhhmcxx.setSfhz("2");
            khglKhhmcxx.setKhlx("2");
            khglKhhmcxx.setLrr(sysUser.getUsername());
            nhxqService.save(khglKhhmcxx);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加家庭成员信息失败", e);
            return Result.error("系统错误，添加失败！");
        }
        return Result.ok("添加成功");
    }

    @RequestMapping(value = "DeleterGlrxxId", method = RequestMethod.GET)
    public Result<?> DeleterGlrxxId(@RequestParam(name = "id", required = true) String id) {
        try {
            khglShglrxxService.removeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除失败", e);
            return Result.error("系统错误，删除失败！");
        }
        return Result.ok("删除成功");
    }

    @GetMapping(value = "/queryYwxxByZjhm")
    public Result<?> queryYwxxByZjhm(@Param("id") String id) {
        JSONArray jsonArray = new JSONArray();
        try {
            if (!StringUtils.isEmpty(id)) {
                QueryWrapper<Shxq> shhmcxxQueryWrapper = new QueryWrapper<>();
                shhmcxxQueryWrapper.eq("id", id);
                Shxq shhmcxx = shcjxxService.getOne(shhmcxxQueryWrapper);
                if (shhmcxx != null && !StringUtils.isEmpty(shhmcxx.getTyshxydm())) {
                    QueryWrapper<KhglYwhywwlxxPad> ywhywwlxxPadQueryWrapper = new QueryWrapper<>();
                    ywhywwlxxPadQueryWrapper.eq("zjhm", shhmcxx.getTyshxydm());
                    List<KhglYwhywwlxxPad> cdkywxxList = khglYwhywwlxxPadService.list(ywhywwlxxPadQueryWrapper);
                    if (cdkywxxList != null && cdkywxxList.size() > 0) {
                        Map<String, KhglYwhywwlxxPad> map = new HashMap<>();
                        map.put("cdkywxx", cdkywxxList.get(0));
                        jsonArray.add(map);
                    }
                    QueryWrapper<KhywxxDksjmxPad> dksjmx = new QueryWrapper<>();
                    dksjmx.eq("zjhm", shhmcxx.getTyshxydm());
                    List<KhywxxDksjmxPad> dksjmxList = khywxxDksjmxPadService.list(dksjmx);
                    if (dksjmxList != null && dksjmxList.size() > 0) {
                        Map<String, List<KhywxxDksjmxPad>> map = new HashMap<>();
                        for (int i = 0; i < dksjmxList.size(); i++) {
                            dksjmxList.get(i).setDkxt(sysDictService.queryDictTextByKey("dkxt", dksjmxList.get(i).getDkxt()));
                            dksjmxList.get(i).setDbfs(sysDictService.queryDictTextByKey("dbfs", dksjmxList.get(i).getDbfs()));
                            if (!StringUtils.isEmpty(dksjmxList.get(i).getKhjlbz())) {
                                dksjmxList.get(i).setKhjlbz(sysDictService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", dksjmxList.get(i).getKhjlbz()));
                            }
                        }
                        map.put("dksjmx", dksjmxList);
                        jsonArray.add(map);
                    }
                    QueryWrapper<KhywxxSjyhPad> sjyh = new QueryWrapper<>();
                    sjyh.eq("zjhm", shhmcxx.getTyshxydm());
                    List<KhywxxSjyhPad> sjyhList = khywxxSjyhPadService.list(sjyh);
                    if (sjyhList != null && sjyhList.size() > 0) {
                        Map<String, KhywxxSjyhPad> map = new HashMap<>();
                        sjyhList.get(0).setOpenJgdm(sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", sjyhList.get(0).getOpenJgdm()));
                        sjyhList.get(0).setOpenType(sysDictService.queryDictTextByKey("sjyh_khlx", sjyhList.get(0).getOpenType()));
                        map.put("sjyh", sjyhList.get(0));
                        jsonArray.add(map);
                    }
                    return Result.ok(jsonArray);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询对公业务信息错误", e);
            return Result.error("查询对公业务信息错误！");
        }
        return Result.ok("没有数据");
    }

    @GetMapping("/getShxxByTyshxydm")
    public Result<?> getShxxById(@RequestParam("tyshxydm")String tyshxydm){
        if (StringUtils.isNotBlank(tyshxydm)){
            QueryWrapper<VKhglShxxgl> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("tyshxydm",tyshxydm);
            VKhglShxxgl vKhglShxxgl=vKhglShxxglService.getOne(queryWrapper,false);
            return Result.ok(vKhglShxxgl);
        }
        return Result.ok();
    }

}
