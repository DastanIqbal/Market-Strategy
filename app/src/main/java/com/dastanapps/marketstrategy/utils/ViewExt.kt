package com.dastanapps.marketstrategy.utils

import android.content.Context
import android.widget.Toast

/**
 *
 * Created by Iqbal Ahmed on 14/01/2024
 *
 */

fun Context.toast(msg: String){
    Toast.makeText(this, msg, android.widget.Toast.LENGTH_SHORT).show()
}