package com.example.workout7

data class ExerciseModel(val id:Int,
                         val name:String,
                         val pic:Int,
                         val duration : Long,
                         var isSelected : Boolean,
                         var isCompleted : Boolean)