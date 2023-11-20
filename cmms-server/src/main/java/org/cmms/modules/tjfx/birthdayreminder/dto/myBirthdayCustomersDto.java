package org.cmms.modules.tjfx.birthdayreminder.dto;


import lombok.Data;
import org.cmms.modules.tjfx.birthdayreminder.entity.myBirthdayCustomerEntity;
import org.cmms.modules.tjfx.birthdayreminder.entity.wdsrkhFileEntity;


import java.util.List;

@Data
public class myBirthdayCustomersDto {


      private myBirthdayCustomerEntity birthdayCustomers;

        /** 文件信息*/
        private List<wdsrkhFileEntity> wdsrkhFileList;

      private List<String> deleteIds;


}
