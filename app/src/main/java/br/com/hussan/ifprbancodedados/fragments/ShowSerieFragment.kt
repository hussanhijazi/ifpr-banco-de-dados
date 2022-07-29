package br.com.hussan.ifprbancodedados.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.hussan.ifprbancodedados.ImageStreamingMapper
import br.com.hussan.ifprbancodedados.R
import br.com.hussan.ifprbancodedados.data.Serie
import br.com.hussan.ifprbancodedados.databinding.ShowSerieFragmentBinding

class ShowSerieFragment : Fragment(R.layout.add_serie_fragment) {

    private lateinit var binding: ShowSerieFragmentBinding
    private lateinit var imageStreamingMapper: ImageStreamingMapper

    companion object {
        const val SERIE_ARGS = "SERIE_ARGS"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShowSerieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageStreamingMapper = ImageStreamingMapper(context ?: return)

        val serie = requireArguments().getSerializable(SERIE_ARGS) as Serie

        binding.run {
            txtName.text = serie.name
            txtStreaming.text = serie.streaming

            val image = imageStreamingMapper.get(serie.streaming)

            if (image != null) {
                txtStreaming.setCompoundDrawablesWithIntrinsicBounds(
                    image,
                    0,
                    0,
                    0
                )
            }

        }
    }
}
