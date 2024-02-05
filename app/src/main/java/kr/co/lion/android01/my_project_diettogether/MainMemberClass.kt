package kr.co.lion.android01.my_project_diettogether

import android.os.Parcel
import android.os.Parcelable

class MainMemberClass(var name:String?, var number:Int, var memberId:String?, var memberPw:String? ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(number)
        parcel.writeString(memberId)
        parcel.writeString(memberPw)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainMemberClass> {
        override fun createFromParcel(parcel: Parcel): MainMemberClass {
            return MainMemberClass(parcel)
        }

        override fun newArray(size: Int): Array<MainMemberClass?> {
            return arrayOfNulls(size)
        }
    }
}