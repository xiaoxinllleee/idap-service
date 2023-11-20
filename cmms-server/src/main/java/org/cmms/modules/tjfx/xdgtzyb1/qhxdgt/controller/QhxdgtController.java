package org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.SshUtil;
import org.cmms.modules.tjfx.xdgtzyb1.hgt.entity.Tjfx_zhbymxb_dh;
import org.cmms.modules.tjfx.xdgtzyb1.hgt.service.ITjfx_zhbymxb_dhService;
import org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.entity.Qhxdgt;
import org.cmms.modules.tjfx.xdgtzyb1.qhxdgt.service.IQhxdgtService;
import org.cmms.modules.util.EtlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date: 2020-03-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "1")
@RestController
@RequestMapping("/qhxdgt/qhxdgt")
public class QhxdgtController extends JeecgController<Qhxdgt, IQhxdgtService> {
    @Autowired
    private IQhxdgtService qhxdgtService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private ITjfx_zhbymxb_dhService iTjfx_zhbymxb_dhService;

    /**
     * 分页列表查询
     *
     * @param qhxdgt
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "1-分页列表查询")
    @ApiOperation(value = "1-分页列表查询", notes = "1-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Qhxdgt qhxdgt,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Qhxdgt> queryWrapper = QueryGenerator.initQueryWrapper(qhxdgt, req.getParameterMap());
        Page<Qhxdgt> page = new Page<Qhxdgt>(pageNo, pageSize);
        IPage<Qhxdgt> pageList = qhxdgtService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param qhxdgt
     * @return
     */
    @AutoLog(value = "1-添加")
    @ApiOperation(value = "1-添加", notes = "1-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Qhxdgt qhxdgt) {
        qhxdgtService.save(qhxdgt);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param qhxdgt
     * @return
     */
    @AutoLog(value = "1-编辑")
    @ApiOperation(value = "1-编辑", notes = "1-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Qhxdgt qhxdgt) {
        qhxdgtService.updateById(qhxdgt);
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
        qhxdgtService.removeById(id);
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
        this.qhxdgtService.removeByIds(Arrays.asList(ids.split(",")));
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
        Qhxdgt qhxdgt = qhxdgtService.getById(id);
        return Result.ok(qhxdgt);
    }


    @RequestMapping(value = "/extract", method = RequestMethod.PUT)
    public Result<?> extract(@RequestBody JSONObject tjyf) {
        try {
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                QueryWrapper<Tjfx_zhbymxb_dh> queryWrapper=new QueryWrapper<Tjfx_zhbymxb_dh>();
                Date ksrq1 = DateUtil.string2Date(tjyf.getString("ksrq"), "yyyy-MM-dd");
                Date jsrq1 = DateUtil.string2Date(tjyf.getString("jsrq"), "yyyy-MM-dd");
                queryWrapper.eq("KSRQ",ksrq1).eq("JSRQ",jsrq1);
                iTjfx_zhbymxb_dhService.remove(queryWrapper);
                //需要同步到impala的表
                List<String> tableNameList = Stream.of("khgl_khhmcxx", "yxdygl_czxxgl", "yxgl_khhfxxb", "cams_jbxx_nhzllb", "cams_zcsx_nhpjsxxx").collect(Collectors.toList());
                //同步oracle到impala
                tableNameList.forEach(item -> {
                    EtlUtil.SHcallEtlRc(10, true,false,false ,item, "idap");
                });
                //调用python脚本
                String ksrq = tjyf.getString("ksrq");
                String jsrq = tjyf.getString("jsrq");
                String jgdm = null;
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ TjfxJrphsjQhController&& python exec_tjfxxdgtzyby.py --ld_ksrq " + ksrq + " --ld_jsrq " + jsrq + " --ld_jgdm " + jgdm + "'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_TJFX_XDGTZYBY_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_XDGTZYBY_IMPORT.sh");
            }
            qhxdgtService.extract(tjyf.getString("ksrq"), tjyf.getString("jsrq"), "");
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param qhxdgt
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Qhxdgt qhxdgt) {
        return super.exportXls(request, qhxdgt, Qhxdgt.class, "1");
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
        return super.importExcel(request, response, Qhxdgt.class);
    }

}
