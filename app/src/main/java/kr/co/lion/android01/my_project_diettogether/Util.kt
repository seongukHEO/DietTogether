package kr.co.lion.android01.my_project_diettogether

class Util {
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
}

enum class FavoriteExercise(var num:Int, var str:String){
    ILONEXERCISE(0, "헬스"),
    FILLATES(0, "필라테스"),
    BALLEXERCISE(0, "구기종목"),
    NEVER(0, "좋아하는 운동 없음")
}