package com.monoprogram.myblend.presentation.top.blend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monoprogram.myblend.Application
import com.monoprogram.myblend.entity.Blend
import com.monoprogram.myblend.entity.Herb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyRecipeViewModel : ViewModel() {

    val herbInfo: LiveData<List<Herb>> get() = _herbInfo
    val blendInfo: LiveData<List<Blend>> get() = _blendInfo
    val defaultBlend: LiveData<List<Blend>> get() = _defaultBlend

    private val herbDao = Application.database.herbDao()
    private val blendDao = Application.database.blendDao()

    private val _herbInfo = MutableLiveData<List<Herb>>()
    private val _blendInfo = MutableLiveData<List<Blend>>()
    private val _defaultBlend = MutableLiveData<List<Blend>>()
    private val selectList: ArrayList<Herb> = arrayListOf()

    private val defaultInitRecipe: List<Blend> =
        listOf(
            Blend(
                0,
                "default1",
                "Mint,Lemongrass,LemonVerbena,LemonMyrtle,RoseHip,Stevia",
                "3,2,2,1,1,1"
            ),
            Blend(
                0,
                "default2",
                "RoseHip,Hibiscus,Stevia",
                "5,3,2"
            ),
            Blend(
                0,
                "default3",
                "Mint,Lemongrass,LemonVerbena,LemonMyrtle,RoseHip,Stevia",
                "3,2,2,1,1,1"
            ),
            Blend(
                0,
                "default4",
                "RoseHip,Hibiscus,Stevia",
                "5,3,2"
            )
        )

    fun onCreateHerbInfo() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                _herbInfo.postValue(herbDao.getAll())
            }
        }
    }

    fun onUpdateBlendInfo() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                _blendInfo.postValue(blendDao.getAll())
            }
        }
    }

    fun onSelectedHerbList(list: ArrayList<String>) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                list.forEach {
                    selectList.add(herbDao.getHerb(it))
                }
                _herbInfo.postValue(selectList)
            }
        }
    }

    fun onClickedSave(herbList: List<String>, valueList: List<Int>) {
        val herb = herbList.joinToString(",")
        val value = valueList.joinToString(",")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                blendDao.insert(Blend(0, "test_" + blendDao.getAll().size, herb, value))
            }
        }
    }

    fun setDefaultRecipe() {
        _defaultBlend.postValue(defaultInitRecipe)
    }
}