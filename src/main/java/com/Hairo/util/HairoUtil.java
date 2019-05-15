package com.Hairo.util;

import com.Hairo.pojo.SysUsers;
import com.Hairo.service.PermissionService;
import com.Hairo.service.SysUserService;
import com.Hairo.service.impl.PermissionServiceImpl;
import com.Hairo.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/16 0:56
 * 作用描述:
 * 辅助类
 */
@SuppressWarnings("all")
@Transactional(rollbackFor = Exception.class)//统一事务
public class HairoUtil {
    public static final Integer PAGESIZE = 15;
    /**
     * 邮箱验证
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * Ip验证
     * @param addr
     * @return
     */
    public static boolean isIP(String addr)
    {
        if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
        {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(addr);

        boolean ipAddress = mat.find();

        return ipAddress;
    }


    /**
     * 验证用户
     * @param user
     * @return
     */
    public static SysUsers verifyUser(SysUsers user){
        if(user == null  || user.getU_password()==null || user.getU_name()==null ||Integer.valueOf(user.getU_password())!=32){
            return null;
        }else {
            return new SysUserServiceImpl().getUserByName(user.getU_name());
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public synchronized static Map result(Integer i){
        Map map = new HashMap();
        if(i==0){
            map.put("success",0);
            map.put("message","操作失败");
        }else if(i==1){
            map.put("success",1);
            map.put("message","操作成功");
        }else if(i==2){
            map.put("success",0);
            map.put("message","操作失败,权限不足");
        }else if(i==3){
            map.put("success",0);
            map.put("message","操作失败,用户名或密码错误");
        }else if(i==-1){
            map.put("success",0);
            map.put("message","操作失败,非法操作");
        }else{
            map.put("success",0);
            map.put("message","操作失败,未知异常,请稍后再试");
        }
        return  map;
    }

    //查询权限
    public synchronized static boolean permissionQuery(Integer userId){
        PermissionService permissionService = new PermissionServiceImpl();
        String role = permissionService.PermissionQuery(userId);
        if(role==null || !"admin".equals(role)){
            return false;
        }
        return true;
    }



//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//    @RequestMapping(value = "/db",method = RequestMethod.GET)
//    public String b(){
//        StringRedisTemplate redisTemplate = getRedisTemplate();
//        ValueOperations opsForValue = redisTemplate.opsForValue();
//        opsForValue.set("测试","测试s");
//        System.out.println(opsForValue.get("测试")+"********************");
//        return "OK";
//    }
//
//    /**
//     * 切换Redis数据库
//     * RedisDB-3作为公共缓存DB
//     * @return
//     */
//    private StringRedisTemplate getRedisTemplate() {
//        LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) stringRedisTemplate.getConnectionFactory();
//        jedisConnectionFactory.setDatabase(3);
//        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
//        jedisConnectionFactory.resetConnection();//重置数据库s
//        return stringRedisTemplate;
//    }
}
