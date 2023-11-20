package org.cmms.modules.report.util;

/**
 */
public class ReplaceText {

    /**----------------------------------------------
     *对发布文本进行html脚本校样
     *查找特殊字符如：'<','>',
     -----------------------------------------------*/
    public static String ScriptReplace(String strInput)
    {
        if( strInput == null || strInput.length() == 0 )
        {
            return strInput;
        }
        StringBuffer buf = new StringBuffer();
        char ch = ' ';
        for( int i=0; i<strInput.length(); i++ )
        {
            ch = strInput.charAt(i);
            if( ch == '<' ) {
                buf.append( "&lt;" );
            }
            else if( ch == '>' ) {
                buf.append( "&gt;" );
            }
            else {
                buf.append( ch );
            }
        }
        return buf.toString();

    }
    /*--------------------------------------
    函数说明：查找字符串
    参数说明：searchStr 要查找的字符串
    start  开始查找的索引
    返回值：	返回找到字符串第一个字符所在位置的索引
    没找到返回 -1
    -----------------------------------------*/
    public static int indexOf(StringBuffer strBuf,String searchStr,int start)
    {
        if((strBuf==null)||(searchStr==null))
            return -1;
        int i;
        char[] search=searchStr.toCharArray();
        if (search.length==0)
        {
            return 0;
        }
        int max=strBuf.length()-search.length;
        if (max<0)
            return -1;
        if (start>max)
            return -1;
        if (start<0)
            start=0;
        // 在strBuf中找到search的第一个元素
        searchForFirst:
        for (i=start;i<=max ; i++)
        {
            if (strBuf.charAt(i)==search[0])
            {
                //找到了search中的第一个元素后，比较剩余的部分是否相等
                int k=1;
                while(k<search.length)
                {
                    if (strBuf.charAt(k+i)!=search[k])
                    {
                        continue searchForFirst;
                    }
                    k++;
                }
                return i;
            }
        }
        return -1;
    }
    /*--------------------------------------------
    函数说明：转换成html的换行
    参数说明：source 源字符串
    返回值：字符串
    ----------------------------------------------*/
    public static String convToHtmlNewLine(String source)
    {
        StringBuffer strBuf=new StringBuffer(source);
        replace(strBuf,"\r\n","<br>");
        replace(strBuf,"\r","<br>");
        replace(strBuf,"\n","<br>");
        return strBuf.toString();
    }

    /*------------------------------------------------------
    函数说明：替换所有的字符串
    参数说明：oldStr  被替换的字符串
    newStr  用来替换的字符串
    返回值：返回自己
    -------------------------------------------------------*/
    public static void replace(StringBuffer strBuf,String oldStr,String newStr)
    {
        int temp=0;
        while((temp=indexOf(strBuf,oldStr,temp))>=0)
        {
            strBuf.replace(temp,temp+oldStr.length(),newStr);
            temp+=newStr.length();
        }
    }
    /*---------------------------------------------
    函数说明：替换所有的字符串
    参数说明：oldStr  被替换的字符串
    newStr  用来替换的字符串
    返回值：返回自己
    -----------------------------------------------*/
    public static String replace(String source,String oldStr,String newStr)
    {
        StringBuffer strBuf=new StringBuffer(source);
        replace(strBuf,oldStr,newStr);
        return strBuf.toString();
    }

