package com.example.task_4.ui.notifications

import kotlin.math.*
import kotlin.math.pow
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.task_4.databinding.FragmentNotificationsBinding
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonCalculateTask3.setOnClickListener { Task3() }

        return root
    }

    private fun round(num: Double) = "%.4f".format(num)

    private fun Task3()
    {
        val SNom = 6.3
        val Uh = 115.0
        val Ul = 11.0
        val UkMax = 11.1
        val Rcn = 10.65
        val Xcn = 24.02
        val RcMin = 34.88
        val XCMin = 65.68

        val XT: Double = UkMax * pow(Uh, 2.0) / 100 / SNom
        val Xsh = Xcn + XT
        val Zsh = sqrt(pow(Rcn, 2.0) + pow(Xsh, 2.0))


        val XshMin = XCMin + XT
        val ZshMin = sqrt(pow(RcMin, 2.0) + pow(XshMin, 2.0))

        val Ish3 = Uh * 1000 / sqrt(3.0) / Zsh
        val Ish2 = Ish3 * sqrt(3.0) / 2

        val IshMin3 = Uh * 1000 / sqrt(3.0) / ZshMin
        val IshMin2 = IshMin3 * sqrt(3.0) / 2

        val Kpr: Double = pow(Ul, 2.0) / pow(Uh, 2.0)

        val RshN = Rcn * Kpr
        val XshN = Xsh * Kpr
        val ZshN = sqrt(pow(RshN, 2.0) + pow(XshN, 2.0))

        val RshMinN = RcMin * Kpr
        val XshMinN = XshMin * Kpr
        val ZshMinN = sqrt(pow(RshMinN, 2.0) + pow(XshMinN, 2.0))

        val IshN3 = Ul * 1000 / sqrt(3.0) / ZshN
        val IshN2 = IshN3 * sqrt(3.0) / 2

        val IshMinN3 = Ul * 1000 / sqrt(3.0) / ZshMinN
        val IshMinN2 = IshMinN3 * sqrt(3.0) / 2

        val Rl = 7.91
        val Xl = 4.49

        val RSumN = Rl + RshN
        val XSumN = Xl + XshN
        val ZSumN = sqrt(pow(RSumN, 2.0) + pow(XSumN, 2.0))

        val RSumMinN = Rl + RshMinN
        val XSumMinN = Xl + XshMinN
        val ZSumMinN = sqrt(pow(RSumMinN, 2.0) + pow(XSumMinN, 2.0))

        val Iln3 = Ul * 1000 / sqrt(3.0) / ZSumN
        val Iln2 = Iln3 * sqrt(3.0) / 2

        val IlMinN3 = Ul * 1000 / sqrt(3.0) / ZSumMinN
        val IlMinN2 = IlMinN3 * sqrt(3.0) / 2

        val output = """
        Опір на шинах в нормальному режимі: ${round(Zsh)} Ом
        Опір на шинах в мінімальному режимі: ${round(ZshMin)} Ом
        Сила трифазного струму на шинах в нормальному режимі: ${round(Ish3)} А
        Сила двофазного струму на шинах в нормальному режимі: ${round(Ish2)} А
        Сила трифазного струму на шинах в мінімальному режимі: ${round(IshMin3)} А
        Сила двофазного струму на шинах в мінімальному режимі: ${round(IshMin2)} А
        Коефіцієнт приведення для визначення дійсних струмів: ${round(Kpr)}
        Опір на шинах в нормальному режимі: ${round(ZshN)} Ом
        Опір на шинах в мінімальному режимі: ${round(ZshMinN)} Ом
        Сила трифазного струму на шинах в нормальному режимі: ${round(IshN3)} А
        Сила двофазного струму на шинах в нормальному режимі: ${round(IshN2)} А
        Сила трифазного струму на шинах в мінімальному режимі: ${round(IshMinN3)} А
        Сила двофазного струму на шинах в мінімальному режимі: ${round(IshMinN2)} А
        Опір в точці 10 в нормальному режимі: ${round(ZSumN)} Ом
        Опір в точці 10 в мінімальному режимі: ${round(ZSumMinN)} Ом
        Сила трифазного струму в точці 10 в нормальному режимі: ${round(Iln3)} А
        Сила двофазного струму в точці 10 в нормальному режимі: ${round(Iln2)} А
        Сила трифазного струму в точці 10 в мінімальному режимі: ${round(IlMinN3)} А
        Сила двофазного струму в точці 10 в мінімальному режимі: ${round(IlMinN2)} А
    """.trimIndent()

        binding.outputTask3.text = output;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}