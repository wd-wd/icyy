package com.lanxiang.module_home.model

import com.lanxiang.comlib.model.BaseModel
import com.lanxiang.icyy.api.HomeServer
import com.lanxiang.module_home.dto.HomeFeedDTO
import com.lanxiang.netlibrary.ApiServiceProvider
import com.lanxiang.netlibrary.getApiService

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/23 8:41 AM
 * @desc:首页数据请求仓库，
 * 可能会有人觉的没必要直接写在viewmodel里面不好吗，但是这里要说明一下
 * 请求仓库是必要的，同一个请求可能在别的viewModel中使用，我们可以不用重复的写
 *  这里我定义的是model，也可以定义为repository
 */
class HomeModel : BaseModel<HomeFeedDTO> {
    suspend fun loadData(): HomeFeedDTO =
        ApiServiceProvider.getApiService<HomeServer>().getFeedList()

}