package br.com.rodriguesalex.tracker.presentation.trackerHome.presentation

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.rodriguesalex.tracker.R
import br.com.rodriguesalex.tracker.databinding.ActivityMainBinding
import com.robin.locationgetter.EasyLocation
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.observers.SafeObserver
import javax.inject.Inject

class TrackerActivity : DaggerAppCompatActivity(), EasyLocation.EasyLocationCallBack {


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var easyLocation: EasyLocation

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: TrackerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        vm = ViewModelProviders.of(this, factory).get(TrackerViewModel::class.java)
        binding.lifecycleOwner = this
        binding.vm = vm

        setupObservers()
        startEasy()
    }

    private fun startEasy() {
        EasyLocation(this, this)
    }

    private fun setupObservers() {
        vm.state.observe(this, Observer {state ->
            when (state) {
                is TrackerViewModelState.ResponseFromServer -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun getLocation(location: Location) {
        vm.update(location)
    }

    override fun locationSettingFailed() {
        Toast.makeText(this, "Algo falhou permissionamento", Toast.LENGTH_LONG).show()
    }

    override fun permissionDenied() {
        Toast.makeText(this, "Permissao negada", Toast.LENGTH_LONG).show()
    }
}
