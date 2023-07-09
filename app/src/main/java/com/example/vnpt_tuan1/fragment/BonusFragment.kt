package com.example.vnpt_tuan1.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vnpt_tuan1.activity.AutoCompleteActivity
import com.example.vnpt_tuan1.activity.GridActivity
import com.example.vnpt_tuan1.databinding.FragmentBonusBinding

class BonusFragment : Fragment() {
    private lateinit var binding: FragmentBonusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBonusBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.grid.setOnClickListener{
            val intent = Intent(activity, GridActivity::class.java)
            startActivity(intent)
        }
        binding.autoComplete.setOnClickListener{
            val intent = Intent(activity, AutoCompleteActivity::class.java)
            startActivity(intent)
        }
    }
}