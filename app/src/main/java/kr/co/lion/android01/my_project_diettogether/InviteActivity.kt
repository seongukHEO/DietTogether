package kr.co.lion.android01.my_project_diettogether

import android.content.DialogInterface
import android.content.Intent
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
        setView()
    }


    //뷰 설정
    fun setView(){
        activityInviteBinding.apply {
            //전부다 안보이게 한다
            weightText.isVisible = false


            //라디오 버튼 10번째 것을 눌렀을 떄 보이게 한다
           radioButton10.setOnCheckedChangeListener { buttonView, isChecked -> 
               when(radioButton10.isChecked){
                   true -> {weightText.isVisible = true}
                   false -> {weightText.isVisible = false}
               }
           }
        }
    }
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
                activityInviteBinding.apply {
                    inviteProgramButton.setOnClickListener {
                        checkOK()

                    }
                    //와 해냈다 그러니까 checkOK안에 버튼을 클릭했을 때 어디로 넘어가야하는 지를 알려놓고
                    //다른 함수에서 그걸 호출한다
                }
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        activityInviteBinding.apply {
            //성별
            var str1 = InviteClass()
            str1.gender = when(toggleGroup2.checkedButtonId){
                R.id.mantoggleButton -> Mygender.MAN_GENDER
                R.id.girlToggleButton -> Mygender.GIRL_GENDER
                else -> Mygender.MAN_GENDER
            }

            //키
            str1.height = heightText.text.toString().toInt()


            //나이
            str1.age = ageText.text.toString().toInt()


            //몸무게
            str1.weight = when(radioGroup.checkedRadioButtonId){
                R.id.radioButton -> Myweight.FOURDOWN
                R.id.radioButton2 -> Myweight.FOURFIVE
                R.id.radioButton3 -> Myweight.FIVESIX
                R.id.radioButton4 -> Myweight.SIXSEVEN
                R.id.radioButton5 -> Myweight.SEVENEIGHT
                R.id.radioButton6 -> Myweight.EIGHTNINE
                R.id.radioButton7 -> Myweight.NINEHUND
                R.id.radioButton8 -> Myweight.HUNDONEHUND
                R.id.radioButton9 -> Myweight.ONEHUNDOVER
                R.id.radioButton10 -> Myweight.NEVER
                else -> Myweight.NEVER
            }

            str1.favoriteExercise = favoriteText.text.toString()

            Util.inviteList.add(str1)




        }

    }

    //유효성 검사
    fun checkOK(){
        activityInviteBinding.apply {
            //키를 입력하지 않았을 경우
            var emptyheight = heightText.text.toString()
            if (emptyheight.trim().isEmpty()){
                enum.showDiaLog(this@InviteActivity, "키 입력 오류", "키를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(heightText, this@InviteActivity)
                }
                return
            }
            //나이를 입력하지 않았을 경우
            var emptyAge = ageText.text.toString()
            if (emptyAge.trim().isEmpty()){
                enum.showDiaLog(this@InviteActivity, "나이 입력 오류", "나이를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(ageText, this@InviteActivity)
                }
                return
            }
            //좋아하는 운동을 입력하지 않았을 경우
            var emptyExercise = favoriteText.text.toString()
            if (emptyExercise.trim().isEmpty()){
                favoriteText.setText("좋아하는 운동이 없음")
            }

        }
        setEvent()
        activityInviteBinding.apply {
            inviteProgramButton.setOnClickListener {
                var newIntent = Intent(this@InviteActivity, InviteInfoActivity::class.java)
                startActivity(newIntent)

            }
            //와 해냈다 그러니까 checkOK안에 버튼을 클릭했을 때 어디로 넘어가야하는 지를 알려놓고
            //다른 함수에서 그걸 호출한다
        }

    }
}



























































