package com.mr.anonym.riskbook.presentation.managers

fun riskCalculator(entry: Double,stopLoss: Double,volume: Int): Double{
    val perRisk = entry - stopLoss

    return volume / perRisk
}