    /*-----------------------------------------------------
    函数说明：查找字符串
    参数说明：searchStr 要查找的字符串
    start  开始查找的索引
    返回值：返回找到字符串第一个字符所在位置的索引
    没找到返回 -1
    -------------------------------------------------------*/
    public static int lastIndexOf(StringBuffer strBuf,String searchStr,int start,int end)
    {
        if((strBuf==null)||(searchStr==null))
            return -1;
        int i;
        char[] search=searchStr.toCharArray();  //将要查找的字符串转换成字符数组
        if (search.length==0)  //空字符串
        {
            return 0;
        }
        if(start<0)start=0;		//起始位置小于零 复位零
        if(end>=strBuf.length()) end=strBuf.length()-1;        //结束位置超过长度 复位字符串缓冲的最后一位
        int max=end-start-search.length;   ///
        if (max<0)
            return -1;
        // 在strBuf中找到search的第一个元素
        System.out.println(end);
        System.out.println(max-start);
        searchForFirst:
        for (i=end;i>=start+search.length;i--)
        {
            System.out.println(i);
            if (strBuf.charAt(i)==search[search.length-1])   //比较最后一个字符是否吻合
            {
                //找到了search中的第一个元素后，比较剩余的部分是否相等
                int k=1;
                while(k<search.length)
                {
                    System.out.println(k);
                    if (strBuf.charAt(i-k)!=search[search.length-1-k])
                    {
                        System.out.println(k);
                        System.out.println(strBuf.charAt(i-k));
                        continue searchForFirst;
                    }
                    k++;
                }
                return i-search.length+1;
            }
        }
        return -1;
    }
    /*--------------------------------------------
    函数说明：删除前面的空格
    参数说明：strBuf 字符串缓冲
    end  开始查找的索引
    返回值：返回删除的空格数
    ---------------------------------------------*/
    public static int delBeforeBlank(StringBuffer strBuf,int end)
    {
        if(strBuf==null) return 0;
        if(end>=strBuf.length()) end=strBuf.length()-1;
        for(int i=end;i>=0;i--)
        {
            if(strBuf.charAt(i)==' ')
                strBuf.deleteCharAt(i);
            else
                return end-i;
        }
        return end+1;
    }
    /*-----------------------------------------------------------------
    函数说明：删除后面的空格
    参数说明：strBuf 字符串缓冲
    start  开始查找的索引
    返回值：返回删除的空格数
    -----------------------------------------------------------------*/
    public static int delBlackBlank(StringBuffer strBuf,int start)
    {
        if(strBuf==null) return 0;
        if(start<0) start=0;
        for(int i=0;start<strBuf.length();i++)
        {
            if(strBuf.charAt(start)==' ')
                strBuf.deleteCharAt(start);
            else
                return i;
        }
        return strBuf.length()-start;
    }
    /*-----------------------------------------------------------
    函数说明：查找被注释的字符串(指标签 前面是<!-- 后面 --> )
    参数说明：searchStr 要查找的字符串
    start  开始查找的索引
    返回值：返回找到标签第一个字符所在位置的索引
    没找到返回 -1
    ---------------------------------------------------------------*/
    public static int beginIndexOfTag(StringBuffer strBuf,String tagBody,int start)
    {
        int index=0;
        int beginIndex=0;
        String strTmp="";
        int returnValue=-1;
        do
        {
            index=indexOf(strBuf,tagBody,start);    //得到标签主体的索引位置
            System.out.println(index);
            if(index<0) return -1;					//找不到返回 -1
            beginIndex=lastIndexOf(strBuf,"<!--",start,index);
            if(beginIndex<0) return -1;
            strTmp=strBuf.substring(beginIndex,index);
            strTmp=strTmp.trim();
            if(strTmp.equals("<!--"))
            {
                returnValue=beginIndex;
                break;
            }
        }
        while(true);
        return returnValue;
    }
    /*-------------------------------------------------------------
    函数说明：查找被注释的字符串(指标签 前面是<!-- 后面 --> )
    参数说明：searchStr 要查找的字符串
    start  开始查找的索引
    返回值：返回找到标签最后一个字符的所在位置后面一个位置的索引
    没找到返回 -1
    --------------------------------------------------------------*/
    public static int endIndexOfTag(StringBuffer strBuf,String tagBody,int start)
    {
        int index=0;
        int endIndex=0;
        String strTmp="";
        int returnValue=-1;
        do
        {
            index=indexOf(strBuf,tagBody,start);
            System.out.println(index);
            if(index<0) return -1;
            endIndex=indexOf(strBuf,"-->",index+tagBody.length());
            System.out.println(endIndex);
            if(endIndex<0) return -1;
            strTmp=strBuf.substring(index+tagBody.length(),endIndex+3);
            strTmp=strTmp.trim();
            if(strTmp.equals("-->"))
            {
                returnValue=endIndex+3;
                break;
            }
            start+=tagBody.length();
        }
        while(true);
        return returnValue;
    }
    /*-------------------------------------------------------------
    函数说明：替换所有的标签
    参数说明：tagBody  被替换的标签体
    newStr  用来替换的字符串
    返回值：返回自己
    --------------------------------------------------------------*/
    public static void replaceTag(StringBuffer strBuf,String tagBody,String newStr)
    {
        int beginIndex=0;
        int endIndex=0;
        int temp=0;
        beginIndex=beginIndexOfTag(strBuf,tagBody,temp);
        endIndex=endIndexOfTag(strBuf,tagBody,beginIndex);
        while((beginIndex>=0)&&(endIndex>=0))
        {
            strBuf.replace(beginIndex,endIndex,newStr);
            temp=beginIndex+newStr.length();
        }
    }

    /*------------------------------------------------
    函数说明：替换所有夹在两个标志之间的字符串
    参数说明：beginStr  开始标志字符串
    endStr    结束标志字符串
    newStr 用来替换的字符串
    返回值：返回自己
    ---------------------------------------------------*/
    public static void replaceTag(StringBuffer strBuf,String beginTag,String endTag,String newStr)
    {
        int beginIndex=0;
        int endIndex=0;
        int temp=0;
        beginIndex=beginIndexOfTag(strBuf,beginTag,temp);
        endIndex=endIndexOfTag(strBuf,endTag,beginIndex);
        while((beginIndex>=0)&&(endIndex>=0))
        {
            strBuf.replace(beginIndex,endIndex,newStr);
            temp=beginIndex+newStr.length();
        }
    }
    /*-------------------------------------------------------------
    函数说明：替换所有夹在两个标志之间的字符串
    参数说明：beginStr  开始标志字符串
    endStr    结束标志字符串
    newStr 用来替换的字符串
    返回值：返回自己
    ---------------------------------------------------------------*/
    public static void replace(StringBuffer strBuf,String beginStr,String endStr,String newStr)
    {
        int beginIndex=0;
        int endIndex=0;
        while((beginIndex=indexOf(strBuf,beginStr,beginIndex))>=0)
        {
            endIndex=indexOf(strBuf,endStr,beginIndex)+endStr.length();
            strBuf.replace(beginIndex,endIndex,newStr);
            beginIndex+=newStr.length();
        }
    }

    /*----------------------------------------------------
    函数说明：是否数字字符串
    参数说明：
    返回值	返回 false 表示不是数字 返回 true 表示int
    ----------------------------------------------------*/
    public static boolean isInt(String str)
    {
        if((str==null)||str.equals("")) return false;
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
    /*-------------------------------------------------
    函数说明：是否数字字符串
    参数说明：
    返回值	返回 false 表示不是数字 返回 true 表示int
    -------------------------------------------------*/
    public static boolean isBoolean(String str)
    {
        if((str==null)||str.equals("")) return false;
        try
        {
            Boolean.valueOf(str);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }


    /*---------------------------------------------------
    @功能：醒目显示某个关键字
    -----------------------------------------------------*/
    public static String getHigheKey(String p_strHighe,String p_strKey)
    {
        StringBuffer buff=new StringBuffer(p_strHighe);

        ReplaceText.replace(buff,p_strKey,"<font color=red>"+p_strKey+"</font>");

        return buff.toString();
    }

}
