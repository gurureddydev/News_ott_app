import com.guru.data_common.local.UserEntity
import model.UserDM


internal fun UserEntity.toUserDM() : UserDM {
    return when (this){
        null -> {
            UserDM(id = 0, name =null, image_url = null)
        }
        else -> {
            UserDM(id, name, image_url)
        }
    }
}

internal fun List<UserEntity>.toListUserDM()  = this.map {
    it.toUserDM()
}

internal fun UserDM.toUserEntity() : UserEntity {
    return when (this){
        null -> {
            UserEntity(id = 0, name =null, image_url = null)
        }
        else -> {
            UserEntity(id, name, image_url)
        }
    }
}

internal fun List<UserDM>.toListUserEntity()  = this.map {
    it.toUserEntity()
}