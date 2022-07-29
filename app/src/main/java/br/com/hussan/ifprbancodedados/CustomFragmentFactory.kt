package br.com.hussan.ifprbancodedados

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import br.com.hussan.ifprbancodedados.fragments.ShowSerieFragment

class CustomFragmentFactory() : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        if (className == ShowSerieFragment::class.java.name) {
            return ShowSerieFragment()
        }
        return super.instantiate(classLoader, className)
    }
}