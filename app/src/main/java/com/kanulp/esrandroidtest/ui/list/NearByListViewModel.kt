package com.kanulp.esrandroidtest.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanulp.esrandroidtest.data.model.NearByModel
import com.kanulp.esrandroidtest.repository.NearByRepository
import com.kanulp.esrandroidtest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NearByListViewModel @Inject constructor(private val repository: NearByRepository) : ViewModel() {

    private var _res = MutableLiveData<Resource<NearByModel>>()

    val res : LiveData<Resource<NearByModel>>
        get() = _res

    fun getNearByData() = viewModelScope.launch {
        Log.d("VIEWMODEL","Calling nearby fun")
        repository.getNearByData().let {
            if (it.data?.code ==200 ){
                Log.d("VIEWMODEL","Calling Viewmodel Data: $it")
                _res.postValue(Resource.success(it.data!!))
                Log.d("VIEWMODEL","Calling Viewmodel Data: ${it.status}")
            }else{
               Log.d("VIEWMODEL","Calling Viewmodel Error: ${it.data} | ${it.status} | ${it.message} ")
                _res.postValue(Resource.error(it.message.toString(), null))
            }
        }
    }

}