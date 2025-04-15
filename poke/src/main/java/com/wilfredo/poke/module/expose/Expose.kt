package com.wilfredo.poke.module.expose

import android.content.Context
import android.content.Intent
import com.wilfredo.poke.module.ui.HostActivity

object Expose {
    @JvmStatic
    fun openModule(context: Context) {
        context.startActivity(Intent(context, HostActivity::class.java))
    }
}