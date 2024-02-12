package kr.co.lion.android01.my_project_diettogether

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.lion.android01.my_project_diettogether.databinding.ActivitySearchIdBinding

class SearchIdActivity : AppCompatActivity() {
    lateinit var activitySearchIdBinding: ActivitySearchIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchIdBinding = ActivitySearchIdBinding.inflate(layoutInflater)
        setContentView(activitySearchIdBinding.root)

        setEvent()
        setToolBar()
    }

    //툴바 설정
    fun setToolBar() {
        activitySearchIdBinding.apply {
            materialToolbar7.apply {
                //타이틀
                title = "아이디 찾기"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 눌렀을때
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }

    //이벤트 설정
    fun setEvent() {
        activitySearchIdBinding.apply {
            searchIdButton.setOnClickListener {
                var info = intent.getStringExtra("obj1")
                var info2 = intent.getStringExtra("obj2")
                //Log.e("test1234", "${info}")
                // Log.e("test1234", "${info2}")

                var number = searchIdNumberText.text.toString()

                if (number == info2){
                    Log.e("test1234", "${info2}")
                    SearchIdResult.text = "아이디 : ${info}"
                }else{
                    enum.showDiaLog(this@SearchIdActivity, "아이디 정보 없음", "현재 휴대폰 번호에 저장된 아이디가 없습니다"){ dialogInterface: DialogInterface, i: Int ->
                        enum.showSoftInput(searchIdNumberText, this@SearchIdActivity)
                    }
                }
            }
        }
    }
}































