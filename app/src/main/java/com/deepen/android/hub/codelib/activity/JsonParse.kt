package com.deepen.android.hub.codelib.activity

import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.deepen.android.hub.codelib.bean.Flight
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws

/**
 * @author shane
 */
object JsonParse {
    @Throws(JSONException::class)
    fun testJson() {
        val jsonStr = "[{\"FlightDepcode\":\"PEK\",\"distance\":\"3811\",\"LegFlag\":\"0\",\"FlightDep\":\"北京\",\"FlightArrtimePlanDate\":\"2018-01-15 19:15:00\",\"FlightNo\":\"MU563\",\"ShareFlightNo\":\"\",\"FlightState\":\"计划\",\"ShareFlag\":\"0\",\"FlightCompany\":\"中国东方航空股份有限公司\",\"BaggageID\":\"\",\"FlightArrtimeDate\":\"\",\"FlightArrtimeReadyDate\":\"2018-01-15 19:15:00\",\"BoardGate\":\"90\",\"fcategory\":\"1\",\"StopFlag\":\"1\",\"FlightArr\":\"新德里\",\"FlightDeptimePlanDate\":\"2018-01-15 10:40:00\",\"FlightArrAirport\":\"德里\",\"FlightDeptimeReadyDate\":\"\",\"FlightArrcode\":\"DEL\",\"FlightDeptimeDate\":\"\",\"FlightTerminal\":\"T3\",\"org_timezone\":\"28800\",\"FlightHTerminal\":\"T2\",\"FlightDepAirport\":\"北京首都\",\"dst_timezone\":\"19800\"},{\"FlightDepcode\":\"PEK\",\"distance\":\"1178\",\"LegFlag\":\"0\",\"FlightDep\":\"北京\",\"FlightArrtimePlanDate\":\"2018-01-15 12:55:00\",\"FlightNo\":\"MU563\",\"ShareFlightNo\":\"\",\"FlightState\":\"计划\",\"ShareFlag\":\"0\",\"FlightCompany\":\"中国东方航空股份有限公司\",\"BaggageID\":\"04\",\"FlightArrtimeDate\":\"\",\"FlightArrtimeReadyDate\":\"\",\"BoardGate\":\"68\",\"fcategory\":\"0\",\"StopFlag\":\"0\",\"FlightArr\":\"上海\",\"FlightDeptimePlanDate\":\"2018-01-15 10:40:00\",\"FlightArrAirport\":\"上海浦东\",\"FlightDeptimeReadyDate\":\"\",\"FlightArrcode\":\"PVG\",\"FlightDeptimeDate\":\"\",\"FlightTerminal\":\"T1\",\"org_timezone\":\"28800\",\"FlightHTerminal\":\"T2\",\"FlightDepAirport\":\"北京首都\",\"dst_timezone\":\"28800\"},{\"FlightDepcode\":\"PVG\",\"distance\":\"4268\",\"LegFlag\":\"0\",\"FlightDep\":\"上海\",\"FlightArrtimePlanDate\":\"2018-01-15 19:15:00\",\"FlightNo\":\"MU563\",\"ShareFlightNo\":\"\",\"FlightState\":\"计划\",\"ShareFlag\":\"0\",\"FlightCompany\":\"中国东方航空股份有限公司\",\"BaggageID\":\"\",\"FlightArrtimeDate\":\"\",\"FlightArrtimeReadyDate\":\"2018-01-15 19:15:00\",\"BoardGate\":\"16\",\"fcategory\":\"1\",\"StopFlag\":\"0\",\"FlightArr\":\"新德里\",\"FlightDeptimePlanDate\":\"2018-01-15 14:25:00\",\"FlightArrAirport\":\"德里\",\"FlightDeptimeReadyDate\":\"2018-01-15 14:25:00\",\"FlightArrcode\":\"DEL\",\"FlightDeptimeDate\":\"\",\"FlightTerminal\":\"T3\",\"org_timezone\":\"28800\",\"FlightHTerminal\":\"T1\",\"FlightDepAirport\":\"上海浦东\",\"dst_timezone\":\"19800\"}]"
        val l = System.nanoTime()
        val jsonArray = JSONArray(jsonStr)
        val gson = Gson()
        for (i in 0 until jsonArray.length()) {
            val `object` = jsonArray[i] as JSONObject
            LogUtils.d("JsonParse", "object = $`object`")
            val flight = gson.fromJson(`object`.toString(), Flight::class.java)
            LogUtils.d("JsonParse", "flight = $flight")
        }
        LogUtils.d("JsonParse", "duration = " + (System.nanoTime() - l))
    }
}