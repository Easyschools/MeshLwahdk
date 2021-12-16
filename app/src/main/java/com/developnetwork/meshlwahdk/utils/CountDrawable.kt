package com.developnetwork.meshlwahdk.utils
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.developnetwork.meshlwahdk.R

class CountDrawable(context: Context?) : Drawable() {
    private lateinit var mBadgePaint: Paint
    private lateinit var mTextPaint: Paint
    private val mTxtRect: Rect = Rect()

    private var mCount = ""
    private var mWillDraw = false

    init {
        if (context != null) {
            val mTextSize: Float = context.resources.getDimension(R.dimen._10ssp)
            mBadgePaint = Paint()
            mBadgePaint.color = ContextCompat.getColor(
                context.applicationContext,
                R.color.red
            )
            mBadgePaint.isAntiAlias = true
            mBadgePaint.style = Paint.Style.FILL
            mTextPaint = Paint()
            mTextPaint.color = Color.WHITE
            mTextPaint.typeface = Typeface.DEFAULT
            mTextPaint.textSize = mTextSize
            mTextPaint.isAntiAlias = true
            mTextPaint.textAlign = Paint.Align.CENTER
        }
    }

    override fun draw(canvas: Canvas) {
        if (!mWillDraw) {
            return
        }
        val bounds: Rect = bounds
        val width: Float = (bounds.right - bounds.left).toFloat()
        val height: Float = (bounds.bottom - bounds.top).toFloat()

        // Position the badge in the top-right quadrant of the icon.

        /*Using Math.max rather than Math.min */
        val radius = Math.max(width, height) / 2 / 2
        val centerX = width - radius - 1 + 5
        val centerY = radius - 5
        if (mCount.length <= 2) {
            // Draw badge circle_accent.
            canvas.drawCircle(centerX, centerY, (radius + 5.5).toFloat(), mBadgePaint)
        } else {
            canvas.drawCircle(centerX, centerY, (radius + 6.5).toFloat(), mBadgePaint)
        }
        // Draw badge count text inside the circle_accent.
        mTextPaint.getTextBounds(mCount, 0, mCount.length, mTxtRect)
        val textHeight: Float = (mTxtRect.bottom - mTxtRect.top).toFloat()
        val textY = centerY + textHeight / 2f
        if (mCount.length > 2) canvas.drawText(
            "99+",
            centerX,
            textY,
            mTextPaint
        ) else canvas.drawText(mCount, centerX, textY, mTextPaint)
    }

    /*
    Sets the count (i.e notifications) to display.
     */
    fun setCount(count: String) {
        mCount = count

        // Only draw a badge if there are notifications.
        mWillDraw = !count.equals("0", ignoreCase = true)
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        // do nothing
    }

    override fun setColorFilter(cf: ColorFilter?) {
        // do nothing
    }

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }
}