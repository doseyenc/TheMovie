package com.example.themovie.common.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.reflect.ParameterizedType

abstract class BaseBottomSheetDialogFragment<VB: ViewBinding> : BottomSheetDialogFragment() {
    private var _binding: VB? = null

    val binding: VB get() = _binding!!

    val nullableBinding: VB? get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        _binding = method.invoke(null, layoutInflater, container, false) as VB
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    abstract fun init()

    fun navigate(action: NavDirections) {
        view?.findNavController()?.navigate(action)
    }

    fun hideNavigationBar() {
        val window = requireActivity().window
        WindowInsetsControllerCompat(window, window.decorView)
            .hide(WindowInsetsCompat.Type.navigationBars())
    }

    fun showNavigationBar() {
        val window = requireActivity().window
        WindowInsetsControllerCompat(window, window.decorView)
            .show(WindowInsetsCompat.Type.navigationBars())
    }

}