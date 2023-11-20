package org.cmms.modules.tjfx.zhcdksjmx.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.zhcdksjmx.entity.Zhcksjmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行存款数据明细
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
public interface ZhcksjmxMapper extends BaseMapper<Zhcksjmx> {
    public List<Map> getzhjynck();

    public List<Map> getzhdqhqck();

    public List<Map> getzhdgdsck();

    public List<Map> getzhanlqjck(@Param("tjyf") Date tjyf);

    public List<Map> getzhdhckqj(@Param("tjyf") Date tjyf);



    public List<Map> getjynck(@Param("jgdm") String jgdm);

    public List<Map> getdqhqck(@Param("jgdm") String jgdm);

    public List<Map> getdgdsck(@Param("jgdm") String jgdm);


    public List<Map> getanlqjck(@Param("tjyf") Date tjyf,@Param("jgdm") String jgdm);

    public List<Map> getdhckqj(@Param("tjyf") Date tjyf,@Param("jgdm") String jgdm);


    public Date getAnlqjcRq();

    public Date getZhdksjrq();

    public Date getZhcksjrq();


    public List<Map> getzhjyndksj();

    public List<Map> getjyndksj(@Param("jgdm") String jgdm);

    public List<Map> getckpm(@Param("tjyf") Date tjyf);

    public List<Map> getckwclpm(@Param("tjyf") Date tjyf);

    public List<Map> getzhwjfldk(@Param("tjyf") Date tjyf);

    public List<Map> getwjfldk(@Param("tjyf") Date tjyf,@Param("jgdm") String jgdm);



    public List<Map> getqhcksj(@Param("tjyf") Date tjyf);

    public List<Map> getzhcksj(@Param("tjyf") Date tjyf,@Param("jgdm") String jgdm);

    public List<Map> getqhcdkxzs();

    public List<Map> getcdkxzs(@Param("jgdm") String jgdm);


    public List<Map> getzhanlqjdk(@Param("tjyf") Date tjyf);

    public List<Map> getanlqjdk(@Param("tjyf") Date tjyf,@Param("jgdm") String jgdm);


    public List<Map> getzhdhdkqj(@Param("tjyf") Date tjyf);

    public List<Map> getdhdkqj(@Param("tjyf") Date tjyf,@Param("jgdm") String jgdm);

    public List<Map> getdkpm(@Param("tjyf") Date tjyf);

    public List<Map> getdkwclpm(@Param("tjyf") Date tjyf);

    public Date getAnlqjcRqDk();

    public void callSysjmx();


}
