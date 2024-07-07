// CustomButton.kt
package com.example.app_login.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.example.app_login.R


/**
 * drawableRight与文本一起居中显示
 */
class CustomButton(context: Context?, attrs: AttributeSet?) :
    AppCompatButton(context!!, attrs) {
    private var picDistance = 0
    private var picWidth = 0
    private var picHeight = 0

    init {
        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomButton)
        picDistance = ta.getDimensionPixelOffset(R.styleable.CustomButton_dl_dis, 0)
        picWidth = ta.getDimensionPixelOffset(R.styleable.CustomButton_dl_width, 0)
        picHeight = ta.getDimensionPixelOffset(R.styleable.CustomButton_dl_height, 0)
        ta.recycle()
    }


    override fun onDraw(canvas: Canvas) {
        var canvas = canvas
        canvas = getTopCanvas(canvas)
        super.onDraw(canvas)
    }

    private fun getTopCanvas(canvas: Canvas): Canvas {
        val drawables = compoundDrawables
            ?: return canvas
        var isLeftDrawable = true //是否左边的drawable
        var drawable = drawables[0] // 左面的drawable

        if (drawable == null) {
            drawable = drawables[2] // 右面的drawable
            isLeftDrawable = false
        }

        if (drawable == null) {
            return canvas
        }


        var isNeedScan = true

        if (picWidth == (drawable as BitmapDrawable).bitmap.width) {
            //不缩放
            isNeedScan = false
        }

        var bitmap: Bitmap? = null
        bitmap = if (isNeedScan) {
            createScaledBitmap(drawableToBitmap(drawable), picWidth, picHeight)
        } else {
            drawable.bitmap
        }

        val newDrawable: Drawable = BitmapDrawable(null, bitmap)
        newDrawable.setBounds(0, 0, bitmap!!.width, bitmap.height)
        if (isLeftDrawable) {
            setCompoundDrawables(newDrawable, null, null, null)
        } else {
            setCompoundDrawables(null, null, newDrawable, null)
        }

        val textSize = paint.measureText(text.toString())
        val drawWidth = bitmap.width
        val contentWidth = textSize + drawWidth + picDistance
        val paddingRight = (width - contentWidth).toInt()
        setPadding(0, 0, paddingRight, 0) // 直接贴到左边
        val dx = (width - contentWidth) / 2
        canvas.translate(dx, 0f) // 往右移动
        return canvas
    }


    private fun createScaledBitmap(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        val scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, bitmap.config)

        val scaleX = newWidth / bitmap.width.toFloat()
        val scaleY = newHeight / bitmap.height.toFloat()

        val scaleMatrix = Matrix()
        scaleMatrix.setScale(scaleX, scaleY, 0f, 0f)

        val canvas = Canvas(scaledBitmap)
        canvas.setMatrix(scaleMatrix)
        val paint = Paint(Paint.FILTER_BITMAP_FLAG)
        paint.isAntiAlias = true
        paint.isDither = true
        paint.isFilterBitmap = true
        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        return scaledBitmap
    }


    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val config = if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888
        else Bitmap.Config.RGB_565
        val bitmap = Bitmap.createBitmap(width, height, config)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        return bitmap
    }
}