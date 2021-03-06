package com.lanxiang.module_home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanxiang.module_home.dto.HomeFeedDTO
import com.lanxiang.module_home.model.HomeModel
import com.lanxiang.module_home.vo.FeedVO
import com.lanxiang.netlibrary.coroutines.safeLaunch

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/4/29 4:39 PM
 * @desc:
 *
 */
class HomeVM : ViewModel() {
    private val homeModel: HomeModel by lazy { HomeModel() }
    val homeRecommendDTO: MutableLiveData<MutableList<FeedVO>> by lazy { MutableLiveData<MutableList<FeedVO>>() }
    fun getList() {
        viewModelScope.safeLaunch {
            tryBlock {
                val homeList = homeModel.loadData()
//                val data = service.getFeedList()
                homeRecommendDTO.postValue(convert(homeList))
            }
            catchError {

            }
        }
    }

    private fun convert(feedDTO: HomeFeedDTO): MutableList<FeedVO> {
        val map =
            feedDTO.itemList.filter { it.data.content != null && it.data.content?.data?.author != null }
                .map {
                    FeedVO(
                        it.data.content?.data?.cover?.detail,
                        it.data.content?.data?.author?.icon,
                        it.data.content?.data?.author?.name + "/ #" + it.data.content?.data?.category,
                        it.data.content?.data?.title
                    )
                } as MutableList<FeedVO>
        return map
    }
}