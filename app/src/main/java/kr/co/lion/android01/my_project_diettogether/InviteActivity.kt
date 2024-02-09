package kr.co.lion.android01.my_project_diettogether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityInviteBinding

class InviteActivity : AppCompatActivity() {

    lateinit var activityInviteBinding: ActivityInviteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInviteBinding = ActivityInviteBinding.inflate(layoutInflater)
        setContentView(activityInviteBinding.root)

        setToolBar()
        setEvent()
        setView()
    }

    //툴바 설정
    fun setToolBar(){
        activityInviteBinding.apply {
            materialToolbar4.apply {
                //타이틀 설정
                title = "참여하기"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }

    //뷰 설정
    fun setView(){
        activityInviteBinding.apply {
            if(radioButton10.isChecked){
                weightText.isVisible = true
            }else{
                weightText.isVisible = false
            }
            if (favoriteSwitch.isChecked){
                checkBox.isVisible = true
                checkBox2.isVisible = true
                checkBox3.isVisible = true
                checkBox4.isVisible = true
            }else{
                checkBox.isVisible = false
                checkBox2.isVisible = false
                checkBox3.isVisible = false
                checkBox4.isVisible = false
            }
        }

    }

    //이벤트 설정
    fun setEvent(){

    }

    //유효성 검사
    fun checkOK(){

    }
}



























































