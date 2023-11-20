package org.cmms.modules.yxdygl.pqqxgl.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxgl;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxglTree;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxglVO;
import org.cmms.modules.yxdygl.pqqxgl.entity.ygglYxdyglPqqxglPo;
import org.cmms.modules.yxdygl.pqqxgl.service.IYxdyglPqqxglService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMain;
import org.cmms.modules.yxdygl.yxdyglmain.service.IYxdyglMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 片区权限管理
 * @Author: jeecg-boot
 * @Date: 2021-11-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "片区权限管理")
@RestController
@RequestMapping("/pqqxgl/yxdyglPqqxgl")
public class YxdyglPqqxglController extends JeecgController<YxdyglPqqxgl, IYxdyglPqqxglService> {
    @Autowired
    private IYxdyglMainService yxdyglMainService;
    @Autowired
    private IYxdyglPqqxglService yxdyglPqqxglService;

    /**
     * 分页列表查询
     *
     * @param yxdyglPqqxgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "片区权限管理-分页列表查询")
    @ApiOperation(value = "片区权限管理-分页列表查询", notes = "片区权限管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(YxdyglPqqxgl yxdyglPqqxgl,
                                   @RequestParam(name = "wgmc", required = false) String wgmc,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String menuId = yxdyglPqqxgl.getMenuId();
        yxdyglPqqxgl.setMenuId(null);
        QueryWrapper<YxdyglPqqxgl> queryWrapper = QueryGenerator.initQueryWrapper(yxdyglPqqxgl, req.getParameterMap());
        if (StringUtils.isNotEmpty(wgmc)) {
            queryWrapper.inSql("menu_Id", "select wgbh from yxdygl_main where wgmc like '%" + wgmc + "%'");
        }
        //查询网格时，同时查询下级网格的数据
        if (StringUtils.isNotEmpty(menuId)) {
            String sqlSswg = "select wgbh from yxdygl_main t where wgbh ='" + menuId + "' or parent_id='" + menuId + "'";
            queryWrapper.and(i -> i.inSql("menu_Id", sqlSswg));
        }
        if (!getUsername().equals("admin")) {
            queryWrapper.eq("khjl", getLoginUser().getWorkNo());
        }
        Page<YxdyglPqqxgl> page = new Page<YxdyglPqqxgl>(pageNo, pageSize);
        IPage<YxdyglPqqxgl> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @ApiOperation(value = "片区权限管理-分页列表查询", notes = "片区权限管理-分页列表查询")
    @GetMapping(value = "/getYgList")
    public Result<?> list(@RequestParam(name = "yggh", defaultValue = "1") String yggh,
                          HttpServletRequest req) {
        QueryWrapper<YxdyglPqqxgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("khjl", yggh);
        List<YxdyglPqqxgl> list = service.list(queryWrapper);
        return Result.ok(list);
    }

    @GetMapping(value = "/listTree")
    public Result<?> listTree(YxdyglPqqxgl yxdyglPqqxgl,
                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                              HttpServletRequest req) {
        Page<YxdyglPqqxglTree> page = new Page<YxdyglPqqxglTree>(pageNo, pageSize);
        IPage<YxdyglPqqxglTree> pageList = service.getTreeList(page, yxdyglPqqxgl);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param yxdyglPqqxgl
     * @return
     */
    @AutoLog(value = "片区权限管理-添加")
    @ApiOperation(value = "片区权限管理-添加", notes = "片区权限管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody YxdyglPqqxglVO yxdyglPqqxgl) {
        System.out.println(yxdyglPqqxgl);
        //村组信息
        List<String> checkedKeys = yxdyglPqqxgl.getCheckedKeys();
        //查询所有下级的数据
        QueryWrapper<YxdyglMain> yxdyglMainQueryWrapper = new QueryWrapper<>();
        yxdyglMainQueryWrapper.in("parent_id", checkedKeys);
        List<YxdyglMain> yxdyglMainList = yxdyglMainService.list(yxdyglMainQueryWrapper);
        List<String> childrenKeys = yxdyglMainList.stream().map(YxdyglMain::getWgbh).collect(Collectors.toList());
        checkedKeys.removeAll(childrenKeys);
        checkedKeys.addAll(childrenKeys);

        //员工信息
        List<String> selectedRowKeys = yxdyglPqqxgl.getSelectedRowKeys();
        List<YxdyglPqqxgl> list = new ArrayList<>();
        if ("1".equals(yxdyglPqqxgl.getSfzkhjl())) {
            //主客户经理只能有一个
            String zkhjl = selectedRowKeys.get(0);
            //客户经理已经有权限的片区
            List<String> menuIdsByKhjl = service.getMenuIdsByKhjlgh(zkhjl);
            checkedKeys.removeAll(menuIdsByKhjl);
            //已经存在主客户经理的片区， 替换成辅客户经理
            List<String> menuIdsByZkhjl = service.getMenuIdsByZkhjl();
//            checkedKeys.removeAll(menuIdsByZkhjl);

            if (CollUtil.isNotEmpty(checkedKeys)) {
                for (String c : checkedKeys) {
                    YxdyglPqqxgl insert = new YxdyglPqqxgl();
                    insert.setKhjl(zkhjl);
                    insert.setMenuId(c);
                    insert.setSfzkhjl(yxdyglPqqxgl.getSfzkhjl());
                    if (menuIdsByZkhjl != null && menuIdsByZkhjl.contains(c)) {
                        insert.setSfzkhjl("2");
                    }
                    list.add(insert);
                }
            }
        } else {
            for (String khjl : selectedRowKeys) {
                //客户经理已经有权限的片区
                List<String> menuIdsByKhjl = service.getMenuIdsByKhjlgh(khjl);
                List<String> checkedKeysCopy = CollectionUtil.newArrayList(checkedKeys);
                checkedKeysCopy.removeAll(menuIdsByKhjl);
                for (String menuId : checkedKeysCopy) {
                    YxdyglPqqxgl insert = new YxdyglPqqxgl();
                    insert.setKhjl(khjl);
                    insert.setMenuId(menuId);
                    insert.setSfzkhjl(yxdyglPqqxgl.getSfzkhjl());
                    list.add(insert);
                }
            }
        }
        service.saveBatch(list);

        //被转移的客户
        if (StringUtils.isNotBlank(yxdyglPqqxgl.getKhjl())) {
            LambdaQueryWrapper<YxdyglPqqxgl> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(YxdyglPqqxgl::getKhjl, yxdyglPqqxgl.getKhjl());
            lambdaQueryWrapper.in(YxdyglPqqxgl::getMenuId, checkedKeys);
            service.remove(lambdaQueryWrapper);
        }
        //todo 要删除数据重复添加的数据 一个客户经理在片区只管一个
        return Result.ok("添加成功！");
    }

