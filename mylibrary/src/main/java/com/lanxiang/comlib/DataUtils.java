package com.lanxiang.comlib;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/1 6:42 PM
 * @desc:
 */
public class DataUtils {
    public static <T> List<T> arrayBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }
}
