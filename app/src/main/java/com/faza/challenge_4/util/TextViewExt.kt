package com.faza.challenge_4.util

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
fun TextView.highLightWord(word: String, onClick: () -> Unit) {
    val ssBuilder = SpannableStringBuilder(this.text)
    val clickAbleSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            onClick.invoke()
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = ds.linkColor
            ds.isUnderlineText = false
        }
    }
    ssBuilder.setSpan(
        clickAbleSpan,
        text.indexOf(word),
        text.indexOf(word) + word.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = ssBuilder
    movementMethod = LinkMovementMethod.getInstance();
}