package com.monoprogram.myblend.presentation.top.blend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.monoprogram.myblend.Application
import com.monoprogram.myblend.R
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
                "ミントベース",
                Application.instance.getString(R.string.Mint) + "," +
                        Application.instance.getString(R.string.Lemongrass) + "," +
                        Application.instance.getString(R.string.LemonVerbena) + "," +
                        Application.instance.getString(R.string.LemonMyrtle) + "," +
                        Application.instance.getString(R.string.RoseHip) + "," +
                        Application.instance.getString(R.string.Stevia),
                "3,2,2,1,1,1",
                R.drawable.default_mint,
                "すっきりとした味わいのハーブティー",
                ""
            ),
            Blend(
                0,
                "ハイビスカスベース",
                Application.instance.getString(R.string.Hibiscus) + "," +
                        Application.instance.getString(R.string.RoseHip) + "," +
                        Application.instance.getString(R.string.Stevia),
                "5,3,2",
                R.drawable.default_hibiscus,
                "酸味と甘みがあるハーブティー",
                ""
            ),
            Blend(
                0,
                "レモングラスベース",
                Application.instance.getString(R.string.Lemongrass) + "," +
                        Application.instance.getString(R.string.LemonMyrtle) + "," +
                        Application.instance.getString(R.string.Stevia),
                "5,3,2",
                R.drawable.default_lemongrass,
                "レモンの香りで癒されるハーブティー",
                ""
            ),
            Blend(
                0,
                "ルイボスベース",
                Application.instance.getString(R.string.Rooibos) + "," +
                        Application.instance.getString(R.string.RoseHip) + "," +
                        Application.instance.getString(R.string.LemonVerbena) + "," +
                        Application.instance.getString(R.string.Stevia),
                "4,3,2,1",
                R.drawable.default_rooibos,
                "ごくごく飲めるハーブティー",
                ""
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

    fun onClickedSave(herbList: List<String>, valueList: List<Int>, blendName: String) {
        val herb = herbList.joinToString(",")
        val value = valueList.joinToString(",")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                val herbImageList: ArrayList<Int> = arrayListOf()
                herbList.forEach { herbImageList.add(herbDao.getHerb(it).imageId) }
                val image = herbImageList.joinToString(",")

                blendDao.insert(
                    Blend(
                        0,
                        blendName,
                        herb,
                        value,
                        0,
                        "",
                        image
                    )
                )
            }
        }
    }

    fun setDefaultRecipe() {
        _defaultBlend.postValue(defaultInitRecipe)
    }
}