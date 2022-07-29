package br.com.hussan.ifprbancodedados

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import br.com.hussan.ifprbancodedados.fragments.ListFragment

class MainActivity : AppCompatActivity(R.layout.activity_main)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_container_view, ListFragment())
            }
        }
    }
}