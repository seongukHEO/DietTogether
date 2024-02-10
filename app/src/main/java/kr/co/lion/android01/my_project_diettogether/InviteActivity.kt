package kr.co.lion.android01.my_project_diettogether

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
            //전부다 안보이게 한다
            weightText.isVisible = false
            checkBox.isVisible = false
            checkBox2.isVisible = false
            checkBox3.isVisible = false
            checkBox4.isVisible = false
            favoriteText.isVisible = false

            //라디오 버튼 10번째 것을 눌렀을 떄 보이게 한다
           radioButton10.setOnCheckedChangeListener { buttonView, isChecked -> 
               when(radioButton10.isChecked){
                   true -> {weightText.isVisible = true}
                   false -> {weightText.isVisible = false}
               }
           }
            //스위치를 on했을 때 보이게 한다
            favoriteSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                when(favoriteSwitch.isChecked){
                    true -> {
                        checkBox.isVisible = true
                        checkBox2.isVisible = true
                        checkBox3.isVisible = true
                        checkBox4.isVisible = true

                        checkBox4.setOnCheckedChangeListener { buttonView, isChecked ->
                            when (checkBox4.isChecked){
                                true -> {favoriteText.isVisible = true}
                                false -> {favoriteText.isVisible = false}
                            }
                        }
                    }
                    false -> {
                        FavoriteExercise.NEVER
                        checkBox.isVisible = false
                        checkBox2.isVisible = false
                        checkBox3.isVisible = false
                        checkBox4.isVisible = false
                    }
                }
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        activityInviteBinding.apply {
            //성별
            var str1 = when(toggleGroup2.checkedButtonId){
                R.id.mantoggleButton -> {
                    Mygender.MAN_GENDER
                }
                R.id.girlToggleButton -> {
                    Mygender.GIRL_GENDER
                }
                else -> Mygender.MAN_GENDER
            }
            //키
            var str2 = heightText.text.toString().toInt()
            //나이
            var str3 = ageText.text.toString().toInt()
            //몸무게
            var t1 = when(radioGroup.checkedRadioButtonId){
                R.id.radioButton -> Myweight.FOURDOWN
                R.id.radioButton2 -> Myweight.FOURFIVE
                R.id.radioButton3 -> Myweight.FIVESIX
                R.id.radioButton4 -> Myweight.SIXSEVEN
                R.id.radioButton5 -> Myweight.SEVENEIGHT
                R.id.radioButton6 -> Myweight.EIGHTNINE
                R.id.radioButton7 -> Myweight.NINEHUND
                R.id.radioButton8 -> Myweight.HUNDONEHUND
                R.id.radioButton9 -> Myweight.ONEHUNDOVER
                else -> R.id.radioButton10
            }
            var str4 = t1 as Myweight

           var t4 = activityInviteBinding.apply {
               checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                   if (checkBox.isChecked) {
                       FavoriteExercise.ILONEXERCISE
                   }
               }
               checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
                   if (checkBox2.isChecked){
                       FavoriteExercise.FILLATES
                   }
               }
               checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
                   if (checkBox3.isChecked){
                       FavoriteExercise.BALLEXERCISE
                   }
               }
           }
            var str5 = t4 as FavoriteExercise

            var str10 = InviteClass(str1, str2, str3, str4, str5)
            inviteProgramButton.setOnClickListener {
                var newIntent = Intent(this@InviteActivity, InviteInfoActivity::class.java)
                newIntent.putExtra("obj1", str10)
                setResult(RESULT_OK, newIntent)
                startActivity(newIntent)
            }

        }

    }

    //유효성 검사
    fun checkOK(){

    }
}



























































