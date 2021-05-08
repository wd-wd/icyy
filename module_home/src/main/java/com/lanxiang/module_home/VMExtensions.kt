package com.lanxiang.module_home

import androidx.lifecycle.ViewModel
import com.lanxiang.icyy.api.HomeServer
import com.lanxiang.module_home.dto.HomeFeedDTO
import com.lanxiang.netlibrary.ApiServiceProvider
import com.lanxiang.netlibrary.getApiService

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/7 7:39 PM
 * @desc:
 *
 */
      suspend fun ViewModel.getHomeList(): HomeFeedDTO {
          return ApiServiceProvider.getApiService<HomeServer>().getFeedList()
      }

