package kr.co.lion.android01.my_project_diettogether

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityPrintInfoBinding

class PrintInfoActivity : AppCompatActivity() {

    lateinit var activityPrintInfoBinding: ActivityPrintInfoBinding

    //modify에서 받아올 런쳐
    lateinit var modifyActivitylauncher:ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPrintInfoBinding = ActivityPrintInfoBinding.inflate(layoutInflater)
        setContentView(activityPrintInfoBinding.root)

        setView()
        setToolBar()
        printInfo()
    }

    //뷰 설정
    fun setView(){
        var contract = ActivityResultContracts.StartActivityForResult()
        modifyActivitylauncher = registerForActivityResult(contract){
            if (it.resultCode == RESULT_OK){
                if (it.data != null) {
                    val info2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it?.data!!.getParcelableExtra("obj2", MainMemberClass::class.java)
                    }else{
                        it?.data!!.getParcelableExtra<MainMemberClass>("obj2")
                    }
                    activityPrintInfoBinding.apply {
                        if (info2 != null){
                            nonameText.setText("${info2.name}")
                            noNumberText.setText("${info2.number}")
                            noIdText.setText("${info2.memberId}")
                            noPwText.setText("${info2.memberPw}")
                        }
                    }
                }
            }
        }
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
                    finish()
                }
                //메뉴 가져오기
                inflateMenu(R.menu.print_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.modify_menu -> {
                            var t1 = nonameText.text.toString()
                            var t2 = noNumberText.text.toString().toInt()
                            var t3 = noIdText.text.toString()
                            var t4 = noPwText.text.toString()

                            var addt = MainMemberClass(t1, t2, t3, t4)
                            var newIntent = Intent(this@PrintInfoActivity, ModifyActivity::class.java)
                            newIntent.putExtra("obj1", addt)
                            setResult(RESULT_OK, newIntent)
                            modifyActivitylauncher.launch(newIntent)
                        }
                        R.id.delect_menu -> {
                            var newIntent2 = Intent()
                            newIntent2.putExtra("obj2", MainMemberClass::class.java)
                            setResult(RESULT_OK, newIntent2)
                            finish()
                        }
                    }
                    true
                }
            }
            //버튼을 클릭했을 때
            modifyButton123.setOnClickListener {
                var t1 = nonameText.text.toString()
                var t2 = noNumberText.text.toString().toInt()
                var t3 = noIdText.text.toString()
                var t4 = noPwText.text.toString()

                var t5 = MainMemberClass(t1, t2, t3, t4)
                var newIntent = Intent(this@PrintInfoActivity, MainActivity::class.java)
                newIntent.putExtra("obj1", t5)
                setResult(RESULT_OK, newIntent)
                finish()
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










































