package com.andika.project_credit_scoring.repositories

import com.andika.project_credit_scoring.api.HistoryApi
import com.andika.project_credit_scoring.entity.History
import retrofit2.Response
import javax.inject.Inject

//@Inject constructor(historyApi: HistoryApi)

class HistoryRepositoryImpl @Inject constructor(private val historyApi: HistoryApi): HistoryRepository{


    override suspend fun getAllHistory() : Response<List<History>>{
        return historyApi.getReport()
    }


    //
//    companion object {
//        var historyList = arrayListOf(
//            History(
//                "Andika Haris",
//                2100,
//                true,
//                "21%",
//                true,
//                "21-01-2021"
//            ),
//            History(
//                "Donna Bunggala",
//                2300,
//                false,
//                "11%",
//                false,
//                "21-01-2021"
//            ),
//            History(
//                "Krisna Mukti",
//                2100,
//                true,
//                "29%",
//                true,
//                "11-11-2021"
//            ),
//            History(
//                "Herman Suherman",
//                3400,
//                true,
//                "20%",
//                true,
//                "13-07-2021"
//            )
//        )
//        var historyDua = arrayListOf(
//            History(
//                "Andika Haris",
//                2100,
//                true,
//                "21%",
//                true,
//                "21-01-2021"
//            ),
//            History(
//                "Donna Bunggala",
//                2300,
//                false,
//                "11%",
//                false,
//                "21-01-2021"
//            )
//        )
//        var historyTiga = arrayListOf(
//            History(
//                "Krisna Mukti",
//                2100,
//                true,
//                "29%",
//                true,
//                "11-11-2021"
//            ),
//            History(
//                "Herman Suherman",
//                3400,
//                true,
//                "20%",
//                true,
//                "13-07-2021"
//            )
//        )
//    }
}