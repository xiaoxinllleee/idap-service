package org.cmms.common.util.encryption;


import lombok.Data;

@Data
public class  EncryptedString {

    public static  String key = "1234567890adbcde";//长度为16个字符

    public static  String iv  = "1234567890hjlkew";//长度为16个字符

    public static String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxgaRAud920IRs4dm3UFGjsdF+R8V4sw6RAJxa" +
            "7vJT0Gw4ZYXVShDO3Jo1pENabxQxYsbkl1cclng5l7ebZ3KsW1mZEMbD96mJ46yWoedeOHDMRY4p" +
            "DtvlHL/xIC3ZrJk8GiGLMKDckCyGfwXbXj9ut7nmvkZqH6c6Qpc9E+LGGwIDAQAB";

    public static String RSA_PRIMARY_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALGBpEC533bQhGzh2bdQUaOx0X5H" +
            "xXizDpEAnFru8lPQbDhlhdVKEM7cmjWkQ1pvFDFixuSXVxyWeDmXt5tncqxbWZkQxsP3qYnjrJah" +
            "5144cMxFjikO2+Ucv/EgLdmsmTwaIYswoNyQLIZ/BdteP263uea+RmofpzpClz0T4sYbAgMBAAEC" +
            "gYBbBCJ4ndYbUifUvKmp+SEjUlYc7igvD4D0kN+bMNJIb6JN+KB7ERM1PqBDyQpQIIy2FfPt2jvs" +
            "iKr0vPqEULLXGrloM9CI8vudUgFpo6tS9i7sKl1RaeMocztIbZlE/VjkfWzeU90H6VvXKFu3iU/5" +
            "uhKDrIW01BfdKnZGi7G3wQJBAOGoN2ti99yW4t2M04r1H8qtoxVkwtj2QmJjhcE1zGuxkjs323H9" +
            "I/cM6bLC0NQTw66xS22HVgY69lljlHKPjzkCQQDJX++69XNIKv9pmJ50ECXbPGSw9TtfRj8Qgkyl" +
            "3ZzCZiQjtaQo11+xBA+1Wubo0nOmqpXmDStd2+g7VGDpyWvzAkEAz3Pwjx8LM5bbtkZzbHpx4cjD" +
            "9zUNFJ1A5mcMXlXxOsii4SI28pKAb+1FI/yKM46ABagk6erToSKvu/oaTT/BsQJAR6oPq65ML4O1" +
            "z8OTqGRSxJnSj7NGRNq/+MUWN/UNI5WsCNVMrYgwMtPqLzVBddvnconEvNE0/LsVA4A82RZohwJA" +
            "YDMNLdY4GqWKpkTf93G04D+saN2hpKZOV/hifVUOO2uuGEYarlExa/8eoxI1rgU/ttuTiDz8BOWE" +
            "YeONhD/2bQ==";
}
