package org.android.ticco.data.mapper

import org.android.ticco.data.datasource.remote.user.CheckOnboardingResponse
import org.android.ticco.data.datasource.remote.user.Checkdata
import org.android.ticco.domain.model.Check
import org.android.ticco.domain.model.CheckOnboarding

fun CheckOnboardingResponse.toCheckOnBoarding(): CheckOnboarding =
    if (success) {
        CheckOnboarding(status = status, message = message, data = data.toCheck())
    } else {
        CheckOnboarding(status = status, message = message, data = null)
    }

fun Checkdata.toCheck(): Check =
    Check(isChecked = isChecked)