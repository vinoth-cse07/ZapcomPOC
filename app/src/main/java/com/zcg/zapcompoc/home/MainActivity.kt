package com.zcg.zapcompoc.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zcg.core_data.items.Item
import com.zcg.zapcompoc.ItemsApplication
import com.zcg.zapcompoc.R
import com.zcg.zapcompoc.adapters.ItemsAdapter
import com.zcg.zapcompoc.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainActivityViewModel.MainViewModelFactory
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }
    private lateinit var homeBinding: ActivityMainBinding
    private var adapter: ItemsAdapter? = null
    private var itemsList: MutableList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        (applicationContext as ItemsApplication).applicationComponent.inject(this)
        viewModel.onActivityCreated(this)
        setUpRecyclerView()
        observeItemsData()
    }

    private fun setUpRecyclerView() {
        adapter = ItemsAdapter(itemsList)
    }

    @SuppressLint("InflateParams", "NotifyDataSetChanged")
    private fun observeItemsData() {
        viewModel.apply {
            itemsData.observe(this@MainActivity) {
                if (homeBinding.parentLayout.childCount > 0){
                    homeBinding.parentLayout.removeAllViews()
                }

                it?.forEach { item ->
                    when (item.sectionType) {
                        "horizontalFreeScroll" -> {
                            val view =
                                layoutInflater.inflate(
                                    R.layout.horizontal_scroll_items_layout,
                                    null
                                )
                            val recyclerView =
                                view.findViewById<RecyclerView>(R.id.my_recycler_view)

                            recyclerView.adapter = adapter
                            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
                            recyclerView.layoutManager = linearLayoutManager.apply {
                                orientation = LinearLayoutManager.HORIZONTAL
                            }
                            itemsList.clear()
                            itemsList.addAll(item.items)
                            adapter?.notifyDataSetChanged()
                            addChild(view)

                        }

                        "splitBanner" -> {
                            val view =
                                layoutInflater.inflate(
                                    R.layout.split_image_items_layout,
                                    null
                                )
                            val imgView1 = view.findViewById<ImageView>(R.id.imageView1)
                            val imgView2 = view.findViewById<ImageView>(R.id.imageView2)
                            if (item.items.size >= 2) {
                                Glide.with(this@MainActivity)
                                    .load(item.items[0].image)
                                    .placeholder(R.drawable.pics)
                                    .into(imgView1)
                                Glide.with(this@MainActivity)
                                    .load(item.items[1].image)
                                    .placeholder(R.drawable.pics)
                                    .into(imgView2)
                            }
                            addChild(view)

                        }

                        "banner" -> {
                            val view =
                                layoutInflater.inflate(
                                    R.layout.banner_items_layout,
                                    null
                                )
                            val bannerImage = view.findViewById<ImageView>(R.id.bannerImage)
                            if (item.items.isNotEmpty()) {
                                Glide.with(this@MainActivity)
                                    .load(item.items[0].image)
                                    .placeholder(R.drawable.pics)
                                    .into(bannerImage)
                            }
                            addChild(view)

                        }
                    }
                }
            }

            onErrorAction.observe(this@MainActivity) {
                if (it) {
                    finishAndRemoveTask()
                }
            }
        }
    }

    private fun addChild(view: View){
        if (homeBinding.parentLayout.childCount != 0) {
            val params = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 70, 0, 0)
            view.layoutParams = params
        }
        homeBinding.parentLayout.addView(view, homeBinding.parentLayout.childCount)
    }
}