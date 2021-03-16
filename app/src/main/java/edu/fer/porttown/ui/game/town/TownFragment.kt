package edu.fer.porttown.ui.game.town

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import edu.fer.porttown.databinding.FragmentTownBinding
import edu.fer.porttown.model.helpers.Buildings

class TownFragment : Fragment() {
    private lateinit var binding: FragmentTownBinding
    private lateinit var buildingAdapter: FastItemAdapter<TownBuildingItem>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTownBinding.inflate(inflater, container, false)

        setupBuildingPicker()

        return binding.root
    }

    private fun setupBuildingPicker() {
        buildingAdapter = FastItemAdapter()
        buildingAdapter.onClickListener = { _, _, item, position ->
            // TODO: fragments for each building are still not done
            true
        }

        binding.buildingList.apply {
            layoutManager = LinearLayoutManager(this.context)
            itemAnimator = DefaultItemAnimator()
            adapter = buildingAdapter
        }

        // TODO: will need changing when backend is done
        buildingAdapter.add(Buildings.createAllBuildings().map { TownBuildingItem(it) })
    }
}