package com.ffn.zerozeroseven.utlis;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UserInfoUtils {
    /**
     * 保存用户名和密码的业务方法
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean saveUserInfo(Context context, String username,
                                       String password) {
        try {
            // 使用Android上下问获取当前项目的路径
            File file = new File(context.getFilesDir(), "userinfo.txt");
            // 创建输出流对象
            FileOutputStream fos = new FileOutputStream(file);
            // 向文件中写入信息
            if(!TextUtils.isEmpty(password)){
                fos.write((username + "##" + password).getBytes());
            }else{
                fos.write((username + "##" + "nopwd").getBytes());
            }
            // 关闭输出流对象
            fos.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    /**
     * 获取普通txt文件信息
     *
     * @param context
     * @return
     */
    public static Map<String, String> getTxtFileInfo(Context context) {
        try {
            // 创建FIle对象
            File file = new File(context.getFilesDir(), "userinfo.txt");
            // 创建FileInputStream对象
            FileInputStream fis = new FileInputStream(file);
            // 创建BufferedReader对象
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            // 获取文件中的内容
            String content = br.readLine();
            // 创建Map集合
            Map<String, String> map = new HashMap<>();
            // 使用保存信息使用的##将内容分割出来
            String[] contents = content.split("##");
            // 保存到map集合中
            map.put("username", contents[0]);
            map.put("password", contents[1]);
            // 关闭流对象
            fis.close();
            br.close();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
