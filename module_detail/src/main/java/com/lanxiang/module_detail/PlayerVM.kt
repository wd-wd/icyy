package com.lanxiang.module_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lanxiang.comlib.BaseApplication
import com.lanxiang.comlib.TilTokDTO
import java.io.InputStream
import java.nio.charset.Charset

/**
 * @auhthor: wangdong
 * @e-mail: 690931210@qq.com
 * @date: 2021/5/3 9:49 AM
 * @desc:
 *
 */
class PlayerVM : ViewModel() {
    val tikTokDTO: MutableLiveData<MutableList<TilTokDTO>> by lazy { MutableLiveData<MutableList<TilTokDTO>>() }
    fun getList() {
        tikTokDTO.postValue(convertData())

    }

    private fun convertData(): MutableList<TilTokDTO>? {
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
