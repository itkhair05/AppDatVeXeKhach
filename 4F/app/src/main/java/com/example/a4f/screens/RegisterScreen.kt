package com.example.a4f.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a4f.R
import com.example.a4f.navigation.AppRoutes
import com.example.a4f.ui.theme.LoginButtonColor
import com.example.a4f.ui.theme.LoginScreenBackground
import androidx.compose.ui.draw.clip

private fun isPasswordValid(password: String): Boolean {
    val hasMinLength = password.length >= 8
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }
    return hasMinLength && hasDigit && hasSpecialChar
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {

    // --- 1. THÊM BIẾN LƯU TRỮ VÀ LỖI ---
    var email by rememberSaveable { mutableStateOf("") }
    var tenKhachHang by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    // Biến lưu thông báo lỗi
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    var tenKhachHangError by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }
    // --- KẾT THÚC THÊM BIẾN ---

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Không có title */ },
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

            Image(
                painter = painterResource(id = R.drawable.img_register_van),
                contentDescription = "Register Van",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 16.dp, end = 16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- 2. SỬA Ô "Email" ---
            Text(
                text = "Email",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = null // Xóa lỗi khi gõ
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("yourname@gmail.com") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(16.dp),
                // Hiển thị lỗi
                isError = emailError != null,
                supportingText = {
                    if (emailError != null) {
                        Text(text = emailError!!, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- 3. SỬA Ô "Tên Khách Hàng" ---
            Text(
                text = "Tên Khách Hàng",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = tenKhachHang,
                onValueChange = {
                    tenKhachHang = it
                    tenKhachHangError = null // Xóa lỗi khi gõ
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Họ và Tên") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(16.dp),
                // Hiển thị lỗi
                isError = tenKhachHangError != null,
                supportingText = {
                    if (tenKhachHangError != null) {
                        Text(text = tenKhachHangError!!, color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- 4. SỬA Ô "Password" ---
            Text(
                text = "Password",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = null // Xóa lỗi khi gõ
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("•••••••••") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = "Toggle password visibility")
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(16.dp),
                // Hiển thị lỗi
                isError = passwordError != null,
                supportingText = {
                    if (passwordError != null) {
                        Text(text = passwordError!!, color = MaterialTheme.colorScheme.error)
                    } else {
                        // Gợi ý mật khẩu
                        Text(text = "Ít nhất 8 ký tự, 1 số, 1 ký tự đặc biệt.")
                    }
                }
            )

            // Xóa chữ "--- Strong"
            Spacer(modifier = Modifier.height(24.dp))

            // --- 5. SỬA NÚT "Đăng ký" ---
            Button(
                onClick = {
                    // --- Thêm logic Validate ---
                    var isValid = true

                    // 1. Kiểm tra Email
                    if (!email.contains("@") || email.isBlank()) {
                        emailError = "Email không hợp lệ."
                        isValid = false
                    }

                    // 2. Kiểm tra Tên
                    if (tenKhachHang.isBlank()) {
                        tenKhachHangError = "Vui lòng nhập họ và tên."
                        isValid = false
                    }

                    // 3. Kiểm tra Mật khẩu
                    if (!isPasswordValid(password)) {
                        passwordError = "Mật khẩu không đủ mạnh."
                        isValid = false
                    }

                    // Chỉ chuyển trang khi tất cả đều hợp lệ
                    if (isValid) {
                        navController.navigate(AppRoutes.HOME) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
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
                Text(text = "Đăng ký", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 9. Đăng nhập Social (Giữ nguyên)
            Text(text = "Or sign up with", color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Nút Google
                IconButton(
                    onClick = { /* TODO: Xử lý login Google */ },
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google_logo),
                        contentDescription = "Google Login",
                        modifier = Modifier.size(32.dp)
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                // Nút Điện thoại
                IconButton(
                    onClick = { /* TODO: Xử lý login bằng SĐT */ },
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Đăng nhập SĐT",
                        modifier = Modifier.size(32.dp),
                        tint = Color.DarkGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}