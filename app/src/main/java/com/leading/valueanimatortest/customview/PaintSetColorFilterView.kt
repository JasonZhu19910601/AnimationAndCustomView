package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.leading.valueanimatortest.R

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName PaintSetColorFilterView
 * @date 2018/12/26 11:24
 * @author Zj
 * @describe 1.按钮点击时，动态加深图片色彩（简易方法）
 *            2.可以学到针对不同主题动态设置不同色彩图片的方法(setTint())
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class PaintSetColorFilterView : View {
    private lateinit var mPaint: Paint
    private lateinit var mBmp: Bitmap

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        mPaint = Paint()
//        mBmp = BitmapFactory.decodeResource(resources, R.drawable.sample)
        mBmp = BitmapFactory.decodeResource(resources, R.drawable.broom)
    }
//    1、ColorMatrixColorFilter
//    这个是色彩矩阵颜色过滤器，该类只有两个函数，也都是构造函数：
//    ColorMatrixColorFilter(ColorMatrix matrix)
//    ColorMatrixColorFilter(float[] array)
//    在这里可以直接传入一个ColorMatrix对象，也可以直接传入一个色彩矩阵。我们知道ColorMatrix对应的也是一个色彩矩阵。


    // 2、LightingColorFilter
    //前一篇，我们利用一篇的篇幅来讲解ColorMatrix的作用，所有需要完成色彩操作的都是可以利用ColorMatrix来完成的，
    // 只是有一点ColorMatrix纵然很强大，但太！过！难！用，所以Android为我们提供了一个简单过滤颜色和增强色彩的函数，
    // 就是LightingColorFilter
    //这个叫做光照颜色过滤器，可以简单的完成色彩过滤和色彩增强功能。
    //整个类就只有一个函数，还是构造函数：
    //public LightingColorFilter(int mul, int add)
    //这里有两个参数,mul是乘法multiply的缩写，add是加法的意思。mul和add取值都是0xRRGGBB,分别对应R、G、B颜色，
    // 注意哦，这里是没有透明度A的，透明度在这里是不起作用的，LightingColorFilter只针对RGB色值起作用
    //比如，当前有一个颜色值为（r,g,b），对它应用LightingColorFilter(mul, add)效果后的颜色值为：
    //结果R值 = (r*mul.R+add.R)%255;
    //结果G值 = (g*mul.G+add.G)%255;
    //结果B值 = (b*mul.B+add.B)%255;
    //前面我们讲了mul和add的取值都是0xRRGGBB类型的值，即mul和add中都是包含了R、G、B分量的；
    //在上面的公式中，三个颜色分量R、G、B值的方式都是一样的，我们只拿红色来讲：
    //结果R值 = (r*mul.R+add.R)%255;
    //作用LightingColorFilter(mul, add)效果后的R值等于，原来的r值乘以mul.R,然后再加上add.R做为最终结果。
    // 因为颜色值要的取值范围在0-255，所以要把结果对255取余，得到最终结果。
    //所以从公式中可以看出mul.R是对当前红色值进行放大的倍数；而add.R则表示对当前红色增加的数值；

//    利用mul进行颜色值放大并不好控制，所以更多的是用来过滤颜色，
// 即当对应的颜色值取0时，就不会将对应的颜色显示出来，而把要显示出来的颜色对应的mul值设置为ff,即255;
// 从公式中可以知道设置为255不会对原始的这个颜色分量产生任何影响。所以这样就可以把想要的颜色给显示出来，把不想要的颜色给过滤掉
//    比如，下面这个蓝色按钮：
//    我们可以在点击时让它变成绿色，这要怎么做呢？直接使用LightingColorFilter把其它颜色都过滤掉，只显示绿色就可以了：

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.isAntiAlias = true

//        val width = 200
        val width = 100
        var height = width * mBmp.height / mBmp.width
        canvas.drawColor(Color.GRAY)

        canvas.drawBitmap(mBmp, null, Rect(0, 0, width, height), mPaint)

//        canvas.translate(0f, 350f)
//        // 把其他颜色都过滤掉，只显示蓝色
//        mPaint.colorFilter = LightingColorFilter(0x00ff00, 0x000000)

