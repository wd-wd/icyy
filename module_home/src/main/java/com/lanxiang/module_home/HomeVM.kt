package com.lanxiang.module_home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanxiang.icyy.api.HomeServer
import com.lanxiang.module_home.dto.HomeRecommendDTO
import com.lanxiang.netlibrary.ApiServiceProvider
import com.lanxiang.netlibrary.coroutines.safeLaunch
import com.lanxiang.netlibrary.getApiService

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/29 4:39 PM
 * @desc:
 *
 */
class HomeVM : ViewModel() {
    private val service = ApiServiceProvider.getApiService<HomeServer>()
    val homeRecommendDTO: MutableLiveData<HomeRecommendDTO> by lazy { MutableLiveData<HomeRecommendDTO>() }
    fun getList() {
        viewModelScope.safeLaunch {
            tryBlock {
                val data = service.getRecommendList()
                homeRecommendDTO.postValue(data)
            }
            catchError {

            }
        }
    }
}