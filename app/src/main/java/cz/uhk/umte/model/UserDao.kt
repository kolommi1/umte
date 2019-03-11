package cz.uhk.umte.model

import com.raizlabs.android.dbflow.sql.language.Select

class UserDao {

    fun createOrUpdate(user: UserDB){
        user.save()
    }

    fun delete(user: UserDB){
        user.delete()
    }

    fun selectAll() : List<UserDB> {
        return Select().from(UserDB::class.java).queryList()
    }

    fun selectById(id: Int) : UserDB? {
        return Select().from(UserDB::class.java).where(UserDB_Table.id.eq(id)).querySingle()
    }
}