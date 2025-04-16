package com.wilfredo.poke.module.ui.location

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wilfredo.poke.module.R
import com.wilfredo.poke.module.data.network.LocationService
import com.wilfredo.poke.module.databinding.FragmentLocationBinding

class LocationFragment : Fragment() {

    private val viewModel: LocationViewModel by viewModels()

    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: LocationAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                startLocationService()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.permission_denied_text), Toast.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
        initObservers()
    }

    private fun initElements() {

        if (!checkPermission()) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            startLocationService()
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnLocationStop.setOnClickListener {
            stopLocationService()
        }

        adapter = LocationAdapter()
        binding.rvLocations.adapter = adapter
    }

    private fun initObservers() {
        viewModel.locations.observe(viewLifecycleOwner) { locations ->
            adapter.submitList(locations)
        }
    }

    private fun startLocationService() {
        val intent = Intent(requireActivity(), LocationService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.root.context.startForegroundService(intent)
        } else {
            binding.root.context.startService(intent)
        }
    }

    private fun stopLocationService() {
        val intent = Intent(binding.root.context, LocationService::class.java)
        binding.root.context.stopService(intent)
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            binding.root.context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}