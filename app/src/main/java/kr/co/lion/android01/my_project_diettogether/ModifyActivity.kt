package kr.co.lion.android01.my_project_diettogether

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {
    lateinit var activityModifyBinding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityModifyBinding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(activityModifyBinding.root)

        setToolBar()
        printInfo()
    }

    //툴바 설정
    fun setToolBar(){
        activityModifyBinding.apply {
            materialToolbar10.apply {
                //타이틀 설정
                title = "동물 정보 수정"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 눌렀을 때
                setNavigationOnClickListener {
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.modify_menu)
                //버튼을 눌렀을 때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.modify_menu_click -> {
                            activityModifyBinding.apply {
                                var info2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                                    intent.getParcelableExtra("obj1", MainMemberClass::class.java)
                                }else{
                                    intent.getParcelableExtra<MainMemberClass>("obj1")
                                }
                                if (info2 != null){
                                    var name = modifyNameText.text.toString()
                                    var number = modifyNumberText.text.toString().toInt()
                                    var memberId = modifyIdText.text.toString()
                                    var memberPw = modifyPWText.text.toString()
                                    var str1 = MainMemberClass(name, number, memberId, memberPw)

                                    var newIntent = Intent()
                                    newIntent.putExtra("obj2", str1)
                                    setResult(RESULT_OK, newIntent)
                                    checkOK()
                                }
                            }
                        }
                    }
                    true
                }
            }
        }

    }

    //화면에 출력
    fun printInfo(){
        activityModifyBinding.apply {
            var info = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                intent.getParcelableExtra("obj1", MainMemberClass::class.java)
            }else{
                intent.getParcelableExtra<MainMemberClass>("obj1")
            }
            if (info != null){
                modifyNameText.setText("${info.name}")
                modifyNumberText.setText("${info.number}")
                modifyIdText.setText("${info.memberId}")
                modifyPWText.setText("${info.memberPw}")
            }
        }

    }

    //유효성 검사
    fun checkOK(){
        activityModifyBinding.apply {
            var name = modifyNameText.text.toString()
            if (name.trim().isEmpty()){
                enum.showDiaLog(this@ModifyActivity, "이름 입력 오류", "이름을 입력하시오"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(modifyNameText, this@ModifyActivity)
                }
                return
            }
            var number = modifyNumberText.text.toString()
            if (number.trim().isEmpty()){
                enum.showDiaLog(this@ModifyActivity, "휴대폰 번호 입력 오류", "휴대폰 번호를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(modifyNumberText, this@ModifyActivity)
                }
                return
            }
            var modifyId = modifyIdText.text.toString()
            if (modifyId.trim().isEmpty()){
                enum.showDiaLog(this@ModifyActivity, "아이디 입력 오류", "아이디를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(modifyIdText, this@ModifyActivity)
                }
                return
            }
            var modifyPW = modifyPWText.text.toString()
            if (modifyPW.trim().isEmpty()){
                enum.showDiaLog(this@ModifyActivity, "비밀번호 입력 오류", "비밀번호를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(modifyPWText, this@ModifyActivity)
                }
                return
            }
        }

        finish()
    }


}















































