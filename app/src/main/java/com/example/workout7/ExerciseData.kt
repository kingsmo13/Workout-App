package com.example.workout7

object exerciseData{
    fun getExerciseData():ArrayList<ExerciseModel>?{
        var exerciseList : ArrayList<ExerciseModel> = ArrayList()

        val ex1 = ExerciseModel(1,"Jumping Jacks",R.drawable.jumpingjacks,30000,false,false)
        exerciseList.add(ex1)

        val ex2 = ExerciseModel(2,"Sit Ups",R.drawable.situps,30000,false,false)
        exerciseList.add(ex2)

        val ex3 = ExerciseModel(3,"Push Ups",R.drawable.pushups,30000,false,false)
        exerciseList.add(ex3)

        return exerciseList
    }
}
