package com.example.movieapp.data.credits

fun validationActorsList(creditsDTO: CreditsDTO): ArrayList<Actor>{
    val list = arrayListOf<Actor>()
    creditsDTO.cast?.let { listActorDTO ->
        for ((index) in listActorDTO.withIndex()){
            list.add(validationActor(listActorDTO[index]))
        }
    }
    return list
}

fun validationActor(actorDTO: ActorDTO?): Actor {
    return Actor().also { actor ->
        actor.adult = actorDTO?.adult ?: false
        actor.gender = actorDTO?.gender ?: 2
        actor.id = actorDTO?.id ?: 3223
        actor.knownForDepartment = actorDTO?.knownForDepartment ?: ""
        actor.name = actorDTO?.name ?: ""
        actor.originalName = actorDTO?.originalName ?: ""
        actor.popularity = actorDTO?.popularity ?: 0.0
        actor.profilePath = actorDTO?.profilePath ?: ""
        actor.castId = actorDTO?.castId ?: 46
        actor.character = actorDTO?.character ?: ""
        actor.creditId = actorDTO?.creditId ?: ""
        actor.order = actorDTO?.order ?: 0
    }
}

fun validationMemberFilmCrew(mfcDTO: MemberFilmCrewDTO?) : MemberFilmCrew{
    return MemberFilmCrew().also { mfc ->
        mfc.adult = mfcDTO?.adult ?: false
        mfc.gender = mfcDTO?.gender ?: 2
        mfc.id = mfcDTO?.id ?: 36
        mfc.knownForDepartment = mfcDTO?.knownForDepartment ?: ""
        mfc.name = mfcDTO?.name ?: ""
        mfc.originalName = mfcDTO?.originalName ?: ""
        mfc.popularity = mfcDTO?.popularity ?: 0.0
        mfc.profilePath = mfcDTO?.profilePath ?: ""
        mfc.creditId = mfcDTO?.creditId ?: ""
        mfc.department = mfcDTO?.department ?: ""
    }
}