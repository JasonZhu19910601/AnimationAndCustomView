package com.leading.valueanimatortest.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import com.leading.valueanimatortest.R
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * @package com.leading.valueanimatortest.adapter
 * @fileName ListAdapter
 * @date 2018/11/20 15:17
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class ListAdapter(drawableList: ArrayList<Drawable>, length: Int, context: Context, listView: ListView) : BaseAdapter() {
    private var bottomInAnimation: Animation
    private var topInAnimation: Animation
    private var isScrollDown = false
    private var mFirstPosition = 0
    private var mFirstTop = 0
    private var isScrollUp = false

    /**
     * firstVisibleItem与getChildAt(int position)中的参数position的意义不同，firstVisibleItem 是指在整个ListView中的位置。
     * 而getChildAt(int position)中参数position传的是当前屏幕显示区域中item的索引，屏幕中第一个item的view可以通过getChildAt(0)得到。 
     *
     * 第一：屏幕中第一个item或前几个item一起移出屏幕，
     * 在这种情况下，我们只需要判断firstVisibleItem是否比上次的值大即可。即第一个显示的item是不是已经向下移了
     *
     * 第二：可能用户并没有一次性移一整条item，而是仅让当前item向上移了一点点。
     * 这里，由于当前可见的第一个item的位置仍然是firstVisibleItem，只是它的top值变了。
     *
     */
    private var mOnScrollListener = object : AbsListView.OnScrollListener {
        override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
            val firstChild = view?.getChildAt(0) ?: return
            val top = firstChild.top
            /**
             * firstVisibleItem > mFirstPosition表示向下滑动一整个Item
             * mFirstTop > top表示在当前这个item中滑动
             */
            isScrollDown = firstVisibleItem > mFirstPosition || mFirstTop > top
            isScrollUp = mFirstPosition > firstVisibleItem || top > mFirstTop
            mFirstTop = top
            mFirstPosition = firstVisibleItem
        }

        override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

        }

    }

    private var viewHolderCount = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder?
        var view: View? = convertView
        if (view === null) {
            holder = ViewHolder()
            view = mLayoutInfater.inflate(R.layout.item_layout, null)
            holder.mImageView = view.img
            holder.mTextView = view.text
            viewHolderCount++
            Log.e("viewHolderCount--> ", "" + viewHolderCount)
            view.setTag(holder)
        } else {
            holder = view.tag as ViewHolder
        }

        /**
         * 由于我们只能在Item生成时给这个Item添加动画，所以要解决多个item同时移动的问题，我们只能给最后一个Item添加动画，其它item不给他们添加；
         * 但我们怎么知道当前这个item是不是要显示的最后一个item呢？无法得各，
         * 所以一个中转方案是，在每一个item在添加动画前，都把当前显示区域内所有item动画给取消，然后给当前convertView添加上动画；
         * 当listview滚动到最后一个Item的时候，自然，同样也是先把所有动画取消，然后给他自己添加上动画，
         * 所以这样看起来就好像是只给他自己添加了动画，之前滚动的item是没有动画的。
         *
         * mListView.getChildAt(int position)的用法，说到它的参数position表示的是在当前屏幕显示区域中当前item的索引。
         * 所以在当前屏幕中第一个item的索引是0；而mListView.getChildCount()则表示当前屏幕显示区域中，总共有多少个item。
         * 所以我们利用上面的代码，对屏幕显示区域中每个item进行索引，然后取消他们的动画即可。
         */
        //清除当前显示区域中所有item的动画
        val childCount = mListView.childCount
        Log.e("childCount", "" + childCount)
        if (childCount > 0) {
            for (i in 0..childCount) {
                Log.e("tag", "" + i)
                val child = mListView.getChildAt(i)
                child?.clearAnimation()
            }
        }

        // 然后在getView的时候，判断如果是向下滑动，就添加动画
        if (isScrollDown) {
            view?.startAnimation(bottomInAnimation)
        }
        if (isScrollUp) {
            view?.startAnimation(topInAnimation)
        }

        holder.mImageView.setImageDrawable(mDrawableList[position % mDrawableList.size])
        holder.mTextView.text = "" + position
        return view!!
    }

    private var mDrawableList = drawableList
    private var mLength = length
    private var mLayoutInfater: LayoutInflater
    private var mContext: Context = context
    private var mListView: ListView = listView

    init {
        listView.setOnScrollListener(mOnScrollListener)
        this.mLayoutInfater = LayoutInflater.from(context)
        bottomInAnimation = AnimationUtils.loadAnimation(context, R.anim.bottom_in_anim)
        topInAnimation = AnimationUtils.loadAnimation(context, R.anim.top_in_anim)
    }


    override fun getItem(position: Int): Any {
        return mDrawableList[position % mDrawableList.size]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mLength
    }

    public class ViewHolder {
        public lateinit var mImageView: ImageView
        public lateinit var mTextView: TextView
    }
}