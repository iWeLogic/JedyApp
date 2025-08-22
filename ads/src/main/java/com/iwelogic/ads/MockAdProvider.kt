package com.iwelogic.ads

import android.app.Activity
import com.google.android.gms.ads.nativead.NativeAd


object MockAdProvider : AdProvider {

    override fun init(activity: Activity) {
    }

    override fun loadNativeAd(adUnitId: String, onAdLoaded: (NativeAd) -> Unit) {

    }


    override fun loadInterstitial(adUnitId: String) {

    }

    override fun showInterstitial(onShown: (() -> Unit)?) {

    }
}
