package com.example.myfirstapp.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.myfirstapp.R
import com.example.myfirstapp.model.Speaker
import com.example.myfirstapp.view.adapter.SpeakerAdapter
import com.example.myfirstapp.view.adapter.SpeakerListener
import com.example.myfirstapp.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment(), SpeakerListener {

    private lateinit var viewModel      : SpeakerViewModel
    private lateinit var speakerAdapter : SpeakerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)

        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = speakerAdapter
        }

        observeSpeaker()
    }



    private fun observeSpeaker() {
        viewModel.listSpeaker.observe(viewLifecycleOwner, Observer<List<Speaker>> { speaker ->
            speakerAdapter.updateData(speaker)
        })
    }


    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        var bundle = bundleOf("speaker" to speaker)

        findNavController().navigate(R.id.speakerDetailFragmentDialog, bundle)
    }


}
