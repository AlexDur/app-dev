package com.alusch.quizzapp

object Constants2 {

    fun get(): ArrayList<FullPic> {
        val picSet = ArrayList<FullPic>()

        val full_pic1 = FullPic(6, "Donald Trump", R.drawable.trumpfull)
        picSet.add(full_pic1)

        val full_pic2 = FullPic(7,"Sonja Zietlow", R.drawable.sonjazietlow_full)
        picSet.add(full_pic2)

        val full_pic3 = FullPic(7,"Stefan Raab", R.drawable.raab_full)
        picSet.add(full_pic3)

        val full_pic4 = FullPic(7,"Bruce Willis", R.drawable.brucewillis_full)
        picSet.add(full_pic4)

        val full_pic5 = FullPic(7,"Donald Trump", R.drawable.trumptk_full)
        picSet.add(full_pic5)

        return picSet

    }

}