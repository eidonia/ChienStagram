package com.exalt.profile.mappers

import com.exalt.domain.profile.models.LocationPreviewModel
import com.exalt.domain.profile.models.UserPreviewModel
import com.exalt.profile.viewobjects.Location
import com.exalt.profile.viewobjects.User
import javax.inject.Inject

class UserPreviewMapper @Inject constructor() {

    fun toUser(userPreviewModel: UserPreviewModel) = User(
        dateOfBirth = userPreviewModel.dateOfBirth,
        email = userPreviewModel.email,
        firstName = userPreviewModel.firstName,
        gender = userPreviewModel.gender,
        lastName = userPreviewModel.lastName,
        location = toLocation(userPreviewModel.location),
        phone = userPreviewModel.phone,
        picture = userPreviewModel.picture
    )

    private fun toLocation(locationPreviewModel: LocationPreviewModel) = Location(
        city = locationPreviewModel.city,
        country = locationPreviewModel.country,
        state = locationPreviewModel.state,
        street = locationPreviewModel.street
    )

}