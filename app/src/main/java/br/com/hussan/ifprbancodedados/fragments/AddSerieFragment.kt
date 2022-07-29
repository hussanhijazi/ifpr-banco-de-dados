package br.com.hussan.ifprbancodedados.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import br.com.hussan.ifprbancodedados.R
import br.com.hussan.ifprbancodedados.data.Serie
import br.com.hussan.ifprbancodedados.data.SerieDAO
import br.com.hussan.ifprbancodedados.databinding.AddSerieFragmentBinding
import com.google.android.material.snackbar.Snackbar

class AddSerieFragment: Fragment(R.layout.add_serie_fragment) {

    private lateinit var binding: AddSerieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddSerieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            btnAdd.setOnClickListener {
                saveSerie()
            }
        }
    }

    private fun saveSerie() = binding.run {
        val serieDao = SerieDAO.getInstance(context!!)

        val serie = Serie(-1, txtName.text.toString(), spinnerStreaming.selectedItem.toString())
        val id = serieDao?.save(serie)?.toInt()
        val inserted = id != null && id > -1

        if (inserted) {
            Snackbar.make(binding.root, R.string.serie_added, Snackbar.LENGTH_LONG).show()
            parentFragmentManager.popBackStack()
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container_view, ListFragment())
            }
        } else {
            Snackbar.make(binding.root, R.string.serie_error_to_add, Snackbar.LENGTH_LONG)
                .show()
        }
    }
}