//        // 增强颜色的蓝色值，将整个图片变得更蓝
//        // mul参数设置为0xffffff，即没有对颜色做任何改变；add参数设置为0x0000f0，即在每个像素的蓝色值在原来基础上增加0xf0，
//        // 让原来的图像变得更蓝；这样会显得整个图片的颜色更深。更像按压后的效果。
//        mPaint.colorFilter = LightingColorFilter(0xffffff, 0x0000f0)

        // 3、PorterDuffColorFilter
        //这个叫PorterDuff颜色滤镜，也叫图形混合滤镜；其名称是Tomas Proter和Tom Duff两个人名的缩写，他们提出的图形混合的概念极大地推动了图形图像学的发展。
        //这个颜色滤镜的声明如下：
        //public PorterDuffColorFilter(int srcColor, PorterDuff.Mode mode)
        //其中有两个参数：
        //int srcColor：0xAARRGGBB类型的颜色值。
        //PorterDuff.Mode mode：表示混合模式，枚举值有18个，表示各种图形混合模式,有：
        //Mode.CLEAR
        //Mode.SRC
        //Mode.DST
        //Mode.SRC_OVER
        //Mode.DST_OVER
        //Mode.SRC_IN
        //Mode.DST_IN
        //Mode.SRC_OUT
        //Mode.DST_OUT
        //Mode.SRC_ATOP
        //Mode.DST_ATOP
        //Mode.XOR
        //Mode.DARKEN
        //Mode.LIGHTEN
        //Mode.MULTIPLY
        //Mode.SCREEN
        //Mode.OVERLAY
        //Mode.ADD
        // 其实在这里跟我们相关的只有六个：Mode.ADD(饱和度相加)，Mode.DARKEN（变暗），Mode.LIGHTEN（变亮），
//        // Mode.MULTIPLY（正片叠底），Mode.OVERLAY（叠加），Mode.SCREEN（滤色）
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY)// 变暗

//        // 饱和度相加
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.ADD)
//        canvas.drawBitmap(mBmp, null, Rect(0, 0, width, height), mPaint)
//
//        canvas.translate(250f,0f)
//        // 变暗
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.DARKEN)
//        canvas.drawBitmap(mBmp,null, Rect(0,0,width,height),mPaint)
//
//        canvas.translate(-250f,250f)
//        // 变亮
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.LIGHTEN)
//        canvas.drawBitmap(mBmp,null, Rect(0,0,width,height),mPaint)
//
//        canvas.translate(250f,0f)
//        // 正片叠加
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY)
//        canvas.drawBitmap(mBmp,null,Rect(0,0,width,height),mPaint)
//
//        canvas.translate(-250f,250f)
//        // 叠加
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.OVERLAY)
//        canvas.drawBitmap(mBmp,null, Rect(0,0,width,height),mPaint)
//
//        canvas.translate(250f,0f)
//        // 滤色
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SCREEN)
//        canvas.drawBitmap(mBmp,null,Rect(0,0,width,height),mPaint)


        // 除了上面的六个Mode，还有其它的三组Mode，由于每组Mode的效果都是相同的，所以我们分组来讲
        //第一组：清空模式
        //Mode.CLEAR和Mode.XOR他们在这里的效果是完成一致的，就是把图像清空，
//        // 所以一旦应用他们两个中的任何一个，所得到的结果图像就是一个空图
//        canvas.translate(350f, 0f)
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED, PorterDuff.Mode.CLEAR)
//        canvas.drawBitmap(mBmp, null, Rect(0, 0, width, height), mPaint)
//
//        canvas.translate(-350f, 350f)
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED, PorterDuff.Mode.XOR)
//        canvas.drawBitmap(mBmp, null, Rect(0, 0, width, height), mPaint)


        // 第三组：源图模式
