package com.example.animalkingdomexplorerapp

import android.content.Context
import android.widget.Toast

//This is the helper function library to allow for functions that are repeated alot to be grouped in one location and called in multiple places
fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()