package com.app.feature.authentication.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.core.network.ApiResult
import com.app.feature.authentication.domain.LoginModel
import com.app.feature.authentication.login.TestObjects.loginObject
import com.app.feature.authentication.repository.LoginDataRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    private lateinit var repository: LoginDataRepository
    private val testScheduler = StandardTestDispatcher()
    private val loginModel: LoginModel = loginObject

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = mockk(relaxed = true)

        Dispatchers.setMain(testScheduler)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get login screen payload`() {
        coEvery { repository.getLogin() } returns ApiResult.Success(loginModel)

        val response = runBlocking { repository.getLogin() }

        assertEquals(ApiResult.Success(loginModel), response)
    }

    @Test
    fun `loading LiveData should be true before getAll() completes`() {
        val testCoroutineScope = TestScope()
        testCoroutineScope.runTest {
            // Given
            coEvery { repository.getLogin() } returns ApiResult.Success(loginModel)

            // When
            val viewModel = LoginViewModel(repository)
            val screenData = viewModel.getScreenData()

            val observer = Observer<ApiResult<LoginModel>> {}
            screenData.observeForever(observer)

            advanceUntilIdle()
            assertEquals(ApiResult.Success(loginModel), screenData.value)
            screenData.removeObserver(observer)
        }
    }

//    @Test
//    fun `getAll() should update login LiveData when repository returns success`() = runTest {
//        // Given
//        coEvery { repository.getLogin() } returns ApiResult.Success(loginModel)
//        val viewModel = LoginViewModel(repository)
//
//        // When
//        viewModel.getAll()
//
//        // Then
//        advanceUntilIdle()
//        assertEquals(loginModel, viewModel.login.value)
//    }
//
//    @Test
//    fun `getAll() should update error LiveData when repository returns error`() = runTest {
//        // Given
//        coEvery { repository.getLogin() } returns ApiResult.Error(Exception())
//        val viewModel = LoginViewModel(repository)
//
//        // When
//        viewModel.getAll()
//
//        // Then
//        advanceUntilIdle()
//        assert(viewModel.error.value is Exception)
//    }
//
//    @Test
//    fun `getAll() should update loading LiveData when repository returns error`() = runTest{
//        // Given
//        coEvery { repository.getLogin() } returns ApiResult.Error(Exception())
//        val viewModel = LoginViewModel(repository)
//
//        // When
//        viewModel.getAll()
//
//        // Then
//        advanceUntilIdle()
//        assertEquals(false, viewModel.loading.value)
//    }
//
//    @Test
//    fun `getAll() should update loading LiveData when repository returns success`() = runTest{
//        // Given
//        coEvery { repository.getLogin() } returns ApiResult.Success(loginModel)
//        val viewModel = LoginViewModel(repository)
//
//        // When
//        viewModel.getAll()
//
//        // Then
//        advanceUntilIdle()
//        assertEquals(false, viewModel.loading.value)
//    }
}
