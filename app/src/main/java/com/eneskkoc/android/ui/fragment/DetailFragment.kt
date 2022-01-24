package com.eneskkoc.android.ui.fragment

import android.util.Log
import com.eneskkoc.android.R
import com.eneskkoc.android.base.BaseAdapter
import com.eneskkoc.android.base.BaseFragment
import com.eneskkoc.android.data.model.similarmodel.Result
import com.eneskkoc.android.databinding.FragmentDetailBinding
import com.eneskkoc.android.databinding.ItemSimilarBinding
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment :  BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    override val clazz: Class<DetailViewModel> = DetailViewModel::class.java
    override val layoutResourceId: Int = R.layout.fragment_detail
    private lateinit var adapter: BaseAdapter<ItemSimilarBinding, Result>

    override fun init() {
        super.init()
        adapter = object : BaseAdapter<ItemSimilarBinding, Result>(R.layout.item_similar) {
            override fun bindView(binding: ItemSimilarBinding, item: Result?, adapterPosition: Int) {
                binding.viewModel = item
            }

        }
        arguments.let { bundle ->
            val id: Int? = bundle?.let { arg -> DetailFragmentArgs.fromBundle(arg).position }
            id?.let { movie_id -> viewModel.movie(movie_id)}
            id?.let { viewModel.similar(it) }

        }
        binding.viewModel = viewModel
        recyclerviewSimilar.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner, { state ->
            when (state) {
                is DetailViewModel.State.OnCompleted -> onMovie()
                is DetailViewModel.State.OnError -> onMessage(state.error)
            }
        })

    }
    private fun onMovie() {
        binding.tvDesc.text=viewModel.overview
        binding.tvRating.text=viewModel.rating.toString()
        binding.tvTitle.text=viewModel.title
        binding.tvDay.text=viewModel.date
        val movie = viewModel.similar?.toCollection(ArrayList())
        adapter.items = movie
    }

    private fun onMessage(error: String) {
        Log.e("backend hatası", error)

    }

}