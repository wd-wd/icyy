package com.lanxiang.icyy

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanxiang.icyy.api.HomeServer
import com.lanxiang.icyy.dto.HomeRecommendDTO
import com.lanxiang.netlibrary.coroutines.result
import com.lanxiang.netlibrary.coroutines.safeLaunch

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/28 1:47 PM
 * @desc:
 *
 */
class MainModel : ViewModel() {
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