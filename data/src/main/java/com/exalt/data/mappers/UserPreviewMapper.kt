package com.exalt.data.mappers

import com.exalt.api.models.Location
import com.exalt.api.models.UserPreviewDTO
import com.exalt.domain.profile.models.LocationPreviewModel
import com.exalt.domain.profile.models.UserPreviewModel
import javax.inject.Inject

class UserPreviewMapper @Inject constructor() {

    fun fromDto(userPreviewDTO: UserPreviewDTO): UserPreviewModel = UserPreviewModel(
        dateOfBirth = userPreviewDTO.dateOfBirth,
        email = userPreviewDTO.email,
        firstName = userPreviewDTO.firstName,
        gender = userPreviewDTO.gender,
        lastName = userPreviewDTO.lastName,
        location = locationFromDto(userPreviewDTO.location),
        phone = userPreviewDTO.phone,
        picture = userPreviewDTO.picture,
        registerDate = userPreviewDTO.registerDate,
        title = userPreviewDTO.title,
        updatedDate = userPreviewDTO.updatedDate
    )

    private fun locationFromDto(location: Location): LocationPreviewModel = LocationPreviewModel(
        city = location.city,
        country = location.country,
        state = location.state,
        street = location.street
    )

}