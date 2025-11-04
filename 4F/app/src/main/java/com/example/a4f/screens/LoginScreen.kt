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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone // <-- Thêm import này
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun LoginScreen(navController: NavController) {

    var tenKhachHang by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LoginScreenBackground) // 1. Nền xám nhạt
            .verticalScroll(rememberScrollState()) // 2. Cho phép cuộn
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 3. Ảnh xe bus
        Image(
            painter = painterResource(id = R.drawable.img_login_bus),
            contentDescription = "Login Bus",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp),
            contentScale = ContentScale.Fit
        )

        // 4. Text "Xin Chào"
        Text(
            text = "Xin Chào, Khả Như!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = LoginButtonColor,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )

        // 5. Ô nhập "Tên Khách Hàng"
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
            onValueChange = { tenKhachHang = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Họ và tên") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 6. Ô nhập "Password"
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
            onValueChange = { password = it },
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
            shape = RoundedCornerShape(16.dp)
        )

        // 7. Nút "Quên mật khẩu"
        TextButton(
            onClick = {
                // SỬA DÒNG NÀY:
                navController.navigate(AppRoutes.FORGOT_PASSWORD)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Quên mật khẩu?", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 8. Nút "Đăng nhập"
        Button(
            onClick = {
                navController.navigate(AppRoutes.HOME) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LoginButtonColor),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Đăng nhập", fontSize = 18.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 9. Đăng nhập Social
        Text(text = "Or continue with", color = Color.Gray)
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
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = "Google Login",
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.width(32.dp))

            // Nút Điện thoại
            IconButton(
                onClick = { /* TODO: Xử lý login bằng SĐT */ },
                modifier = Modifier
                    .size(50.dp)
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

        // 10. Nút "Đăng ký"
        TextButton(
            onClick = {
                navController.navigate(AppRoutes.REGISTER)
            }
        ) {
            Text(text = "Bạn chưa có tài khoản !!", color = LoginButtonColor)
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}