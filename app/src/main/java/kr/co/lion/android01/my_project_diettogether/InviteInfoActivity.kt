package kr.co.lion.android01.my_project_diettogether

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityInviteInfoBinding
import kr.co.lion.android01.my_project_diettogether.databinding.MyMemberBinding

class InviteInfoActivity : AppCompatActivity() {

    lateinit var activityInviteInfoBinding: ActivityInviteInfoBinding

    //invite에서 받아온다
    lateinit var inviteActivitylauncher : ActivityResultLauncher<Intent>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInviteInfoBinding = ActivityInviteInfoBinding.inflate(layoutInflater)
        setContentView(activityInviteInfoBinding.root)

        initData()
        setToolBar()
        initView()

    }

    fun initData(){
        var contract = ActivityResultContracts.StartActivityForResult()
        inviteActivitylauncher = registerForActivityResult(contract){
            if (it.resultCode == RESULT_OK){
                if (it.data != null){
                    var str1 = it?.data!!.getIntExtra("obj2",0)

                    Util.inviteList.removeAt(str1)
                    activityInviteInfoBinding.recyclerview3.adapter?.notifyDataSetChanged()
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        activityInviteInfoBinding.apply {
            ReCyclerViewAdapter()
            recyclerview3.adapter?.notifyDataSetChanged()
        }
    }


    fun setToolBar(){
        activityInviteInfoBinding.apply {
            materialToolbar6.apply {
                //타이틀
                title = "참여 목록"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 떄
                setNavigationOnClickListener {
                    finish()
                }
            }
        }

    }
    //어댑터 객체를 받는다
    fun initView(){
        activityInviteInfoBinding.apply {
            recyclerview3.apply {
                //어댑터 객체 생성
                adapter = ReCyclerViewAdapter()
                //레이아웃 설정
                layoutManager = LinearLayoutManager(this@InviteInfoActivity)
                //데코
                var deco = MaterialDividerItemDecoration(this@InviteInfoActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }

    }



    //어댑터 클래스
    inner class ReCyclerViewAdapter : RecyclerView.Adapter<ReCyclerViewAdapter.ViewHolderClass>() {


        //viewHolderClass
        inner class ViewHolderClass(myMemberBinding: MyMemberBinding):RecyclerView.ViewHolder(myMemberBinding.root){
            var myMemberBinding:MyMemberBinding
            init {
                this.myMemberBinding = myMemberBinding

                //가로 세로 길이를 같게한다
                this.myMemberBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                //화면을 터치 했을 때 다른 화면을 보여준다
                this.myMemberBinding.root.setOnClickListener {
                    var newIntent = Intent(this@InviteInfoActivity, ShowActivity::class.java)
                    newIntent.putExtra("obj1" ,adapterPosition)
                    inviteActivitylauncher.launch(newIntent)

                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            //viewBinding
            var myMemberBinding = MyMemberBinding.inflate(layoutInflater)
            //뷰홀더
            var viewHolder = ViewHolderClass(myMemberBinding)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return Util.inviteList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            var invite = Util.inviteList[position]
            when(invite.gender){
                Mygender.MAN_GENDER -> {
                    holder.myMemberBinding.inviteGenderText.text = "남자"
                }
                Mygender.GIRL_GENDER -> {
                    holder.myMemberBinding.inviteGenderText.text = "여자"
                }
            }
            holder.myMemberBinding.inviteAgeText.text = "${invite.age}살"
        }
    }

}

















































