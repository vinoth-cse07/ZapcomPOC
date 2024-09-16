package com.zcg.zapcompoc.home


import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.zcg.core_data.items.ItemSection
import com.zcg.core_data.repository.ItemsCallback
import com.zcg.core_usecase.items.ItemsUseCase
import com.zcg.zapcompoc.utils.Utility
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivityViewModel(
    private val itemsUseCase: ItemsUseCase,
) : ViewModel() {

    var itemsData = MutableLiveData<List<ItemSection>?>()
    var onErrorAction = MutableLiveData(false)
    private lateinit var activityLifecycleOwner: LifecycleOwner

    fun onActivityCreated(activityLifecycleOwner: LifecycleOwner) {
        this.activityLifecycleOwner = activityLifecycleOwner
        getItemsFromAPI()
    }

    private fun getItemsFromAPI() {
        viewModelScope.launch {
            itemsUseCase.fetchItems(
                object : ItemsCallback {

                    override fun onSuccess(itemsData: List<ItemSection>) {
                        this@MainActivityViewModel.itemsData.postValue(
                            itemsData
                        )
                    }

                    override fun onError(errorMessage: String) {
                        itemsData.postValue(null)
                    }

                    override fun onNetworkError() {
                        Utility.showAlert(activityLifecycleOwner as Activity,
                            {
                                getItemsFromAPI()
                            },
                            {
                                onErrorAction.postValue(true)
                            })
                    }

                    override fun onCancel() {}
                }
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    class MainViewModelFactory @Inject constructor(private val itemsUseCase: ItemsUseCase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainActivityViewModel(itemsUseCase) as T
        }
    }
}