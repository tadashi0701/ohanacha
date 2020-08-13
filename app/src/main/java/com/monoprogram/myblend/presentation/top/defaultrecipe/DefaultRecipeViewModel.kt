package com.monoprogram.myblend.presentation.top.defaultrecipe

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

class DefaultRecipeViewModel : ViewModel() {

    val herbInfo: LiveData<List<Herb>> get() = _herbInfo
    val defaultRecipe: LiveData<List<Pair<String, Int>>> get() = _defaultRecipe
    private val _herbInfo = MutableLiveData<List<Herb>>()
    private val _defaultRecipe = MutableLiveData<List<Pair<String, Int>>>()
    private val herbDao = Application.database.herbDao()
    private val blendDao = Application.database.blendDao()
    private val selectList: ArrayList<Herb> = arrayListOf()

    private val defaultRecipe1: ArrayList<Pair<String, Int>> = arrayListOf(
        Pair("chamomile", 0), Pair("jasmine", 1), Pair("jasmine", 2)
    )

    private val defaultRecipe2: ArrayList<Pair<String, Int>> = arrayListOf(
        Pair("chamomile", 0), Pair("jasmine", 1), Pair("jasmine", 2),
        Pair("lemongrass", 3), Pair("mint", 4), Pair("rosemary", 5),
        Pair("sage", 6), Pair("thyme", 7)
    )

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

    fun onClickedDefault(defaultId: Int) {
        when (defaultId) {
            1 -> {
                _defaultRecipe.postValue(defaultRecipe1)
            }
            else -> {
                _defaultRecipe.postValue(defaultRecipe2)
            }
        }
    }

    fun onClickedSave(herbList: List<String>, valueList: List<Int>) {
        val herb = herbList.joinToString(",")
        val value = valueList.joinToString(",")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                blendDao.insert(Blend(0, "test", herb, value))
            }
        }
    }

}