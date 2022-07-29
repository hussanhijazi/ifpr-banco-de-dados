package br.com.hussan.ifprbancodedados.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.hussan.ifprbancodedados.CustomFragmentFactory
import br.com.hussan.ifprbancodedados.R
import br.com.hussan.ifprbancodedados.SerieAdapter
import br.com.hussan.ifprbancodedados.data.Serie
import br.com.hussan.ifprbancodedados.data.SerieDAO
import br.com.hussan.ifprbancodedados.databinding.ListFragmentBinding

class ListFragment: Fragment(R.layout.list_fragment) {
    private lateinit var binding: ListFragmentBinding

    private val itemAdapter = SerieAdapter(mutableListOf(), ::showSerie)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val serieDao = SerieDAO.getInstance(context!!)
        val series = serieDao?.list()
        itemAdapter.addItems(series ?: emptyList())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentFragmentManager.fragmentFactory = CustomFragmentFactory()
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_container_view, AddSerieFragment())
                addToBackStack(null)
            }
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.listItems.run {
            adapter = itemAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun showSerie(serie: Serie) {
        parentFragmentManager.commit {
            val classLoader = activity?.classLoader ?: return@commit

            val bundle = bundleOf(ShowSerieFragment.SERIE_ARGS to serie)

            val fragment = parentFragmentManager.fragmentFactory.instantiate(
                classLoader,
                ShowSerieFragment::class.java.name
            )

            setReorderingAllowed(true)
            add(R.id.fragment_container_view, fragment::class.java, bundle)
            addToBackStack(null)
        }
    }
}