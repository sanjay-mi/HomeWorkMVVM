@file:Suppress("DEPRECATION")

package com.imaginato.homeworkmvvm.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.imaginato.homeworkmvvm.data.remote.login.LoginRepository
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class, KoinApiExtension::class)
class MainActivityViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: LoginRepository

    @Mock
    private lateinit var mockProgressObserver: Observer<Boolean>

    @Mock
    private lateinit var mockResultLiveDataObserver: Observer<String>

    private lateinit var viewModel: MainActivityViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val expectedFlowResult = flowOf("your password is incorrect.")
    private val expectedFlowResultForSuccess = flowOf("Sukses.")

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MainActivityViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun check_username_is_empty() {
        viewModel.loginValidationEvent.observeForever {
            assert(it == LoginValidation.IS_USERNAME_BLANK)
        }
        viewModel.checkLoginValidation("", "")
    }

    @Test
    fun check_password_is_empty() {
        viewModel.loginValidationEvent.observeForever {
            assert(it == LoginValidation.IS_PASSWORD_BLANK)
        }
        viewModel.checkLoginValidation("username", "")
    }

    @Test
    fun check_login_input_valid() {
        viewModel.loginValidationEvent.observeForever {
            assert(it == LoginValidation.VALID_INPUT)
        }
        viewModel.checkLoginValidation("username", "1111111")
    }

    @Test
    fun do_login_failed() = testScope.runBlockingTest {
        Mockito.`when`(repository.doLogin(LoginRequest("username", "123456")))
            .thenReturn(expectedFlowResult)
        viewModel.progress.observeForever(mockProgressObserver)
        viewModel.loginResultLiveData.observeForever(mockResultLiveDataObserver)

        viewModel.doLogin("username", "123456")

        Mockito.verify(mockProgressObserver)
            .onChanged(true)
        Mockito.verify(mockResultLiveDataObserver)
            .onChanged("your password is incorrect.")
        Mockito.verify(mockProgressObserver)
            .onChanged(false)
    }

    @Test
    fun do_login_success() = testScope.runBlockingTest {
        Mockito.`when`(repository.doLogin(LoginRequest("username", "1111111")))
            .thenReturn(expectedFlowResultForSuccess)

        viewModel.progress.observeForever(mockProgressObserver)
        viewModel.loginResultLiveData.observeForever(mockResultLiveDataObserver)

        viewModel.doLogin("username", "1111111")

        Mockito.verify(mockProgressObserver)
            .onChanged(true)
        Mockito.verify(mockResultLiveDataObserver)
            .onChanged("Sukses.")
        Mockito.verify(mockProgressObserver)
            .onChanged(false)
    }
}