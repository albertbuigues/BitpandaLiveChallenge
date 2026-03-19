import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bitpanda.livechallenge.domain.EMPTY_SCREEN_BTN_TAG
import com.bitpanda.livechallenge.domain.EMPTY_SCREEN_TAG
import com.bitpanda.livechallenge.domain.models.Coin
import com.bitpanda.livechallenge.domain.usecases.GetTopTenBestCoinsUseCase
import com.bitpanda.livechallenge.domain.usecases.GetTopTenWorstCoinsUseCase
import com.bitpanda.livechallenge.ui.screens.CryptoCoinsScreen
import com.bitpanda.livechallenge.ui.viewmodels.CryptoCoinsListViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class EmptyScreenUiTests {

    @get:Rule val composeRule = createComposeRule()

    private val getTopTen = mockk<GetTopTenBestCoinsUseCase>(relaxed = true)
    private val getWorst = mockk<GetTopTenWorstCoinsUseCase>(relaxed = true)
    private lateinit var viewModel: CryptoCoinsListViewModel

    @Test
    fun whenErrorInFetchCoinsEmptyScreenVisible() = runTest {
        coEvery { getTopTen() } returns Result.failure(IOException())

        initCompose()
        composeRule.waitUntil(5000) {
            composeRule
                .onAllNodesWithTag(EMPTY_SCREEN_TAG)
                .fetchSemanticsNodes().size == 1
        }

        composeRule.onNodeWithTag(EMPTY_SCREEN_TAG).assertExists()
    }

    @Test
    fun whenEmptyScreenVisibleThenReloadWithSuccessMakeDisappear() = runTest {
        coEvery { getTopTen() } returns Result.failure(IOException())

        initCompose()
        composeRule.waitUntil(5000) {
            composeRule
                .onAllNodesWithTag(EMPTY_SCREEN_TAG)
                .fetchSemanticsNodes().size == 1
        }

        composeRule.onNodeWithTag(EMPTY_SCREEN_TAG).assertExists()

        val mockCoin = mockk<Coin>(relaxed = true) {
            every { id } returns "1"
            every { name } returns "Bitcoin"
            every { symbol } returns "BTC"
            every { priceInEuro } returns 50000.0
            every { changePercent } returns 0.5
            every { changePercentFormatted } returns "0.5"
        }
        coEvery { getTopTen() } returns Result.success(listOf(mockCoin))

        composeRule.onNodeWithTag(EMPTY_SCREEN_BTN_TAG).performClick()
        advanceUntilIdle()

        composeRule.waitUntil(5000) {
            composeRule
                .onAllNodesWithTag(EMPTY_SCREEN_TAG)
                .fetchSemanticsNodes().isEmpty()
        }

        composeRule.onNodeWithTag(EMPTY_SCREEN_TAG).assertDoesNotExist()
    }

    private fun initCompose() {
        composeRule.setContent {
            viewModel = CryptoCoinsListViewModel(getTopTen, getWorst)
            CryptoCoinsScreen(viewModel)
        }
    }
}