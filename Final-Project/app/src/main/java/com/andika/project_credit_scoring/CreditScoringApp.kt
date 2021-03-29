package com.andika.project_credit_scoring

import android.app.Application
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.andika.project_credit_scoring.databinding.FragmentProfileBinding
import com.cloudinary.android.MediaManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CreditScoringApp : Application() {

    var config: HashMap<String, String> = HashMap()

    override fun onCreate() {
        super.onCreate()
        config["cloud_name"] = "nielnaga"
        config["api_key"] = "113567158897557"
        config["api_secret"] = "Xh9Kib5apF-v9_YV1aJ28cAKSLs"
        MediaManager.init(this, config);
    }
}