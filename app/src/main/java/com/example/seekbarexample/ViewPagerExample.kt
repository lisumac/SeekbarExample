package com.example.seekbarexample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ViewPagerExample : AppCompatActivity() {

    var viewPager: ViewPager? = null
    var adapter: Adapter? = null
    var btnPrev: Button? = null
    var btnNext:Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_example)
        btnPrev = findViewById(R.id.prev)
        btnNext = findViewById(R.id.next)

       btnPrev!!.visibility = View.VISIBLE


       setupViewPager()
    }

    private fun setupViewPager() {
        adapter = Adapter(this)
        viewPager = findViewById(R.id.viewpager)
        viewPager!!.adapter = adapter
    }

    class Adapter(var context: Context) : PagerAdapter() {
        var inflater: LayoutInflater? = null

        // list img
        var list_img = intArrayOf(
            R.drawable.kaget,
            R.drawable.molor,
            R.drawable.ngantuk
        )

        // list judul
        var list_judul = intArrayOf(
            R.string.judul_1,
            R.string.judul_2,
            R.string.judul_3
        )

        // list deskripsi
        var list_desk = intArrayOf(
            R.string.desk_1,
            R.string.desk_2,
            R.string.desk_3
        )

        // list color bg
        var list_bg = intArrayOf(context.resources.getColor(R.color.merah),context.resources.getColor(R.color.kuning),
            context.resources.getColor(R.color.hujau)
        )

        override fun getCount(): Int {
            return list_judul.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = inflater!!.inflate(R.layout.item_layout, container, false)
            val linearLayout = view.findViewById<LinearLayout>(R.id.item_layout)
            val imageView = view.findViewById<ImageView>(R.id.img)
            val judul = view.findViewById<TextView>(R.id.judul)
            val desk = view.findViewById<TextView>(R.id.deskripsi)
            linearLayout.setBackgroundColor(list_bg[position])
            imageView.setImageResource(list_img[position])
            judul.setText(list_judul[position])
            desk.setText(list_desk[position])
            container.addView(view)
            return view
        }

        override  fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as LinearLayout?)
        }
    }

    fun prev(view: View?) {
        viewPager!!.setCurrentItem(viewPager!!.currentItem - 1, true)
    }

    fun next(view: View?) {
        viewPager!!.setCurrentItem(viewPager!!.currentItem + 1, true)
    }
}