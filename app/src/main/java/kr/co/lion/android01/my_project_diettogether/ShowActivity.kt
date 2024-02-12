package kr.co.lion.android01.my_project_diettogether

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        setToolBar()
        setEvent()
    }
    //툴바 설정
    fun setToolBar(){
        activityShowBinding.apply {
            materialToolbar9.apply {
                //타이틀
                title = "회원 정보 삭제"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 눌렀을 때
                setNavigationOnClickListener {
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.delect_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    var info1 = intent.getIntExtra("obj1", 0)
                    var diaLog = MaterialAlertDialogBuilder(this@ShowActivity).apply {
                        setTitle("회원 정보 삭제")
                        setMessage("회원 정보를 삭제하시겠습니까?")
                        setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            var newIntent = Intent()
                            newIntent.putExtra("obj2", info1)
                            setResult(RESULT_OK, newIntent)
                            finish()
                        }
                        setNegativeButton("취소"){ dialogInterface: DialogInterface, i: Int ->
                            dialogInterface.dismiss()
                        }
                    }
                    diaLog.show()


                    true
                }
            }
        }
    }
    //이벤트 설정 -> 출력처리
    fun setEvent(){
        activityShowBinding.apply {
            showText.apply {
                var info1 = intent.getIntExtra("obj1", 0)

                //와 이걸 잊고 있었네
                var invite = Util.inviteList[info1]

                //출력
                text = "성별 : ${invite.gender.str}\n"
                append("키 : ${invite.height}cm\n")
                append("나이 : ${invite.age}살\n")
                append("몸무게 : ${invite.weight.str}\n")
                append("좋아하는 운동 : ${invite.favoriteExercise}\n")
            }
        }

    }


}