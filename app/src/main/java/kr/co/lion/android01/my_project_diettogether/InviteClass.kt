package kr.co.lion.android01.my_project_diettogether

import android.os.Parcel
import android.os.Parcelable

class InviteClass(var gender:Mygender, var height:Int, var age:Int, var weight:Myweight, var exercise:FavoriteExercise): Parcelable{
    constructor(parcel: Parcel) : this(
        TODO("gender"),
        parcel.readInt(),
        parcel.readInt(),
        TODO("weight"),
        TODO("exercise")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(height)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InviteClass> {
        override fun createFromParcel(parcel: Parcel): InviteClass {
            return InviteClass(parcel)
        }

        override fun newArray(size: Int): Array<InviteClass?> {
            return arrayOfNulls(size)
        }
    }
}