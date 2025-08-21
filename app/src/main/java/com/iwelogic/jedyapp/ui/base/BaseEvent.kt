package com.iwelogic.jedyapp.ui.base

sealed class BaseEvent {

    class ShowErrorMessage(val message: String) : BaseEvent()

    class ShowMessage(val message: String) : BaseEvent()
}
