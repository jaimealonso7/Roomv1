package com.example.roomv1.navigation

sealed class AppScreens (val ruta:String) {
    object UserListView: AppScreens ("UserListView")
    object UserAddView: AppScreens ("UserAddView")
    //object UserUpdateView: AppScreens (UserUpdateView)
}