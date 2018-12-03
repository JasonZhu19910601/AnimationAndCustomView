package com.leading.valueanimatortest.customview

import android.content.Context
import android.graphics.*
import android.view.View

/**
 * @package com.leading.valueanimatortest.customview
 * @fileName MyRegionView
 * @date 2018/11/29 14:13
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class MyRegionView : View {
    constructor(context: Context?) : super(context)

    // 这是Region所具有的一系列Set方法，我这里全部列了出来，下面一一对其讲解：
    //注意：无论调用Set系列函数的Region是不是有区域值，当调用Set系列函数后，原来的区域值就会被替换成Set函数里的区域。
    //SetEmpty（）：从某种意义上讲置空也是一个构造函数，即将原来的一个区域变量变成了一个空变量，要再利用其它的Set方法重新构造区域。
    //set(Region region)：利用新的区域值来替换原来的区域
    //set(Rect r)：利用矩形所代表的区域替换原来的区域
    //set(int left, int top, int right, int bottom)：同样，根据矩形的两个点构造出矩形区域来替换原来的区域值
    //setPath(Path path, Region clip)：根据路径的区域与某区域的交集，构造出新区域，这个后面具体讲解
    // 举个小例子，来说明一个Set系列函数的替换概念：
    //
    //关于重写新建一个类，并派生自view，并且要重写OnDraw函数的问题我就不再讲了，有问题的同学，可以参考下《android Graphics（一）：概述及基本几何图形绘制》，当然最后我也会给出相关的源码，直接看源码也行。
    //
    //下面写了一个函数，先把Set函数注释起来，看看画出来的区域的位置，然后开启Set函数，然后再看画出来的区域
    //注：里面有个函数drawRegion(Canvas canvas,Region rgn,Paint paint),只知道它可以画出指定的区域就可以了，具体里面是什么意思，后面我们再仔细讲。
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 初始化画笔
        val paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f

//        // 构造一个椭圆路径
//        val ovalPath = Path()
//        val rectF = RectF(50f, 50f, 200f, 500f)
//        ovalPath.addOval(rectF, Path.Direction.CCW)
//        // SetPath时，传入一个比椭圆区域小的矩形区域，让其取交集
//        val region = Region()
//        region.setPath(ovalPath, Region(50, 50, 200, 500))

//        val region = Region(10, 10, 100, 100)
//        region.set(100, 100, 200, 200)

        // 区域的合并、交叉等操作
        //无论是区域还是矩形，都会涉及到与另一个区域的一些操作，比如取交集、取并集等，涉及到的函数有：
        //
        //public final boolean union(Rect r)   
        //public boolean op(Rect r, Op op) {  
        //public boolean op(int left, int top, int right, int bottom, Op op)   
        //public boolean op(Region region, Op op)   
        //public boolean op(Rect rect, Region region, Op op)   
        //
        //除了Union(Rect r)是指定合并操作以外，其它四个op（）构造函数，都是指定与另一个区域的操作。其中最重要的指定Op的参数，Op的参数有下面四个：
        //
        //假设用region1  去组合region2
        //public enum Op {
        //        DIFFERENCE(0), //最终区域为region1 与 region2不同的区域
        //        INTERSECT(1), // 最终区域为region1 与 region2相交的区域
        //        UNION(2),      //最终区域为region1 与 region2组合一起的区域
        //        XOR(3),        //最终区域为region1 与 region2相交之外的区域
        //        REVERSE_DIFFERENCE(4), //最终区域为region2 与 region1不同的区域
        //        REPLACE(5); //最终区域为为region2的区域
        // }
        // 构造两个矩形
        val rect1 = Rect(100, 100, 400, 200)
        val rect2 = Rect(200, 0, 300, 300)
        canvas.drawRect(rect1, paint)
        canvas.drawRect(rect2, paint)
        // 构造两个 region
        val region = Region(rect1)
        val region2 = Region(rect2)
        // 去两个区域的交集
        region.op(region2, Region.Op.INTERSECT)
        // 再构造一个画笔，填充 Region 操作结果。
        val paint_fill = Paint()
        paint_fill.color = Color.GREEN
        paint_fill.isAntiAlias = true
        paint_fill.style = Paint.Style.FILL

        // 画出路径
        drawRegion(canvas, region, paint_fill)
    }

    /**
     * 二、矩形集枚举区域——RegionIterator类
     * 对于特定的区域，我们都可以使用多个矩形来表示其大致形状。事实上，如果矩形足够小，一定数量的矩形就能够精确表示区域的形状，
     * 也就是说，一定数量的矩形所合成的形状，也可以代表区域的形状。
     * RegionIterator类，实现了获取组成区域的矩形集的功能，其实RegionIterator类非常简单，总共就两个函数，一个构造函数和一个获取下一个矩形的函数；
     * RegionIterator(Region region) //根据区域构建对应的矩形集
     * boolean	next(Rect r) //获取下一个矩形，结果保存在参数Rect r 中
     *
     *
     * /**几个判断方法*/
    public native boolean isEmpty();//判断该区域是否为空
    public native boolean isRect(); //是否是一个矩阵
    public native boolean isComplex();//是否是多个矩阵组合


    /**一系列的getBound方法，返回一个Region的边界*/
    public Rect getBounds()
    public boolean getBounds(Rect r)
    public Path getBoundaryPath()
    public boolean getBoundaryPath(Path path)


    /**一系列的判断是否包含某点 和是否相交*/
    public native boolean contains(int x, int y);//是否包含某点
    public boolean quickContains(Rect r)   //是否包含某矩形
    public native boolean quickContains(int left, int top, int right,
    int bottom) //是否没有包含某矩阵形
    public boolean quickReject(Rect r) //是否没和该矩形相交
    public native boolean quickReject(int left, int top, int right, int bottom); //是否没和该矩形相交
    public native boolean quickReject(Region rgn);  //是否没和该矩形相交

    /**几个平移变换的方法*/
    public void translate(int dx, int dy)
    public native void translate(int dx, int dy, Region dst);
    public void scale(float scale) //hide
    public native void scale(float scale, Region dst);//hide
     */
    private fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        val regionIterator = RegionIterator(region)

        val rect = Rect()
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint)
        }
    }
}