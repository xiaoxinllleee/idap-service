package org.cmms.modules.tjfx.ipadsysj.service.impl;

import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.tjfx.ipadsysj.entity.SysjQhzhCjpm;
import org.cmms.modules.tjfx.ipadsysj.entity.Zhyxzfpm;
import org.cmms.modules.tjfx.ipadsysj.entity.vTjfxSysj;
import org.cmms.modules.tjfx.ipadsysj.mapper.vTjfxSysjMapper;
import org.cmms.modules.tjfx.ipadsysj.service.IvTjfxSysjService;
import org.cmms.modules.tjfx.zfsjmx.khjlsjmx.entity.ZfsjmxKhjl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 平板端首页数据
 * @Author: jeecg-boot
 * @Date:   2020-07-23
 * @Version: V1.0
 */
@Service
public class vTjfxSysjServiceImpl extends ServiceImpl<vTjfxSysjMapper, vTjfxSysj> implements IvTjfxSysjService {


    public Map<String,Object> querysysj() {
     return baseMapper.querysysj();
    }

    @Override
    public List<ZfsjmxKhjl> queryZhCjpm(String sszh){
     return baseMapper.queryZhCjpm(sszh);
    }
    public List<ZfsjmxKhjl> queryQhCjpm(){
     return baseMapper.queryQhCjpm();
    }
    public Long grbyzfhs(String khjl){
     return baseMapper.grbyzfhs(khjl);
    }
    public Long zhbyzfhs(String zzbz){
     return baseMapper.zhbyzfhs(zzbz);
    }
    public Long qhbyzfhs(){
     return baseMapper.qhbyzfhs();
    }
    public Long grljzfhs(String khjl){
     return baseMapper.grljzfhs(khjl);
    }
    public Long zhljzfhs(String zzbz){
     return baseMapper.zhljzfhs(zzbz);
    }
    @Override
    public Long qhljzfhs(){
     return baseMapper.qhljzfhs();
    }
    @Override
    public Long grdyysxed(String khjl){
     return baseMapper.grdyysxed(khjl);
    }
    @Override
    public Long zhdyysxed(String zzbz){
        return baseMapper.zhdyysxed(zzbz);
    }
    @Override
    public Long qhdyysxed(){
     return baseMapper.qhdyysxed();
    }
    @Override
    public Long grljysxed(String khjl){
     return baseMapper.grljysxed(khjl);
    }
    @Override
    public Long zhljysxed(String zzbz){
        return baseMapper.zhljysxed(zzbz);
    }
    @Override
    public Long qhljysxed(){
     return baseMapper.qhljysxed();
    }
    @Override
    public String queryGyh(String username){
        return baseMapper.queryGyh(username);
    }
    @Override
    public void initKhxx(){
        baseMapper.initKhxx();
    }
    @Override
    public void initKhywxx(){
        baseMapper.initKhywxx();
    }
    @Override
    public void initShxx(){
        baseMapper.initShxx();
    }
    @Override
    public void initShywxx(){
        baseMapper.initShywxx();
    }

    @Override
    public Long shbyzfhs(String khjl){
     return baseMapper.shbyzfhs(khjl);
    }

    @Override
    public Long shzhbyzfhs(String zzbz) {
        return baseMapper.shzhbyzfhs(zzbz);
    }

    @Override
    public Long shqhbyzfhs() {
        return baseMapper.shqhbyzfhs();
    }
    @Override
    public Long shljzfhs(String khjl){
     return baseMapper.shljzfhs(khjl);
    }

    @Override
    public Long shzhljzfhs(String zzbz) {
        return baseMapper.shzhljzfhs(zzbz);
    }

    @Override
    public Long shqhljzfhs() {
        return baseMapper.shqhljzfhs();
    }

    @Override
    public Long shbyysxed(String khjl){
     return baseMapper.shbyysxed(khjl);
    }

    @Override
    public Long shzhbyysxed(String zzbz) {
        return baseMapper.shzhbyysxed(zzbz);
    }
    @Override
    public Long shqhbyysxed() {
        return baseMapper.shqhbyysxed();
    }

    @Override
    public Long shljysxed(String khjl){
     return baseMapper.shljysxed(khjl);
    }

    @Override
    public Long shzhljysxed(String zzbz) {
        return baseMapper.shzhljysxed(zzbz);
    }
    @Override
    public Long shqhljysxed() {
        return baseMapper.shqhljysxed();
    }

    @Override
    public List<SysjQhzhCjpm> queryQhzhCjpm(){
     return baseMapper.queryQhzhCjpm();
    }

    @Override
    public HrBasOrganization queryZzxxByYggh(String yggh) {
        return baseMapper.queryZzxxByYggh(yggh);
    }

    @Override
    public List<SysjQhzhCjpm> getYxzfpm(String khlx,String tjwd){
        return baseMapper.getYxzfpm(khlx,tjwd);
    }
    @Override
    public List<SysjQhzhCjpm> getYxzfpmByYggh(String khlx,String tjwd,String yggh){
        return baseMapper.getYxzfpmByYggh(khlx,tjwd,yggh);
    }

    @Override
    public List<Zhyxzfpm> getZhYxzfpm(String tjwd){
        return baseMapper.getZhYxzfpm(tjwd);
    }

    @Override
    public Map<String,Object> getYgYxzfsj(String yggh, String khlx) {
        return baseMapper.getYgYxzfsj(yggh, khlx);
    }
}

