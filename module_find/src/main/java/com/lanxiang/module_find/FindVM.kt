package com.lanxiang.module_find

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lanxiang.comlib.BaseApplication
import java.io.InputStream
import java.nio.charset.Charset

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/1 11:57 AM
 * @desc:
 *
 */
class FindVM : ViewModel() {
    val tikTokDTO: MutableLiveData<MutableList<TilTokDTO>> by lazy { MutableLiveData<MutableList<TilTokDTO>>() }
    fun getList() {
        tikTokDTO.postValue(convertData())

    }

    private  fun convertData(): MutableList<TilTokDTO>? {
        val `is`: InputStream = BaseApplication.getInstance().assets.open("tiktok_data")
        val length = `is`.available()
        val buffer = ByteArray(length)
        `is`.read(buffer)
        `is`.close()
        val result = String(buffer, Charset.forName("UTF-8"))
        val type = object : TypeToken<List<TilTokDTO>>() {}.type
        val tokDTOs = Gson().fromJson<MutableList<TilTokDTO>>(result, type)
        return tokDTOs
    }
}