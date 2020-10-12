package com.deepen.android.hub.codelib.bean

class Flight {
    val fcategory: String? = null
    val org_timezone: String? = null
    val dst_timezone: String? = null
    val flightCompany: String? = null
    val flightDepcode: String? = null
    val flightArrcode: String? = null
    val flightDeptimePlanDate: String? = null
    val flightArrtimePlanDate: String? = null
    val flightDeptimeReadyDate: String? = null
    val flightArrtimeReadyDate: String? = null
    val flightDeptimeDate: String? = null
    val flightArrtimeDate: String? = null
    val boardGate: String? = null
    val baggageID: String? = null
    val flightState: String? = null
    val shareFlightNo: String? = null
    val flightHTerminal: String? = null
    val flightTerminal: String? = null
    val stopFlag: String? = null
    val shareFlag: String? = null
    val legFlag: String? = null
    val flightDep: String? = null
    val flightArr: String? = null
    val flightDepAirport: String? = null
    val flightArrAirport: String? = null
    val fjson: String? = null
    val flightNo: String? = null
    val fmodifytime: String? = null

    override fun toString(): String {
        return "Flight{" +
                "BaggageID='" + baggageID + '\'' +
                ", fcategory='" + fcategory + '\'' +
                ", org_timezone='" + org_timezone + '\'' +
                ", dst_timezone='" + dst_timezone + '\'' +
                ", FlightCompany='" + flightCompany + '\'' +
                ", FlightDepcode='" + flightDepcode + '\'' +
                ", FlightArrcode='" + flightArrcode + '\'' +
                ", FlightDeptimePlanDate='" + flightDeptimePlanDate + '\'' +
                ", FlightArrtimePlanDate='" + flightArrtimePlanDate + '\'' +
                ", FlightDeptimeReadyDate='" + flightDeptimeReadyDate + '\'' +
                ", FlightArrtimeReadyDate='" + flightArrtimeReadyDate + '\'' +
                ", FlightDeptimeDate='" + flightDeptimeDate + '\'' +
                ", FlightArrtimeDate='" + flightArrtimeDate + '\'' +
                ", BoardGate='" + boardGate + '\'' +
                ", FlightState='" + flightState + '\'' +
                ", ShareFlightNo='" + shareFlightNo + '\'' +
                ", FlightHTerminal='" + flightHTerminal + '\'' +
                ", FlightTerminal='" + flightTerminal + '\'' +
                ", StopFlag='" + stopFlag + '\'' +
                ", ShareFlag='" + shareFlag + '\'' +
                ", LegFlag='" + legFlag + '\'' +
                ", FlightDep='" + flightDep + '\'' +
                ", FlightArr='" + flightArr + '\'' +
                ", FlightDepAirport='" + flightDepAirport + '\'' +
                ", FlightArrAirport='" + flightArrAirport + '\'' +
                ", fjson='" + fjson + '\'' +
                ", FlightNo='" + flightNo + '\'' +
                ", fmodifytime='" + fmodifytime + '\'' +
                '}'
    }
}