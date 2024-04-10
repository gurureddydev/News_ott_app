import kotlinx.coroutines.flow.Flow
import model.UserDM

interface UserDomainRepository {
    suspend fun getUsers(isRemote: Boolean): Flow<List<UserDM>>
}