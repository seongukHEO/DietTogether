package kr.co.lion.android01.my_project_diettogether

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.my_project_diettogether.databinding.ActivitySearchPwactivityBinding

class SearchPWActivity : AppCompatActivity() {
    lateinit var activitySearchPwactivityBinding: ActivitySearchPwactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchPwactivityBinding = ActivitySearchPwactivityBinding.inflate(layoutInflater)
        setContentView(activitySearchPwactivityBinding.root)

        setToolBar()
        setEvent()
    }
    //툴바 설정
    fun setToolBar(){
        activitySearchPwactivityBinding.apply {
            materialToolbar8.apply {
                //타이틀
                title = "비밀번호 찾기"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.last_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    finish()

                    true
                }
            }
        }
    }
    //이벤트 설정
    fun setEvent(){
        activitySearchPwactivityBinding.apply {
            searchPWbutton.setOnClickListener {
                var info1 = intent.getStringExtra("obj3")
                var info2 = intent.getStringExtra("obj4")
                var info3 = intent.getStringExtra("obj5")

                var searchId = searchPWidText.text.toString()
                var searchNumber = searchPWnumberText.text.toString()

                if (searchId == info1 && searchNumber == info3){
                    searchPWText.text = "비밀번호 : ${info2}"
                }else{
                    enum.showDiaLog(this@SearchPWActivity, "회원 정보 오류", "정보에 맞는 비밀번호가 없습니다"){ dialogInterface: DialogInterface, i: Int ->
                        enum.showSoftInput(searchPWidText, this@SearchPWActivity)
                    }
                }
            }
        }
    }
}