package com.iwelogic.ads

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd

class AdMobProvider() : AdProvider {

    private var interstitialAd: InterstitialAd? = null
    private var interstitialId = ""
    private lateinit var activity: Activity

    override fun init(activity: Activity) {
        this.activity = activity
    }

    override fun loadNativeAd(adUnitId: String, onAdLoaded: (NativeAd) -> Unit) {
        val adLoader = AdLoader.Builder(activity, adUnitId)
            .forNativeAd { nativeAd ->
                onAdLoaded(nativeAd)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(error: LoadAdError) {
                    Log.w("AdMobProvider", "Native Ad failed to load: $error")
                }
            })
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }


    override fun loadInterstitial(adUnitId: String) {
        interstitialId = adUnitId
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            activity,
            adUnitId,
            adRequest,
            object : com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            interstitialAd = null
                        }
                    }
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    interstitialAd = null
                }
            }
        )
    }

    override fun showInterstitial(onShown: (() -> Unit)?) {
        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {

            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                onShown?.invoke()
                loadInterstitial(interstitialId)
            }

            override fun onAdShowedFullScreenContent() {
                onShown?.invoke()
                interstitialAd = null
                loadInterstitial(interstitialId)
            }
        }
        interstitialAd?.show(activity) ?: run {
            loadInterstitial(interstitialId)
            onShown?.invoke()
        }
    }
}
