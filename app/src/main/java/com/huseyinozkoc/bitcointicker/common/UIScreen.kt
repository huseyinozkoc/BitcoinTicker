package com.huseyinozkoc.bitcointicker.common

sealed interface UIScreen {
    data class SnackBar(val message: String) : UIScreen
}