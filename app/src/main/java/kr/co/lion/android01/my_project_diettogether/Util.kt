package kr.co.lion.android01.my_project_diettogether

class Util {
    companion object{
        //담아둘 리스트
        var inviteList = mutableListOf<InviteClass>()

    }
}
enum class Mygender(var num:Int, var str:String){
    MAN_GENDER(0, "남자"),
    GIRL_GENDER(0, "여자"),
}
enum class Myweight(var num:Int, var str:String){
    FOURDOWN(0, "40kg 이하"),
    FOURFIVE(0, "40 ~ 50kg"),
    FIVESIX(0, "50 ~ 60kg"),
    SIXSEVEN(0, "60 ~ 70kg"),
    SEVENEIGHT(0, "70 ~ 80kg"),
    EIGHTNINE(0, "80 ~ 90kg"),
    NINEHUND(0, "90 ~ 100kg"),
    HUNDONEHUND(0, "100 ~ 110kg"),
    ONEHUNDOVER(0, "110kg이상"),
    NEVER(0, "직접 입력합니다!")
}

