package kr.co.lion.android01.my_project_diettogether

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.carousel.CarouselLayoutManager
import kr.co.lion.android01.my_project_diettogether.databinding.ActivityCarouselBinding
import kr.co.lion.android01.my_project_diettogether.databinding.CarouselBinding

class carouselActivity : AppCompatActivity() {
    lateinit var activityCarouselBinding: ActivityCarouselBinding

    //이미지 설정
    var picture = arrayOf(
        R.drawable.kk_124, R.drawable.seon_re, R.drawable.ukseon_ew, R.drawable.sdin_re,
        R.drawable.sdsd_ew, R.drawable.seong_uk, R.drawable.eu2, R.drawable.jp4
    )
    //이미지에 맞는 텍스트 설정
    var pictureText = arrayOf(
        "DietMoney", "체지방 3~4%", "체지방 6~7%", "체지방 10~12%",
        "체지방 15%", "체지방 20%", "런던", "런던2"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCarouselBinding = ActivityCarouselBinding.inflate(layoutInflater)
        setContentView(activityCarouselBinding.root)
        setToolBar()
        initView()



    }
    //툴바 설정
    fun setToolBar(){
        activityCarouselBinding.apply {
            materialToolbar5.apply {
                //타이틀 설정
                title = "DietTogether"
                //아이콘 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                //아이콘을 클릭했을 때
                setNavigationOnClickListener {
                    finish()
                }
                //메뉴 설정
                inflateMenu(R.menu.carousel_menu)
                //메뉴를 클릭했을 때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.recycler_menumenu -> {
                            var newIntent = Intent(this@carouselActivity, InviteActivity::class.java)
                            startActivity(newIntent)
                        }
                    }
                    true
                }
            }
        }
    }
    //어댑터 객체 생성
    fun initView(){
        activityCarouselBinding.apply {
            recyclerView.apply {
                //어댑터 객체
                adapter = RecyclerViewAdater()
                //레이아웃
                layoutManager = CarouselLayoutManager()
                //데코?

            }
        }
    }

    //어댑터 클래스 설정
    inner class RecyclerViewAdater : RecyclerView.Adapter<RecyclerViewAdater.viewHolderClass>(){

        //viewHolderClass설정
        inner class viewHolderClass(carouselBinding: CarouselBinding) : RecyclerView.ViewHolder(carouselBinding.root){
            var carouselBinding:CarouselBinding
            init {
                this.carouselBinding = carouselBinding
                //리사이클러뷰를 클릭했을 때?
                carouselBinding.root.setOnClickListener {
                    activityCarouselBinding.apply {
                        imageView2.setImageResource(picture[adapterPosition])
                        textView3.text = pictureText[adapterPosition]
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderClass {
            //viewBinding
            var carouselBinding = CarouselBinding.inflate(layoutInflater)
            //viewHolder
            var viewHolder = viewHolderClass(carouselBinding)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return picture.size
        }

        override fun onBindViewHolder(holder: viewHolderClass, position: Int) {
            holder.carouselBinding.carouselImageView.setImageResource(picture[position])


        }
    }


}












































