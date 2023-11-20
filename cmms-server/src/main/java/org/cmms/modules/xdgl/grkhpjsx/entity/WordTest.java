package org.cmms.modules.xdgl.grkhpjsx.entity;

import cn.hutool.core.util.IdcardUtil;
import org.apache.commons.lang3.StringUtils;
import org.cmms.modules.util.WordUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WordTest {
    public static void main(String[] args) throws Exception{
        System.out.println(IdcardUtil.getBirth("43070319941009685X"));
        System.out.println(IdcardUtil.getYearByIdCard("43070319941009685X"));
        System.out.println(IdcardUtil.getMonthByIdCard("43070319941009685X"));
    }
        /*FamerExportWord  famerExportWord = new FamerExportWord();
        FamilyMember familyMember = new FamilyMember();
        familyMember.setName("aa");
        familyMember.setRelation("bb");
        familyMember.setRelationVal("cc");
        List<FamilyMember> familyMembers = new ArrayList<>();
        familyMembers.add(familyMember);
        familyMembers.add(familyMember);
        famerExportWord.setFamilyMember(familyMembers);
        famerExportWord.setId("1234");
        famerExportWord.setIdn("430703");
        long begin = System.currentTimeMillis();
        WordUtils wordUtils = new WordUtils("infoCollect.docx", "infoCollect.ftl");
        File tempDirectory = wordUtils.generateTempDirectory(WordUtils.CREDIT_SYSTEM_INFO_COLLECT_WORD_DIRECTORY);
        File wordFile = new File(tempDirectory, StringUtils.join(
                "id",
                "_",
                "idn",
                ".docx"
        ));
        System.out.println(wordFile.getAbsolutePath());
        wordUtils.generateDocxFile(new FileOutputStream(wordFile), famerExportWord);
    }*/
}
