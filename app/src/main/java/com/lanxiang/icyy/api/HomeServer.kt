package com.lanxiang.icyy.api

import com.lanxiang.icyy.dto.HomeRecommendDTO
import com.lanxiang.netlibrary.BaseDTO
import retrofit2.http.GET

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/28 1:33 PM
 * @desc:推荐
 *
 */
interface HomeServer {
    @GET("/api/v5/index/tab/allRec")
    suspend fun getRecommendList():HomeRecommendDTO
}