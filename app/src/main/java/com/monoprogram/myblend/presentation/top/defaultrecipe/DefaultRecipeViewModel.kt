package com.monoprogram.myblend.presentation.top.defaultrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monoprogram.myblend.Application
import com.monoprogram.myblend.entity.Herb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DefaultRecipeViewModel : ViewModel() {

    val herbInfo: LiveData<List<Herb>> get() = _herbInfo
    val defaultRecipe: LiveData<List<String>> get() = _defaultRecipe
    private val _herbInfo = MutableLiveData<List<Herb>>()
    private val _defaultRecipe = MutableLiveData<List<String>>()
    private val dao = Application.database.herbDao()
    private val selectList: ArrayList<Herb> = arrayListOf()

    private val defaultRecipe1 = arrayListOf(
        "chamomile", "jasmine", "lavendar"
    )

    private val defaultRecipe2 = arrayListOf(
        "chamomile", "jasmine", "lavendar",
        "lemongrass", "mint", "rosemary",
        "sage", "thyme"
    )

    fun onSelectedHerbList(list: ArrayList<String>) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                list.forEach {
                    selectList.add(dao.getHerb(it))
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

}