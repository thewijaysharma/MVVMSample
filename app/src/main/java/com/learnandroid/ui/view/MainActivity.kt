package com.learnandroid.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.learnandroid.PreferenceUtil
import com.learnandroid.R
import com.learnandroid.base.BaseActivity
import com.learnandroid.base.Status
import com.learnandroid.base.UIState
import com.learnandroid.rest.ApiService
import com.learnandroid.ui.viewmodel.MainViewModel
import com.learnandroid.util.showToast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.System.setProperty
import kotlin.math.log

class MainActivity : BaseActivity() {

    val pref : PreferenceUtil by inject()
    val apiService : ApiService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUsers.setOnClickListener {

            val param = "paramValue"
            val viewModel  by viewModel<MainViewModel>{ parametersOf(param)}

            viewModel.fetchUsers().observe(this@MainActivity, Observer {
                when(it.status){
                    Status.LOADING -> { showToast("Show progress")  }
                    Status.SUCCCESS -> {
                        it.data?.forEach { userModel ->
                            Log.d("MainActivity","${userModel.id} \t ${userModel.title} \n" )
                        }
                        showToast("Success")

                    }
                    Status.ERROR -> {showToast(it.message ?: "")   }

                }
            })
        }


    }

}
