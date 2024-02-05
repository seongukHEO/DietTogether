package kr.co.lion.android01.my_project_diettogether

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityNewMemberBinding

class NewMemberActivity : AppCompatActivity() {

    lateinit var activityNewMemberBinding: ActivityNewMemberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewMemberBinding = ActivityNewMemberBinding.inflate(layoutInflater)
        setContentView(activityNewMemberBinding.root)

        setToolBar()
        newMember()
    }

    //툴바 설정
    fun setToolBar(){
        activityNewMemberBinding.apply {
            materialToolbar2.apply {
                //타이틀 설정
                title = "회원가입"
                //뒤로가기 아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //뒤로가기를 눌렀을 때
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }

    //회원가입 버튼을 눌렀을 때
    fun newMember(){
        activityNewMemberBinding.apply {
            newMemberButton.setOnClickListener {
                var str1 = namwNewText.text.toString()
                var str244 = numberNewText.text.toString()
                var str3 = idNewText.text.toString()
                var str4 = pwNewText.text.toString()

                if (str1.isEmpty()){
                    showDiaLog("이름 오류", "이름을 다시 확인해주세요")
                }else if (str244.isEmpty()){
                    showDiaLog("핸드폰 번호 오류", "핸드폰 번호를 다시 확인해주세요")
                }else if (str3.isEmpty()){
                    showDiaLog("아이디 오류", "아이디를 다시 확인해주세요")
                }else if (str4.isEmpty() || checkpwNewText.text.toString() != str4){
                    showDiaLog("비밀번호 오류", "비밀번호를 다시 확인해주세요")
                }else {
                    var str2 = str244.toInt()

                    var str5 = MainMemberClass(str1, str2, str3, str4)

                    var newIntent = Intent()
                    newIntent.putExtra("obj1", str5)
                    setResult(RESULT_OK, newIntent)
                    finish()
                }
            }
        }
    }
    //다이아로그 생성
    fun showDiaLog(title:String, message:String){
        var viewDiaLog = MaterialAlertDialogBuilder(this@NewMemberActivity).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("확인"){diaLogInterface:DialogInterface, i:Int ->

            }
        }
        viewDiaLog.show()
    }
}




















































