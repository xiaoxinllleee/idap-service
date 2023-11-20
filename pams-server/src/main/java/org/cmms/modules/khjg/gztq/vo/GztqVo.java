package org.cmms.modules.khjg.gztq.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.cmms.modules.khjg.gztq.entity.VDpJdrwgl;
import org.cmms.modules.khlc.khfagl.entity.VPmaAScheme;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class GztqVo {

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date tjrqBegin;


    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date tjrqEnd;


    private List<VDpJdrwgl> dpJdrwgl;

    private List<VPmaAScheme> pmaASchemes ;

}

