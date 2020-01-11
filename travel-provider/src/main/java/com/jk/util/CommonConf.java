/**
 * <pre>项目名称:maven-project-four
 * 文件名称:CommonConf.java
 * 包名:cn.guobx.com.jk.common
 * 创建日期:2019年8月9日下午5:26:46
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre>
 */
package com.jk.util;

/**
 * <pre>项目名称：maven-project-four
 * 类名称：CommonConf
 * 类描述：
 * 创建人：Guo BaoXiang
 *
 * 励志语录: 业精于勤荒于嬉 行成于思毁于随
 *
 * 创建时间：2019年8月9日 下午5:26:46
 * 修改人：Guo BaoXiang
 * 修改时间：2019年8月9日 下午5:26:46
 * 修改备注：
 * @version </pre>
 */
public class CommonConf {
    //redis Key 部门缓存key
    public static final String DEPT_CACHE = "deptCache";

    //地区缓存key
    public static final String AREA_CACHE = "areaCache";

    //左侧权限树缓存key
    public static final String POWER_TREE_CACHE = "powerTreeCache";

    //缓存权限拦截器
    public static final String POWER_CACHE = "powerCache";

    //缓存角色
    public static final String ROLE_CACHE = "roleCache";

    //网易短信平台接口地址
    public static final String WANG_YI_SMS = "https://api.netease.im/sms/sendcode.action";

    //网易app key
    public static final String WANG_YI_APP_KEY = "f7d770400b7d53a146552310cb1fc91c";//需要改为自己的

    //网易App Secret
    public static final String WANG_YI_APP_SECRET = "010d4b143f22";//需要改为自己的

    //网易短信模板id
    public static final String WANG_YI_TEMPLATEID = "14839173";//需要改为自己的

    //短信验证码缓存key
    public static final String SSM_CODE_CACHE = "ssmCode";

    //一分钟内不能重复获取短信锁
    public static final String SSM_CODE_LOCK = "ssmCodeLock";

    public static final String TEST_URL = "test1";


}
