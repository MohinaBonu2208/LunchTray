package com.bignerdranch.android.lunchtime

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class OrderViewModel : ViewModel() {
    val menuItems = DataSource.menuItems

    var TAX = 0.20

    // Default values for item prices
    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree

    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<Double> = _subtotal

    fun setEntree(entree: String) {
        if (_entree.value != null) {
            previousEntreePrice = _entree.value!!.price
        }
        if (_subtotal.value != null) {
            _subtotal.value = subtotal.value?.minus(previousEntreePrice)
        }
        val selectedEntree = menuItems[entree]!!
        _entree.value = selectedEntree
        updateSubtotal(selectedEntree.price)
    }


    fun setSide(side: String) {
        if (_side.value != null) {
            previousSidePrice = _side.value!!.price
        }

        if (_subtotal.value != null) {
            _subtotal.value = _subtotal.value?.minus(previousSidePrice)
        }
        val selectedSideMeal = menuItems[side]!!
        _side.value = selectedSideMeal
        updateSubtotal(selectedSideMeal.price)

    }

    fun setAccompaniment(accompaniment: String) {
        if (_accompaniment.value != null) {
            previousAccompanimentPrice = _accompaniment.value!!.price
        }
        if (_subtotal.value != null) {
            _subtotal.value = _subtotal.value?.minus(previousAccompanimentPrice)
        }

        val selectedAccompaniment = menuItems[accompaniment]!!
        _accompaniment.value = selectedAccompaniment
        updateSubtotal(selectedAccompaniment.price)

    }

    private fun updateSubtotal(itemPrice: Double) {
        if (_subtotal.value != null) {
            _subtotal.value = itemPrice.plus(_subtotal.value!!)
        } else {
            _subtotal.value = itemPrice
        }

    }


    fun resetOrder() {
        _entree.value = null
        _side.value = null
        _accompaniment.value = null
        _subtotal.value = 0.0
        TAX = 0.0
    }
}