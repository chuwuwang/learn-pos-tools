package com.sea.pos.ui.pmx

import com.sea.pos.api.facade.ApiService
import java.text.SimpleDateFormat
import java.util.*

internal object PMXFacade : ApiService() {

    suspend fun active(url: String, req: ActivateReq): Result<ActiveResponse> {
        val map = mutableMapOf<String, Any>()
        map["data"] = req
        map["appId"] = "PMX POS"
        map["version"] = "1.4"
        map["keyVersion"] = "1"
        map["merchantNo"] = req.serialNumber
        map["requestTime"] = getRequestTime()
        return post<Map<String, Any>, ActiveResponse>(url, map)
    }

    private fun getRequestTime(): String {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        val millis = System.currentTimeMillis()
        val date = Date(millis)
        return SimpleDateFormat(pattern, Locale.US).format(date)
    }

}