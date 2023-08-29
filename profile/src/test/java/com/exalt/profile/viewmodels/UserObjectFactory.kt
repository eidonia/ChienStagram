package com.exalt.profile.viewmodels

import com.exalt.domain.home.models.DomainModelFactory
import com.exalt.profile.viewobjects.Location
import com.exalt.profile.viewobjects.User

object UserObjectFactory {

    fun getDefaultUser(id: String = DomainModelFactory.OWNER_ID) = User(
        dateOfBirth = DomainModelFactory.OWNER_DATE_BIRTH_MAP,
        email = DomainModelFactory.OWNER_EMAIL,
        firstName = DomainModelFactory.OWNER_FIRST_NAME,
        gender = DomainModelFactory.OWNER_GENDER,
        lastName = DomainModelFactory.OWNER_LAST_NAME,
        location = Location(
            city = DomainModelFactory.OWNER_LOCATION_CITY,
            country = DomainModelFactory.OWNER_LOCATION_COUNTRY,
            state = DomainModelFactory.OWNER_LOCATION_STATE,
            street = DomainModelFactory.OWNER_LOCATION_STREET
        ),
        phone = DomainModelFactory.OWNER_PHONE,
        picture = DomainModelFactory.OWNER_PICTURE_URL
    )

}