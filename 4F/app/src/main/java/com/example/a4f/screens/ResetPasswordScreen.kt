package com.example.a4f.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a4f.navigation.AppRoutes
import com.example.a4f.ui.theme.LoginButtonColor
import com.example.a4f.ui.theme.LoginScreenBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(navController: NavController) {

    // --- 1. THÊM BIẾN LƯU TRỮ VÀ LỖI ---
    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    // Biến cho con mắt (ẩn/hiện)
    var newPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    // Biến lưu thông báo lỗi
    var newPasswordError by rememberSaveable { mutableStateOf<String?>(null) }
    var confirmPasswordError by rememberSaveable { mutableStateOf<String?>(null) }
    // --- KẾT THÚC THÊM BIẾN ---

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Quay lại"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = LoginScreenBackground
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "Tạo mật khẩu mới",
                fontSize = 26.sp,
                // ... (code text)
            )

            Text(
                text = "Vui lòng đặt mật khẩu mới mà bạn sẽ không quên.",
                fontSize = 16.sp,
                // ... (code text)
            )

            // --- 2. SỬA Ô "Mật khẩu mới" ---
            OutlinedTextField(
                value = newPassword,
                onValueChange = {
                    newPassword = it
                    newPasswordError = null // Xóa lỗi khi người dùng gõ
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Mật khẩu mới") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                singleLine = true,
                visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (newPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    // ... (code colors)
                ),
                shape = RoundedCornerShape(16.dp),
                // Hiển thị lỗi nếu có
                isError = newPasswordError != null,
                supportingText = {
                    if (newPasswordError != null) {
                        Text(text = newPasswordError!!, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- 3. SỬA Ô "Xác nhận Mật khẩu" ---
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError = null // Xóa lỗi khi người dùng gõ
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Xác nhận mật khẩu") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                singleLine = true,
                // Thêm con mắt ẩn/hiện
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    // ... (code colors)
                ),
                shape = RoundedCornerShape(16.dp),
                // Hiển thị lỗi nếu có
                isError = confirmPasswordError != null,
                supportingText = {
                    if (confirmPasswordError != null) {
                        Text(text = confirmPasswordError!!, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- 4. SỬA NÚT "Lưu mật khẩu" ---
            Button(
                onClick = {
                    // --- Thêm logic Validate ---
                    var isValid = true

                    // Kiểm tra 1: Mật khẩu mới
                    if (newPassword.length < 8) {
                        newPasswordError = "Mật khẩu phải có ít nhất 8 ký tự."
                        isValid = false
                    }
                    // (Bạn có thể thêm luật kiểm tra ký tự đặc biệt ở đây)
                    // else if (!newPassword.any { it.isDigit() }) { ... }

                    // Kiểm tra 2: Mật khẩu xác nhận
                    if (newPassword != confirmPassword) {
                        confirmPasswordError = "Mật khẩu xác nhận không khớp."
                        isValid = false
                    }

                    // Chỉ chuyển trang khi tất cả đều hợp lệ
                    if (isValid) {
                        navController.navigate(AppRoutes.LOGIN) {
                            popUpTo(AppRoutes.LOGIN) { inclusive = true }
                        }
                    }
                    // --- Hết logic Validate ---
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LoginButtonColor),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Lưu mật khẩu", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}