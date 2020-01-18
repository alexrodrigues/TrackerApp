package br.com.rodriguesalex.tracker.presentation.trackerHome.presentation

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.rodriguesalex.tracker.R
import br.com.rodriguesalex.tracker.databinding.ActivityMainBinding
import com.robin.locationgetter.EasyLocation
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
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

        startEasy()
    }

    private fun startEasy() {
        EasyLocation(this, this)
    }

    override fun getLocation(location: Location) {
        vm.update(location)
    }

    override fun locationSettingFailed() {
    }

    override fun permissionDenied() {
    }
}
