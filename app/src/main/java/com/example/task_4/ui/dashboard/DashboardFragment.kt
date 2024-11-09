package com.example.task_4.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.task_4.databinding.FragmentDashboardBinding
import kotlin.math.sqrt

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonCalculateTask2.setOnClickListener { Task2() }

        return root
    }

    private fun round(num: Double) = "%.4f".format(num)

    private fun Task2()
    {
        // P - Потужність трансформатора, кВт
        // SNom - Номінальна потужність трансформатора, МВА
        val P = 200.0
        val SNom = 6.3

        // Введення користувачем даних
        // Usn - середня номінальна напруги, кВ
        val Usn = binding.Usn.text.toString().toDouble()

        // Розрахунок опорів елементів заступної схеми та сумарного опору
        // Xc - опір елементів заступної схеми, Ом
        // Xt - опір елементів заступної схеми, Ом
        // X - сумарний опір, Ом
        val Xc = (Usn * Usn) / P
        val Xt = (Usn * Usn * Usn ) / SNom / 100

        val X = Xc + Xt

        // Розрахунок початкового діючого значення струму трифазного КЗ, А
        val Ip0 = Usn / sqrt(3.0) / X

        var output = "Опори елементів заступниї схеми: ${round(Xc)} Ом і ${round(Xt)} Ом\n" +
                "Сумарний опір: ${round(X)} Ом\n" +
                "Початкове діюче значення струму трифазного КЗ: ${round(Ip0)} А\n"

        binding.outputTask2.text = output;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}