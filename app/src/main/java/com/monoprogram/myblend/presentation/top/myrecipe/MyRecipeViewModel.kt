package com.monoprogram.myblend.presentation.top.myrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monoprogram.myblend.Application
import com.monoprogram.myblend.entity.Herb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyRecipeViewModel : ViewModel() {

    val herbInfo: LiveData<List<Herb>> get() = _herbInfo

    private val dao = Application.database.herbDao()
    private val _herbInfo = MutableLiveData<List<Herb>>()
    private var selectList: ArrayList<String> = arrayListOf()

    fun onUpdateHerbInfo() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                _herbInfo.postValue(dao.getAll())
            }
        }
    }

    fun onUpdateSelectList(list: ArrayList<String>) {
        selectList = list
    }
}