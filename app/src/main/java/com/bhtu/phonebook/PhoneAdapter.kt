package com.bhtu.phonebook

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale
import kotlin.math.min

class PhoneAdapter(
    private val phoneItems: List<PhoneItem>,
    private val context: Context
): RecyclerView.Adapter<PhoneItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneItemViewHolder {
        return PhoneItemViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.phone_item_in_list, parent, false))
    }

    override fun getItemCount(): Int {
        return phoneItems.size
    }

    override fun onBindViewHolder(holder: PhoneItemViewHolder, position: Int) {
        val name = phoneItems[position].fullName
        holder.nameView.text = name
        holder.imageView.setImageDrawable(TextDrawable(name[0]))
    }
}

private class TextDrawable(
    private val char: Char
): Drawable() {
    private val foregroundPaint = Paint()
    private val backgroundPaint = Paint()

    init {
        this.foregroundPaint.color = Color.WHITE
        this.foregroundPaint.textSize = 40f
        this.foregroundPaint.isAntiAlias = true
        this.foregroundPaint.textAlign = Paint.Align.CENTER
        this.foregroundPaint.typeface = Typeface.DEFAULT_BOLD

        this.backgroundPaint.color = Color.GRAY
        this.backgroundPaint.style = Paint.Style.FILL
    }

    override fun draw(canvas: Canvas) {
        // draw bg
        val centerX = this.bounds.width() / 2
        val centerY = this.bounds.height() / 2
        val radius = min(centerX, centerY)
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat(), backgroundPaint)

        // draw text
        this.foregroundPaint.textSize = radius.toFloat() * 0.8f
        canvas.drawText(
            this.char.toString().uppercase(Locale.ROOT),
            centerX.toFloat(),
            centerY - ((foregroundPaint.descent() + foregroundPaint.ascent()) / 2),
            this.foregroundPaint
        )
    }

    override fun setAlpha(alpha: Int) {
        this.foregroundPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        this.foregroundPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

}