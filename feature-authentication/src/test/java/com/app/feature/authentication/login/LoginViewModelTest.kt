package com.app.feature.authentication.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.core.network.ApiResult
import com.app.feature.authentication.domain.LoginModel
import com.app.feature.authentication.login.TestObjects.loginObject
import com.app.feature.authentication.repository.LoginDataRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class LoginViewModelTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var repository: LoginDataRepository
    lateinit var viewModel: LoginViewModel
    private val loginModel: LoginModel = loginObject

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        repository = mockk(relaxed = true)
        viewModel = mockk(relaxed = true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `get login screen payload`() {
        coEvery { repository.getLogin() } returns ApiResult.Success(loginModel)

        val response = runBlocking { repository.getLogin() }

        assertEquals(ApiResult.Success(loginModel), response)
    }
}