//        //在Mode模式中，有一组SRC相关的模式,SRC表示的颜色值所代表的图像，
//        // 这些模式有：Mode.SRC、Mode.SRC_IN、Mode.SRC_OUT、Mode.SRC_OVER、Mode.SRC_ATOP下面我们来看看他们的效果：
//        canvas.translate(250f,0f)
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SRC)
//        canvas.drawBitmap(mBmp,null, Rect(0,0,width,height),mPaint)
//
//        canvas.translate(-250f,250f)
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SRC_IN)
//        canvas.drawBitmap(mBmp,null, Rect(0,0,width,height),mPaint)
//
//        canvas.translate(250f,0f)
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SRC_OUT)
//        canvas.drawBitmap(mBmp,null,Rect(0,0,width,height),mPaint)
//
//        canvas.translate(-250f,250f)
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SRC_OVER)
//        canvas.drawBitmap(mBmp,null, Rect(0,0,width,height),mPaint)
//
//        canvas.translate(250f,0f)
//        mPaint.colorFilter = PorterDuffColorFilter(Color.RED,PorterDuff.Mode.SRC_ATOP)
//        canvas.drawBitmap(mBmp,null,Rect(0,0,width,height),mPaint)

        // 从效果图中可以看出，除了Mode.SRC_OUT显示完全透明图片以外，其它全部显示源图像；
        //利用这个特性，我们可以在不同情况下，改变一个纯色图标的颜色。
        // 这个也是V4包中DrawableCompat类添加的一个setLint()函数所使用实现方法
        //setTint(Drawable drawable, int tint)
        // 即最左边是一原图，后面都是指定的各个颜色，利用setTint就可以把一个图片渲染为不同的颜色，
        // 这样就可以支持多主题，在不同的风格和不同的情境下使用不同的颜色的图片。
        // 由于仅使用一个图片就可以实现多个主题，就不必再引入多个颜色的切图，就可以在一定程度上缩小包的大小。
        //我们不必引入V4包，仅仅通过PorterDuffColorFilter就可以实现setTint的功能:
        canvas.translate(110f, 0f)
        mPaint.colorFilter = PorterDuffColorFilter(0xffff00ff.toInt(), PorterDuff.Mode.SRC)
        canvas.drawBitmap(mBmp, null, Rect(0, 0, width, height), mPaint)

        canvas.translate(110f, 0f)
        mPaint.colorFilter = PorterDuffColorFilter(0xff00f0ff.toInt(), PorterDuff.Mode.SRC_ATOP)
        canvas.drawBitmap(mBmp, null, Rect(0, 0, width, height), mPaint)

        canvas.translate(110f, 0f)
        mPaint.colorFilter = PorterDuffColorFilter(0xfff0f0ff.toInt(), PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(mBmp, null, Rect(0, 0, width, height), mPaint)

        canvas.translate(110f, 0f)
        mPaint.colorFilter = PorterDuffColorFilter(0xffffff00.toInt(), PorterDuff.Mode.SRC_OVER)
        canvas.drawBitmap(mBmp, null, Rect(0, 0, width, height), mPaint)

        canvas.translate(110f, 0f)
        mPaint.colorFilter = PorterDuffColorFilter(0xff000000.toInt(), PorterDuff.Mode.SRC_ATOP)
        canvas.drawBitmap(mBmp, null, Rect(0, 0, width, height), mPaint)
        // 从效果图中可以看到，SRC相关的模式，只有Mode.SRC_ATOP和SRC_IN能够实现SetTint的功能，其它的是不行的。这里先记着就可以了，后面地讲原理时会具体讲原因。
        //所以这里的一个应用就是通过PorterDuffColorFilter的Mode.SRC_ATOP或SRC_IN模式实现SetTint()的功能；
        // 有些同学可能会讲，这个功能是不是可以通过ColorMatrix来实现？当然是可以的，比如我们要将原图标改成第三个效果，即颜色为0xff00f0ff，所对应的矩阵为：
        //
        //ColorMatrix matrix = new ColorMatrix(new float[]{
        //        0,0,0,0,0,
        //        0,0,0,0,240,
        //        0,0,0,0,255,
        //        0,0,0,1,0
        //});
        //可不可以看出其中的门道？把原图像中的R、G、B全部置为0，然后我们通过每行最后的那个位移参数来指定我们想指定的RGB色。
        //我们下面对PorterDuffColorFilter进行总结下：
        //1、PorterDuffColorFilter只能实现与一个特定颜色值的合成。
        //2、通过Mode.ADD(饱和度相加)，Mode.DARKEN（变暗），Mode.LIGHTEN（变亮），Mode.MULTIPLY（正片叠底），Mode.OVERLAY（叠加），Mode.SCREEN（滤色）可以实现与指定颜色的复合。
        //3、通过Mode.SRC、Mode.SRC_IN、Mode.SRC_ATOP能够实现setTint()的功能，可以改变纯色图标的颜色。
    }
}