package com.geeks.rickandmorty.data.mapper

import com.geeks.rickandmorty.data.models.CharacterDto
import com.geeks.rickandmorty.data.models.LocationDto
import com.geeks.rickandmorty.data.models.OriginDto
import com.geeks.rickandmorty.domain.models.Character
import com.geeks.rickandmorty.domain.models.Location
import com.geeks.rickandmorty.domain.models.Origin
fun CharacterDto?.toDomain(): Character {
    return Character(
        id = this?.id ?: 0,
        name = this?.name.orEmpty(),
        status = this?.status.orEmpty(),
        species = this?.species.orEmpty(),
        type = this?.type.orEmpty(),
        gender = this?.gender.orEmpty(),
        origin = this?.origin.toDomain(),
        location = this?.location.toDomain(),
        image = this?.image.orEmpty(),
        episode = this?.episode?: emptyList(),
        url = this?.url.orEmpty(),
        created = this?.created.orEmpty()
    )
}

fun LocationDto?.toDomain(): Location {
    return Location(
        name = this?.name.orEmpty(),
        url = this?.url.orEmpty()
    )
}
fun List<CharacterDto>?.toDomain(): List<Character> {
    return this?.map { characterDto -> characterDto.toDomain() } ?: emptyList()
}
fun OriginDto?.toDomain(): Origin {
    return Origin(
        name = this?.name.orEmpty(),
        url = this?.url.orEmpty()
    )
}