    @ApiOperation(value = "片区权限管理-导入员工入岗信息时添加", notes = "片区权限管理-导入员工入岗信息时添加")
    @PostMapping(value = "/addPqqxgl")
    public Result<?> addPqqxgl(@RequestBody List<ygglYxdyglPqqxglPo> ygglYxdyglPqqxglList) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<YxdyglPqqxgl> list = new ArrayList<>();
        ygglYxdyglPqqxglList.stream().forEach(item -> {
                    if (StringUtils.isNotBlank(item.getZzbz())) {
                        QueryWrapper<YxdyglPqqxgl> yxdyglPqqxglQueryWrapper = new QueryWrapper<>();
                        yxdyglPqqxglQueryWrapper.eq("khjl", item.getYggh());
                        yxdyglPqqxglService.remove(yxdyglPqqxglQueryWrapper);

                        QueryWrapper<YxdyglMain> yxdyglMainQueryWrapper = new QueryWrapper<>();
                        yxdyglMainQueryWrapper.eq("zzbz", item.getZzbz());
                        List<String> pqList = yxdyglMainService.list(yxdyglMainQueryWrapper).stream().map(YxdyglMain::getWgbh).collect(Collectors.toList());

                        QueryWrapper<YxdyglPqqxgl> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("sfzkhjl", "1").eq("sszh", item.getZzbz());
                        List<YxdyglPqqxgl> list1 = yxdyglPqqxglService.list(queryWrapper);

                        pqList.stream().forEach(b -> {
                            list.add(new YxdyglPqqxgl().setKhjl(item.getYggh()).setCreateTime(new Date())
                                    .setCreateBy(sysUser.getUsername())
                                    .setSszh(item.getZzbz()).setMenuId(b)
                                    .setSfzkhjl((list1 != null || list1.size() > 0) ? "2" : "1"));
                        });
                        service.saveBatch(list);
                    }
                }
        );
        return Result.ok();

    }

    @ApiOperation(value = "片区权限管理-调整", notes = "片区权限管理-调整")
    @PostMapping(value = "/adjust")
    public Result<?> adjust(@RequestBody YxdyglPqqxglVO yxdyglPqqxgl) {
        //村组信息
        List<String> checkedKeys = yxdyglPqqxgl.getCheckedKeys();
        //员工信息
        List<String> selectedRowKeys = yxdyglPqqxgl.getSelectedRowKeys();
        String khjl = selectedRowKeys.get(0);
        //客户经理已经有权限的片区
        List<String> menuIdsByKhjl = service.getMenuIdsByKhjlgh(khjl);

        //查询所有下级的数据
        QueryWrapper<YxdyglMain> yxdyglMainQueryWrapper = new QueryWrapper<>();
        yxdyglMainQueryWrapper.in("parent_id", checkedKeys);
        List<YxdyglMain> yxdyglMainList = yxdyglMainService.list(yxdyglMainQueryWrapper);
        List<String> childrenKeys = yxdyglMainList.stream().map(YxdyglMain::getWgbh).collect(Collectors.toList());
        checkedKeys.removeAll(childrenKeys);
        checkedKeys.addAll(childrenKeys);

        //不存在的片区需要删除
        List<String> deleteMenuIds = menuIdsByKhjl.stream().filter(item -> !checkedKeys.contains(item)).collect(Collectors.toList());
        if (deleteMenuIds != null && !deleteMenuIds.isEmpty()) {
            QueryWrapper<YxdyglPqqxgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("khjl", khjl);
            queryWrapper.in("menu_id", deleteMenuIds);
            service.remove(queryWrapper);
        }

        //已有权限的片区不做处理
        checkedKeys.removeAll(menuIdsByKhjl);
        //新分配的片区
        //已经存在主客户经理的片区， 替换成辅客户经理
        List<String> menuIdsByZkhjl = service.getMenuIdsByZkhjl();
        List<YxdyglPqqxgl> list = new ArrayList<>();
        for (String c : checkedKeys) {
            YxdyglPqqxgl insert = new YxdyglPqqxgl();
            insert.setKhjl(khjl);
            insert.setMenuId(c);
            insert.setSfzkhjl("1");
            if (menuIdsByZkhjl != null && menuIdsByZkhjl.contains(c)) {
                insert.setSfzkhjl("2");
            }
            list.add(insert);
        }
        service.saveBatch(list);
        return Result.ok("调整成功！");
    }

    /**
     * 编辑
     *
     * @param yxdyglPqqxgl
     * @return
     */
    @AutoLog(value = "片区权限管理-编辑")
    @ApiOperation(value = "片区权限管理-编辑", notes = "片区权限管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody YxdyglPqqxgl yxdyglPqqxgl) {
        if ("1".equals(yxdyglPqqxgl.getSfzkhjl())) {
            //判断当前网格是否已有主客户经理
            QueryWrapper<YxdyglPqqxgl> yxdyglPqqxglQueryWrapper = new QueryWrapper<>();
            yxdyglPqqxglQueryWrapper.eq("menu_id", yxdyglPqqxgl.getMenuId());
            yxdyglPqqxglQueryWrapper.eq("sfzkhjl", "1");
            yxdyglPqqxglQueryWrapper.ne("khjl", yxdyglPqqxgl.getKhjl());
            List<YxdyglPqqxgl> list = service.list(yxdyglPqqxglQueryWrapper);
            if (!list.isEmpty()) {
                return Result.error("此网格已经存在主客户经理[" + list.get(0).getKhjl() + "]");
            }
        }
        service.updateById(yxdyglPqqxgl);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "片区权限管理-通过id删除")
    @ApiOperation(value = "片区权限管理-通过id删除", notes = "片区权限管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "片区权限管理-批量删除")
    @ApiOperation(value = "片区权限管理-批量删除", notes = "片区权限管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "片区权限管理-通过id查询")
    @ApiOperation(value = "片区权限管理-通过id查询", notes = "片区权限管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        YxdyglPqqxgl yxdyglPqqxgl = service.getById(id);
        return Result.ok(yxdyglPqqxgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param yxdyglPqqxgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, YxdyglPqqxgl yxdyglPqqxgl) {
        return super.exportXls(request, yxdyglPqqxgl, YxdyglPqqxgl.class, "网格权限管理");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return super.importExcelByTemplate(jsonObject, request, response, YxdyglPqqxgl.class, null);
    }

    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        return super.exportTemplateXls(YxdyglPqqxgl.class, "网格权限导入模板");
    }

}
