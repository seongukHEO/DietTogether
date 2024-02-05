package kr.co.lion.android01.my_project_diettogether

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityPrintInfoBinding

class PrintInfoActivity : AppCompatActivity() {

    lateinit var activityPrintInfoBinding: ActivityPrintInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPrintInfoBinding = ActivityPrintInfoBinding.inflate(layoutInflater)
        setContentView(activityPrintInfoBinding.root)

        setToolBar()
        printInfo()
    }

    //툴바 설정
    private fun setToolBar(){
        activityPrintInfoBinding.apply {
            materialToolbar3.apply {
                //타이틀 설정
                title = "회원 정보 관리"
                //뒤로 가기 아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                //메뉴 가져오기
                inflateMenu(R.menu.print_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    when(it.itemId){

                    }



                    true
                }
            }
        }
    }

    //정보 입력
    private fun printInfo(){
        activityPrintInfoBinding.apply {

            var newIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("obj2", MainMemberClass::class.java)
            }else{
                intent.getParcelableExtra<MainMemberClass>("obj2")
            }

            nonameText.setText("${newIntent?.name}")
            noNumberText.setText("${newIntent?.number}")
            noIdText.setText("${newIntent?.memberId}")
            noPwText.setText("${newIntent?.memberPw}")
        }
    }
}










































