package kr.co.lion.android01.my_project_diettogether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }
}