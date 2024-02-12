package kr.co.lion.android01.my_project_diettogether

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityMainBinding
import kr.co.lion.android01.my_project_diettogether.databinding.MainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    //NewMemberActivity에서 올 런쳐
    lateinit var newMemberActivitylauncher:ActivityResultLauncher<Intent>

    //PrintActivity에서 사용할 런쳐
    lateinit var printActivitylauncher:ActivityResultLauncher<Intent>

    //modifyActivity에서 사용할 런쳐
    lateinit var modifyActivitylauncher:ActivityResultLauncher<Intent>

    //정보를 담아둘 리스트
    var memberList = mutableListOf<MainMemberClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        initData()
        setToolBar()
        setButton()
        initView()


    }


    fun initData(){
        var contract = ActivityResultContracts.StartActivityForResult()
        printActivitylauncher = registerForActivityResult(contract){
            if (it.resultCode == RESULT_OK){
                if(it.data != null){
                    var info2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it?.data!!.getParcelableExtra("obj2", MainMemberClass::class.java)
                    }else{
                        it?.data!!.getParcelableExtra<MainMemberClass>("obj2")
                    }

                }
            }

        }

        var contract2 = ActivityResultContracts.StartActivityForResult()
        newMemberActivitylauncher = registerForActivityResult(contract2){
            if (it.resultCode == RESULT_OK){
                if (it.data != null){
                    var info1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it?.data!!.getParcelableExtra("obj1", MainMemberClass::class.java)
                    }else{
                        it?.data!!.getParcelableExtra<MainMemberClass>("obj1")
                    }
                    memberList.add(info1!!)
                    activityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
                }
            }

        }
        var contract3 = ActivityResultContracts.StartActivityForResult()
        modifyActivitylauncher = registerForActivityResult(contract3){
            if (it.resultCode == RESULT_OK){
                if (it.data != null){
                    var info3 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        it?.data!!.getParcelableExtra("obj1", MainMemberClass::class.java)
                    }else{
                        it?.data!!.getParcelableExtra<MainMemberClass>("obj1")
                    }
                    memberList.add(info3!!)
                    activityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
                }
            }

        }

    }

    fun setToolBar(){
        activityMainBinding.apply {
            materialToolbar.apply {
                //타이틀 설정
                title = "DietTogether"
            }
        }

    }

    fun initView(){
        activityMainBinding.apply {
            recyclerView.apply {
                //어댑터 객체 생성
                adapter = ThisReCycler()
                //레이아웃 설정
                layoutManager = LinearLayoutManager(this@MainActivity)
                //데코 설정
                var deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }

    }

    fun setButton(){
        activityMainBinding.apply {
            newMemberButton2.setOnClickListener {
                var newIntent2 = Intent(this@MainActivity, NewMemberActivity::class.java)
                newMemberActivitylauncher.launch(newIntent2)

            }

        }
    }

    //어댑터 클래스 생성
    inner class ThisReCycler: RecyclerView.Adapter<ThisReCycler.ThisViewHolder>(){

        //viewHolderClass 생성
        inner class ThisViewHolder(mainBinding: MainBinding):RecyclerView.ViewHolder(mainBinding.root){
            var mainBinding:MainBinding

            init {
                this.mainBinding = mainBinding

                //가로세로 길이 설정
                this.mainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                //recyclerView를 클릭했을 때
                this.mainBinding.root.setOnClickListener {
                    var newIntent3 = Intent(this@MainActivity, PrintInfoActivity::class.java)
                    newIntent3.putExtra("obj2", memberList[adapterPosition])
                    printActivitylauncher.apply {
                        memberList.removeAt(adapterPosition)
                        activityMainBinding.recyclerView.adapter?.notifyDataSetChanged()
                        launch(newIntent3)
                    }
                    modifyActivitylauncher.launch(newIntent3)

                }
                activityMainBinding.apply {

                    toggleGroup1.addOnButtonCheckedListener { group, checkedId, isChecked ->
                        if (isChecked){
                            when(toggleGroup1.checkedButtonId){
                                R.id.searchIdMainText -> {
                                    var newIntent = Intent(this@MainActivity, SearchIdActivity::class.java)
                                    newIntent.putExtra("obj1", mainBinding.recycleId.text)
                                    newIntent.putExtra("obj2", mainBinding.recycleNumber.text)
                                    startActivity(newIntent)
                                }
                                R.id.searchPwMainText -> {
                                    var newIntent2 = Intent(this@MainActivity, SearchPWActivity::class.java)
                                    newIntent2.putExtra("obj3", mainBinding.recycleId.text)
                                    newIntent2.putExtra("obj4", mainBinding.recyclePW.text)
                                    newIntent2.putExtra("obj5", mainBinding.recycleNumber.text)
                                    startActivity(newIntent2)
                                }
                            }
                        }
                    }
                }

                activityMainBinding.apply {
                    //와 성공했다ㅠㅠㅠㅠㅠ
                    loginMainButton.setOnClickListener {
                        var str1 = IdMainTextField.text.toString()
                        var str2 = pwMainTextField.text.toString()
                        if (str1 != mainBinding.recycleId.text ){
                            enum.showDiaLog(this@MainActivity, "아이디 입력 오류", "아이디를 확인해주세요"){ dialogInterface: DialogInterface, i: Int ->
                                enum.showSoftInput(IdMainTextField, this@MainActivity)
                            }
                        }else if (str2 != mainBinding.recyclePW.text){
                            enum.showDiaLog(this@MainActivity, "비밀번호 입력 오류", "비밀번호를 확인해주세요"){ dialogInterface: DialogInterface, i: Int ->
                                enum.showSoftInput(pwMainTextField, this@MainActivity)
                            }
                        }else{
                            var newIntent = Intent(this@MainActivity, carouselActivity::class.java)
                            startActivity(newIntent)
                        }
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThisViewHolder {
            //viewBinding
            var mainBinding =  MainBinding.inflate(layoutInflater)
            //viewHolder
            var thisViewHolder = ThisViewHolder(mainBinding)
            return thisViewHolder
        }

        override fun getItemCount(): Int {
            return memberList.size
        }

        override fun onBindViewHolder(holder: ThisViewHolder, position: Int) {
            holder.mainBinding.recycleId.text = memberList[position].memberId
            holder.mainBinding.recyclePW.text = memberList[position].memberPw
            holder.mainBinding.recycleNumber.text = memberList[position].number.toString()
        }
    }
}










































































