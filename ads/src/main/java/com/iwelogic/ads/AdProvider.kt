package com.iwelogic.ads

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.widget.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

interface AdProvider {

    fun clearNativeAdCache() {
        NativeAdCache.clear()
    }

    fun init(activity: Activity)

    fun loadNativeAd(adUnitId: String, onAdLoaded: (NativeAd) -> Unit)

    fun loadInterstitial(adUnitId: String)

    fun showInterstitial(onShown: (() -> Unit)?)
}

object NativeAdCache {
    val ads = mutableMapOf<String, NativeAd>()
    fun get(adUnitId: String) = ads[adUnitId]
    fun put(adUnitId: String, nativeAd: NativeAd) {
        ads[adUnitId] = nativeAd
    }
    fun clear() {
        ads.clear()
    }
}

@SuppressLint("InflateParams")
@Composable
fun AdProvider.NativeAdComposable(adUnitId: String, modifier: Modifier = Modifier) {
    var nativeAd by remember { mutableStateOf(NativeAdCache.get(adUnitId)) }

    LaunchedEffect(adUnitId) {
        if (nativeAd == null) {
            loadNativeAd(adUnitId) { loadedAd ->
                NativeAdCache.put(adUnitId, loadedAd)
                nativeAd = loadedAd
            }
        }
    }

    nativeAd?.let { ad ->
        AndroidView(
            factory = { context ->
                val adView = LayoutInflater.from(context)
                    .inflate(R.layout.item_native_ad, null) as NativeAdView

                adView.headlineView = adView.findViewById(R.id.ad_headline)
                adView.iconView = adView.findViewById(R.id.ad_app_icon)
                adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
                (adView.headlineView as TextView).text = ad.headline
                (adView.callToActionView as Button).text = ad.callToAction
                ad.icon?.let { (adView.iconView as ImageView).setImageDrawable(it.drawable) }

                adView.setNativeAd(ad)
                adView
            },
            modifier = modifier
        )
    }
}

