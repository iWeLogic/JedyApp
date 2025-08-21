package com.iwelogic.projects.presentation.ui.details

sealed class MovieDetailsIntent {
    data object OnClickReload : MovieDetailsIntent()
}