//package com.example.assignment_ph33001.repository
//
//import android.content.Context
//
//import com.example.assignment_ph33001.model.User
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.flow.map
//
//val Context.dataStore by preferencesDataStore("user_prefs")
//
//class UserManager(private val context: Context) {
//    private val gson = Gson()
//    private val userKey = stringPreferencesKey("user_list")
//
//    // Thêm user mới
//    suspend fun addUser(user: User): Boolean {
//        val currentUsers = getUsers().toMutableList()
//        if (currentUsers.any { it.email == user.email }) return false  // Trùng username
//
//        currentUsers.add(user)
//        val json = gson.toJson(currentUsers)
//        context.dataStore.edit { it[userKey] = json }
//        return true
//    }
//
//    // Lấy danh sách user đã lưu
//    suspend fun getUsers(): List<User> {
//        val json = context.dataStore.data.map { it[userKey] ?: "" }.first()
//        return if (json.isNotEmpty()) gson.fromJson(json, object : TypeToken<List<User>>() {}.type) else emptyList()
//    }
//
//    // Xử lý đăng nhập
//    suspend fun login(username: String, password: String): Boolean {
//        val users = getUsers()
//        return users.any { it.email == username && it.password == password }
//    }
//}
