package com.noemi.android.meniu.model

data class Course(var id: Int = 0,
                  var type: CourseType? = null,
                  var meal: Meal? = null){}
