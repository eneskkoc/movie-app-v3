package com.eneskkoc.android.ui.fragment

import android.util.Log
import androidx.navigation.Navigation
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.eneskkoc.android.R
import com.eneskkoc.android.base.BaseAdapter
import com.eneskkoc.android.base.BaseFragment
import com.eneskkoc.android.data.model.comingmodel.Result
import com.eneskkoc.android.data.model.searchmodel.SearchResult
import com.eneskkoc.android.databinding.FragmentMainBinding
import com.eneskkoc.android.databinding.ItemComingBinding
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment<MainFragmentViewModel, FragmentMainBinding>() {

    override val clazz: Class<MainFragmentViewModel> = MainFragmentViewModel::class.java
    override val layoutResourceId: Int = R.layout.fragment_main
    private lateinit var adapter: BaseAdapter<ItemComingBinding, Result>


    override fun init() {
        super.init()
        adapter = object : BaseAdapter<ItemComingBinding, Result>(R.layout.item_coming) {
            override fun bindView(binding: ItemComingBinding, item: Result?, adapterPosition: Int) {
                binding.viewModel = item
            }

            override fun clickListener(item: Result?, position: Int, binding: ItemComingBinding) {
               openDetail(viewModel.comResult?.get(position)?.id)

            }
        }
        viewModel.coming()
        binding.viewModel = viewModel
        recyclerviewComing.adapter = adapter
        viewModel.now()
        viewModel.data.observe(viewLifecycleOwner, { state ->
            when (state) {
                is MainFragmentViewModel.State.OnCompleted -> onComing()
                is MainFragmentViewModel.State.OnNow -> onNow()
                is MainFragmentViewModel.State.OnNowDetail -> onDetail()
                is MainFragmentViewModel.State.OnSearch -> onSearch(state.searchResult!!)
                is MainFragmentViewModel.State.OnError -> onMessage(state.error)
            }
        })
        binding.floatingSearchView.setOnQueryChangeListener { oldQuery, newQuery ->
            if (!oldQuery.equals("") && newQuery.equals("")) {
                binding.floatingSearchView.clearSuggestions()
            } else {
                binding.floatingSearchView.apply {
                    showProgress()
                    if (query.length > 1) {
                        viewModel.search(query)
                    }
                }

            }
        }
        binding.floatingSearchView.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
                //detay ekranı  açılacak
                searchSuggestion?.body?.let {
                    openDetail(viewModel.findSearchItem(it)?.id)

                }
            }

            override fun onSearchAction(currentQuery: String?) {

            }

        })


    }
    private fun openDetail(id:Int?){
        id?.let {

            Navigation.findNavController(requireView()).navigate(  MainFragmentDirections.actionMainFragmentToDetailFragment(it))
        }
    }

    private fun onSearch(searhResult: List<SearchResult>) {

        binding.floatingSearchView.apply {
            swapSuggestions(searhResult)
            hideProgress()
        }
    }

    private fun onDetail() {
       openDetail(viewModel.id)
    }

    private fun onNow() {
        val imageSlider = binding.imageSlider
        imageSlider.setImageList(viewModel.imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                viewModel.nowId(position)
            }
        })

    }

    private fun onComing() {
        val movie = viewModel.comResult?.toCollection(ArrayList())
        adapter.items = movie

    }

    private fun onMessage(error: String) {
        Log.e("backend hatası", error)

    }

}